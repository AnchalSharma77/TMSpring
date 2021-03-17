package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.TM.Model.StudentModel;
import com.example.TM.Repo.FeeRepo;
import com.example.TM.Repo.StudentRepo;

@Service
public class CentralService {
	//Repos
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	FeeRepo feeRepo;
	
	//Services
	@Autowired
	StudentService studentService;
	
	
	//Models
	

}
