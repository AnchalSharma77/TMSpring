package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TM.Model.StudentModel;
import com.example.TM.Repo.StudentRepo;

public class CentralService {
	//Repos
	@Autowired
	StudentRepo studentRepo;
	
	//Services
	@Autowired
	StudentService studentService;
	
	
	//Models
	@Autowired
	StudentModel studentModel ;

}
