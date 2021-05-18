package com.example.TM.Service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.DTSModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.SPTutorDto;
import com.example.TM.ResDto.StudentPanelRes;
import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@Service
public class StudentPanelService extends CentralService {

	/***
	 * <p>
	 * Lists all the dues and tutors the specified <code>semail</code>
	 * (student) is registered with.
	 * </p>
	 * @param semail must not be {@literal null}.
	 * @return List
	 */
	public List<Long>[] getTidDid( String semail) {
		@SuppressWarnings("unchecked")
		List<Long> arr[] = new ArrayList[2];
		List<Long> Sids = new ArrayList<Long>();
		List<Long> Tids = new ArrayList<Long>();
		List<Long> Dids = new ArrayList<Long>();
		List<RegisterStudentModel> Students= studentRepo.findByEmail(semail);
		for(RegisterStudentModel s : Students) {
			Sids.add(s.getSid());
		}
		List<DTSModel> dtsList= (List<DTSModel>) dtsRepo.findAll();
		for(Long sid :Sids) {
		for(DTSModel tmp : dtsList) {
			
			Long tmpSid=tmp.getSid();
			if(tmpSid.equals(sid)) {
				Tids.add(tmp.getTid());
				Dids.add(tmp.getDid());
			}
			
		}}
		arr[0]=Tids;
		arr[1]=Dids;
		return arr;
		
	}
	
	/***
	 * <p>
	 * Lists the things to be displayed on the dashboard of <code>email</code>
	 * (student).<br>
	 * e.g. total due, attendance, etc
	 * </p>
	 * @param email must not be {@literal null}.
	 * @return StudentPanelRes
	 * @throws ApplicationException
	 */
	public ResponseEntity<StudentPanelRes> stuPanel(String email) throws ApplicationException {
		 HttpStatus httpSts = HttpStatus.OK;
		 StudentPanelRes res= new StudentPanelRes();
		try {
		Object stuPanel[]=new Object[3];
		Long due=0L;
		String semail=new Encrypt().decode(email);
		List<Long> dtIds[]=getTidDid(semail);
		List<Long> Tids = dtIds[0];
		List<Long> Dids = dtIds[1];
		for(Long did:Dids) {
			due = due+dueRepo.findOneByDid(did).getDueFee();
		}
		stuPanel[0]=due;
		stuPanel[1]=Tids.size();
		
		List<SPTutorDto> tList= new ArrayList<SPTutorDto>();
		for(Long tid:Tids) {
			RegisterTutorModel t = registerTutorRepo.findOneByTid(tid);
			SPTutorDto spt = new SPTutorDto();
			String tname=t.getFn()+" "+ t.getLn();
			spt.setName(tname);
			spt.setMobile(t.getMobile());
			List<RegisterStudentModel> stuList =(studentRepo.findByEmail(semail));
			for(RegisterStudentModel stu : stuList) {
				if(stu.getTid()== tid) {
					spt.setDueFee(dueRepo.findOneBySid(stu.getSid()).getDueFee());
			}
						
		}
			try {
			String att=attendanceRepo.findOneByTidAndDateAndEmail(tid,new CurrDate().getCurrDate(),semail).getAttendance();
			  spt.setTodayAtt(att);
			}catch (NullPointerException e) {
				spt.setTodayAtt("n/a");
			}
			
			
			tList.add(spt);
		}
		stuPanel[2]=tList;
		
		res.setMessage(new ResopnseCodes().okMsg);
		res.setResponseCode(new ResopnseCodes().ok);
		res.setStuPanel(stuPanel);
		return ResponseEntity.status(httpSts).body(res);
		//return stuPanel;
	}
	catch (Exception e) {
		throw new ApplicationException(e.getMessage(), null);
	}
	}

}


