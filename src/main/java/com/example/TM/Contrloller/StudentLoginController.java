package com.example.TM.Contrloller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.StudentLoginModel;
import com.example.TM.ReqDto.LoginStudentDto;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.OTP;
import com.example.TM.Util.SendMail;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class StudentLoginController extends CentralService{
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getStudentLoginOtp")
	public void sendLoginOtp(@RequestBody LoginStudentDto stu) {
		StudentLoginModel student = new StudentLoginModel();
		SendMail sendOtp = new SendMail();
		OTP otpOb = new OTP();
		String otp = otpOb.getOtp();
		String email=stu.getEmail();
		student.setEmail(email);
		student.setOtp(otp);
		studentLoginRepo.save(student);
		//return otp;
		sendOtp.sendFromGMail(email ,otp);
		
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/getStudentLoginOtp")
	public boolean loginStudent(@RequestBody LoginStudentDto stu) {
		String id =stu.getEmail();
		String sentOtp= studentLoginRepo.findOneByEmail(id).getOtp();
		String rcvdOtp = stu.getOtp();
		if(sentOtp.equalsIgnoreCase(rcvdOtp)) {
			System.out.println("true");
			return true;
		}
		return false;
	}
	

}
