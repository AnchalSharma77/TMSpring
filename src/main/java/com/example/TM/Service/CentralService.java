package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.TM.Repo.AttendanceRepo;
import com.example.TM.Repo.DTSRepo;
import com.example.TM.Repo.DueRepo;
import com.example.TM.Repo.FeeRepo;
import com.example.TM.Repo.NotesRepo;
import com.example.TM.Repo.RegisterStudentRepo;
import com.example.TM.Repo.RegisterTutorRepo;
import com.example.TM.Repo.ScheduleClassRepo;
import com.example.TM.Repo.StudentLoginRepo;
/**
 * 
 * <p>This service autowire all the repositories and 
 * services available in this project</p>
 * 
 * <p> These can be used anywhere in this project by 
 * simply extending the CentralService</p>
 * @author Anchal
 * @see https://github.com/AnchalSharma77
 */
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
	@Autowired
	public DTSRepo dtsRepo;
	@Autowired
	public NotesRepo notesRepo;
	@Autowired
	public ScheduleClassRepo scheduleClassRepo;
	
	
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
    public	StudentLoginService studentLoginService;
	@Autowired
	public PaymentService paymentService;
	@Autowired
	public FeeService feeService;
	@Autowired
	public StudentPanelService studentPanelService;
	@Autowired
	public AttendanceService attendanceService ; 
	@Autowired
	public ProfileImgService profileImgService;
	@Autowired
	public NotesService notesService;
	@Autowired
	public ScheduleClassService scheduleClassService;
	@Autowired
	public ClassByStudentService classByStudentService;
	@Autowired
	public ResetPasswordService resetPasswordService;
	
	@Autowired
	Environment env;

}
