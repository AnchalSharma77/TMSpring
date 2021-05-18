package com.example.TM.Service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.Model.ScheduleClassModel;
import com.example.TM.ResDto.ScheduledClassRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@Service
public class ClassByStudentService extends CentralService{

	/***
	 * Create a list of all the classes the {@code id} is authorized to attend.
	 * @param id email id with which the particular student is registered.
	 * @return {@code ScheduledClassRes}
	 * @throws ApplicationException
	 */
	public ResponseEntity<ScheduledClassRes> scheduledClassesByStudent(String id) throws ApplicationException {
		try {
			ScheduledClassRes res = new ScheduledClassRes();
		    HttpStatus httpSts = HttpStatus.OK;
		    List<ScheduleClassModel> allClasses =  new ArrayList<ScheduleClassModel>();
		    List<RegisterStudentModel> stu = studentRepo.findByEmail(new Encrypt().decode(id));
		    if(stu == null) {
		    	res.setMessage(new ResopnseCodes().notFoundMsg);
				res.setResponseCode(new ResopnseCodes().notFound);
		    }
		    
		    else {
		    	 for(RegisterStudentModel tmp:stu) {
		    		 String std = tmp.getStd();
		    		 Long Tid= tmp.getTid();
		    		 List<ScheduleClassModel> cls= scheduleClassRepo.findByStdAndTid(std,Tid);
		    		 
		    		 allClasses.addAll(cls);
				    }
			res.setMessage(new ResopnseCodes().okMsg);
			res.setResponseCode(new ResopnseCodes().ok);
			res.setAllClasses(allClasses);
		    }
		return ResponseEntity.status(httpSts).body(res);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), null);
		}
		}
	
}
