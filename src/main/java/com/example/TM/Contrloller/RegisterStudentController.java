package com.example.TM.Contrloller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.DueModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.ReqDto.AddStuReq;
import com.example.TM.ReqDto.BasicStuData;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.CurrDate;


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class RegisterStudentController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getStudent")
	public RegisterStudentModel getStudent(@RequestParam String id) {
		Long mobile= Long.parseLong(id);
		return studentRepo.findOneByMobile(mobile);
	}
	
	@GetMapping("/allStu")
	public List<RegisterStudentModel> getAllStu(){
		return (List<RegisterStudentModel>) studentRepo.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getTotalStudents")
	public long getTotalStudents() {
		return studentRepo.count();
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getBasicStudData")
	public List<BasicStuData>  basicStuData() {
		
		List<BasicStuData> stuList = new ArrayList<BasicStuData>();
		List<RegisterStudentModel> allStu=getAllStu();
		
		for(RegisterStudentModel st:allStu) {
			BasicStuData stu= new BasicStuData();
			stu.setEmail(st.getEmail());
			stu.setMobile(st.getMobile());
			stu.setName(st.getName());
			stuList.add(stu);
		}
		
		return stuList;
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/registerStudent")
	public void addStudent(@RequestBody AddStuReq streq) {
	
		int std = 0 ,  stuFee =0;
		RegisterStudentModel stu = new RegisterStudentModel();
		
		CurrDate dt = new CurrDate();
		stu.setName(streq.getName());
		stu.setMobile(streq.getMobile());
		stu.setEmail(streq.getEmail());
		stu.setStd(streq.getStd());
		stu.setDoj(dt.getCurrDate());
		
		std=Integer.parseInt(streq.getStd());
		stuFee= studentService.getFee(std);
		stu.setFee(stuFee);

		studentRepo.save(stu);	
		

		DueModel due= new DueModel();
		due.setEmail(streq.getEmail());
		due.setMobile(streq.getMobile());
		due.setLd("");//means not made any payment yet
		due.setDm(1L);
		Long fee=(long) stuFee;
		due.setDueFee(fee);
		dueRepo.save(due);
	}
	
	
}
