package com.example.TM.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TM.Model.DueModel;
import com.example.TM.Model.FeeModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.ResDto.DueDto;
import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;

@Service
public class DueService extends CentralService {
	
	CurrDate currDate = new CurrDate();

	/***
	 * 
	 * @param id must not be {@literal null}.
	 * @return {@code true} if the id is registered , {@code false} otherwise
	 */
	public boolean studentExist(String id) {
		
		boolean studentExist =(dueRepo.existsByEmail(id) || dueRepo.existsByMobile(id));
		if(studentExist)
			return true;
		
		return false;
	}


	/***
	 * 
	 * @param lastDate
	 * @return
	 */
	
	public Long getDueMonth(String lastDate) {
		if(lastDate.equalsIgnoreCase("")) {
			return 1L;
		}
		
		Long day=currDate.getDateDiff(lastDate);
		Long days = day % 30;
		Long month = day / 30;
		System.out.println(day + " days = " + month + " Month and " + days + " days");
		return month;
	}

	
	/**
	 * 
	 * <p> sid is te id of student  and eid
	 * is the user id of tutor , which is used
	 * to get the unique {@literal Tid} .
	 * @param sid must not be {@literal null}
	 * @param eid must not be {@literal null}
	 * @return  Long array containing due months and due fee
	 */
	public Long[] getDueData(String sid , String eid ) {
	
		Long due[]= new Long[2];
		Long dueFee=0L;
		String std;
	//	Long tid;
		//RegisterStudentModel student;
		List<FeeModel> fees;
		boolean isEmail=registerTutorService.isEmail(sid);
	
		if(studentExist(sid)) {
			std=isEmail?studentRepo.findOneByEmail(sid).getStd():studentRepo.findOneByMobile(sid).getStd(); 
		
		int st=Integer.parseInt(std);
		
		Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(eid)).getTid();
		fees=feeRepo.findByTid(Tid);
		for(FeeModel f :fees) {
		if(f.getStd()==st) {
		int monthFee=f.getFee();
		Long duration =isEmail? dueRepo.findOneByEmail(sid).getDm(): dueRepo.findOneByMobile(sid).getDm();
		
		due[0]=duration;
		dueFee=duration*monthFee;
		
		due[1]=dueFee;
		}}
		}
		return due;
	}
	/***
	 * 
	 * @param id must not be {@literal null}.
	 * @return Standard corresponding to specific userid {@code id}
	 */
	public String getStd(String id) {
		Long mobile= Long.parseLong(id);
		RegisterStudentModel stud=studentRepo.findOneByMobile(mobile);
		return stud.getStd();
	}
	
	/***
	 * Return the last Date on which fee was paid , for given id
	 * @param id must not be {@literal null}.
	 * @return String
	 */
	public String getLastDate(String id) {
		boolean isEmail=registerTutorService.isEmail(id);
		String lastDate=isEmail?dueRepo.findOneByEmail(id).getLd():dueRepo.findOneByMobile(id).getLd();
		
		return lastDate;
	}
	/***
	 * 
	 * @param id
	 * @return total due fee of the student registered with {@literal id}
	 */
	public Long getDueById(String id) {
		
		return registerTutorService.isEmail(id)?dueRepo.findOneByEmail(id).getDueFee():dueRepo.findOneByMobile(id).getDueFee();
	}
	
	/***
	 * 
	 * Returns all instances of the type {@code DueModel} with the given IDs.
	 * <p>
	 * If some or all ids are not found, no entities are returned for these IDs.
	 * <p>
	 * Note that the order of elements in the result is not guaranteed
	 * 
	 * @return  List
	 */
	public List<DueModel> getAll(){
		return (List<DueModel>) dueRepo.findAll();
	}

	/**
	 * 
	 * @return Sum of due fee of all the students
	 */
	public Long getDuesSum(String id) {
		Long sum = 0L;
		Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
		List<DueModel> dmodel= getAll();
		if(!(dmodel.equals(null))) {
			
			for(DueModel stu : dmodel) {
				if(Tid==stu.getTid())
				sum= sum+ stu.getDueFee();
			}
		}
		return sum;
	}
	
	public List<DueDto> listDueStudents (String tid) {
		Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(tid)).getTid();
		List<DueDto> dueList= new ArrayList<DueDto>();
		List<DueModel> allDueList = getAll();
		for(DueModel d : allDueList) {
			if(d.getTid()==Tid) {
				DueDto dto = new DueDto();
				dto.setDm(d.getDm());
				dto.setDueFee(d.getDueFee());
				dto.setLd(d.getLd());
				dto.setEmail(d.getEmail());
				dto.setMobile(d.getMobile());
				dueList.add(dto);
			}
		}
		return dueList;
	}
}
