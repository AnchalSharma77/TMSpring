package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TM.Repo.AttendanceRepo;
import com.example.TM.Repo.DueRepo;
import com.example.TM.Repo.FeeRepo;
import com.example.TM.Repo.RegisterStudentRepo;
import com.example.TM.Repo.RegisterTutorRepo;
import com.example.TM.Repo.StudentLoginRepo;

@Service
public class CentralService {
	//Repos
	@Autowired
	public FeeRepo feeRepo;
	@Autowired
	public DueRepo dueRepo;
	@Autowired
	public RegisterStudentRepo studentRepo;
	@Autowired
	public RegisterTutorRepo registerTutorRepo;
	@Autowired
	public StudentLoginRepo studentLoginRepo;
	@Autowired
	public AttendanceRepo attendanceRepo;
	
	//Services
	@Autowired
	public RegisterStudentService studentService;
	@Autowired
	public DueService dueService;
	@Autowired
	public LoginService loginService;
	@Autowired
	public RegisterStudentService registerStudentService;
	@Autowired
	public RegisterTutorService registerTutorService;
	@Autowired
    private	StudentLoginService studentLoginService;
	

}
