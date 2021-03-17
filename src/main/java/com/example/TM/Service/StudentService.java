package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TM.Model.FeeModel;
import com.example.TM.Repo.FeeRepo;

import com.example.TM.Service.CentralService;

@Service
public class StudentService {
	
	@Autowired
	FeeRepo feeRepo;
	
	
	
	public int getFee(int std) {
		FeeModel f= new FeeModel();
		f=feeRepo.findById(std).get();
		return f.getFee();
	}

}
