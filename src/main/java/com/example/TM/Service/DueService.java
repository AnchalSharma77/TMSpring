package com.example.TM.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TM.Model.DueModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.Util.CurrDate;

@Service
public class DueService extends CentralService {
	
	CurrDate currDate = new CurrDate();

	/***
	 * 
	 * @param id must not be {@literal null}.
	 * @return
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

	/***
	 * 
	 * @param id must not be {@literal null}.
	 * @return Long array containing due months and due fee
	 */
	public Long[] getDueData(String id  ) {
	
		Long due[]= new Long[2];
		Long dueFee=0L;
		String std;
		boolean isEmail=registerTutorService.isEmail(id);
	
		if(studentExist(id)) {
			std=isEmail?studentRepo.findOneByEmail(id).getStd():studentRepo.findOneByMobile(id).getStd(); 
			
		/*if(registerTutorService.isEmail(id)) {
			std=studentRepo.findOneByEmail(id).getStd();
			
		}
		else
		 std= studentRepo.findOneByMobile(id).getStd(); */
		
		int st=Integer.parseInt(std);
	//	String lastDate=getLastDate(id);
		int monthFee = feeRepo.findOneByStd(st).getFee();
		//Long duration= getDueMonth(lastDate);
		Long duration =isEmail? dueRepo.findOneByEmail(id).getDm(): dueRepo.findOneByMobile(id).getDm();
		
		due[0]=duration;
		dueFee=duration*monthFee;
		
		due[1]=dueFee;
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
		//DueModel stu;
		boolean isEmail=registerTutorService.isEmail(id);
		String lastDate=isEmail?dueRepo.findOneByEmail(id).getLd():dueRepo.findOneByMobile(id).getLd();
		/*if(registerTutorService.isEmail(id)) {
		 stu=dueRepo.findOneByEmail(id);
		}
		else {
			 stu=dueRepo.findOneByMobile(id);
		}*/
		//return stu.getLd();
		return lastDate;
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

	
	public Long getDuesSum() {
		Long sum = 0L;
		List<DueModel> dmodel= getAll();
		if(!(dmodel.equals(null))) {
			for(DueModel stu : dmodel) {
				sum= sum+ stu.getDueFee();
			}
		}
		return sum;
	}
}
