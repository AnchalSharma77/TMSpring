package com.example.TM.Contrloller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.StudentModel;
import com.example.TM.Repo.StudentRepo;
import com.example.TM.ReqDto.AddStuReq;
import com.example.TM.Service.CentralService;
import com.example.TM.Service.StudentService;
import com.example.TM.Util.CurrDate;


@RestController
@RequestMapping(value="/app",produces =" application/json")
public class StudentController {
	
	
	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	StudentService studentService;
	

	
	@GetMapping("/student")
	public Optional<StudentModel> getStudent(@RequestParam Long id) {
		System.out.println("Here...............");
		return studentRepo.findById(id);
	}
	
	@PostMapping("/student")
	public void addStudent(@RequestBody AddStuReq streq) {
		StudentModel stu = new StudentModel();
		CurrDate dt = new CurrDate();
		stu.setName(streq.getName());
		stu.setPhone(streq.getPhone());
		stu.setStd(streq.getStd());
		stu.setDoj(dt.getCurrDate());
		stu.setFee(studentService.getFee(Integer.parseInt(streq.getStd())));
		studentRepo.save(stu);
	}
}
