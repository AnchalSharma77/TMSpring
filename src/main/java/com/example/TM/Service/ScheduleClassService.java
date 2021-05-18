package com.example.TM.Service;


import java.util.List;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.ScheduleClassModel;
import com.example.TM.ReqDto.ScheduleClassDto;
import com.example.TM.ResDto.ScheduleClassRes;
import com.example.TM.ResDto.ScheduledClassRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

import javassist.bytecode.SignatureAttribute.TypeParameter;

@Service
public class ScheduleClassService extends CentralService {
	
	/***
	 * <p>
	 * Add a <code>class</code> against <code>id</code> (tutor) 
	 * as per the data provided in <code>classData</code>
	 * </p>
	 * @param id must not be {@literal null}.
	 * @param classData must not be {@literal null}.
	 * @return ResponseEntity {@link TypeParameter ScheduleClassRes}
	 * @throws ApplicationException
	 */
	public ResponseEntity<ScheduleClassRes> scheduleClass(String id, ScheduleClassDto classData) throws ApplicationException {
		try {
			ScheduleClassRes res = new ScheduleClassRes();
		    HttpStatus httpSts = HttpStatus.OK;
		    Long Tid = registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
		    ScheduleClassModel cls = new ScheduleClassModel();
		    String msg=classData.getMsg();
		    String std=classData.getStd();
		    String link = classData.getLink();
		    String name =classData.getName();
		    String date = classData.getDate();
		    cls.setDate(classData.getDate());
		    cls.setMsg(msg);
		    cls.setStd(std);
		    cls.setTid(Tid);
		    cls.setName(name);
		    cls.setDate(date);
		    cls.setLink(link);
			scheduleClassRepo.save(cls);	
			
		    res.setMessage(msg);
		    res.setStd(std);
		    res.setDate(date);
		    res.setLink(link);
		    res.setName(name);
			res.setMessage(new ResopnseCodes().okMsg);
			res.setResponseCode(new ResopnseCodes().ok);


		return ResponseEntity.status(httpSts).body(res);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), null);
		}
		}
	
	/**
	 * 
	 * <p>
	 * Lists all the classes scheduled by <code>id</code> (tutor)
	 * </p>
	 * @param id must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
	public ResponseEntity<ScheduledClassRes> scheduledClasses(String id) throws ApplicationException {
		try {
			ScheduledClassRes res = new ScheduledClassRes();
		    HttpStatus httpSts = HttpStatus.OK;
		    Long Tid = registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
		  //  ScheduleClassModel cls = new ScheduleClassModel();
		    List<ScheduleClassModel> allClasses =  scheduleClassRepo.findByTid(Tid);
		    if(allClasses== null) {
		    	res.setMessage(new ResopnseCodes().invalidMsg);
				res.setResponseCode(new ResopnseCodes().invalid);
		    }
		    else {
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
