package com.example.TM.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TM.Model.DTSModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.SPTutorDto;
import com.example.TM.Util.CurrDate;

@Service
public class StudentPanelService extends CentralService {

	public List<Long>[] getTidDid( String semail) {
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
			if(tmp.getSid()==sid) {
				Tids.add(tmp.getTid());
				Dids.add(tmp.getDid());
			}
			
		}}
		arr[0]=Tids;
		arr[1]=Dids;
		return arr;
		
	}
	

	
	public Object[] stuPanel(String semail) {
		Object stuPanel[]=new Object[2];
		Long due=0L;
		List<Long> dtIds[]=getTidDid(semail);
		List<Long> Tids = dtIds[0];
		List<Long> Dids = dtIds[1];
		for(Long did:Dids) {
			due = due+dueRepo.findOneByDid(did).getDueFee();
		}
		stuPanel[0]=due;
		
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
			//if(!(att.equals(null)))
			  spt.setTodayAtt(att);
			}catch (NullPointerException e) {
				spt.setTodayAtt("n/a");
			}
			
			
			tList.add(spt);
		}
		stuPanel[1]=tList;
		
		
		
		return stuPanel;
	}

}

















//public Long getStudentTotalDue(String semail) {
//Long due=0L;
//List<Long> Dids = getTidDid(semail)[1];
//for(Long did:Dids) {
//	due = due+dueRepo.findOneByDid(did).getDueFee();
//}
//
//return due;
//
//}

