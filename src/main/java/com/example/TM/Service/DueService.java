package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TM.Repo.DueRepo;

@Service
public class DueService {
	
	@Autowired
	RegisterService registerService;
	@Autowired
	DueRepo dueRepo;
	public boolean studentExist(String id) {
		
		boolean studentExist =(dueRepo.existsByEmail(id) || dueRepo.existsByMobile(id));
		if(studentExist)
			return true;
		
		return false;
	}

	public String getDue(String id) {
		  String dueMonth = null;
		   if(studentExist(id)) {
			   if(registerService.isEmail(id))
			    //    dueMonth = dueRepo.findById(id).get;
				   return null;
		   }
		
			return dueMonth;
		}

}
