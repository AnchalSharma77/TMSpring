package com.example.TM.Contrloller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.StudentLoginModel;
import com.example.TM.ReqDto.LoginStudentDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.OTP;
import com.example.TM.Util.ResopnseCodes;
import com.example.TM.Util.SendMail;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class StudentLoginController extends CentralService{
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/getStudentLoginOtp")
	public ResponseEntity<BaseResponse> sendLoginOtp(@RequestParam("id") String id) {
		StudentLoginModel student = new StudentLoginModel();
		BaseResponse res= new BaseResponse();
		try {
		String email=id;
		String otp = new OTP().getOtp();
	    String body = "Your OTP to login on TM  is : "+otp;
		new SendMail().sendFromGMail(email ,body);
		student.setId(email);
		student.setOtp(otp);
		studentLoginRepo.save(student);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch (Exception e) {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		//return null;
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/studentLogin")
	public ResponseEntity<BaseResponse> loginStudent(@RequestBody LoginStudentDto stu) {
		BaseResponse res= new BaseResponse();
		try {
		String id =stu.getId();
		//System.out.println("================================== id "+ id);
		String sentOtp= studentLoginRepo.findOneById(id).getOtp();
	//	System.out.println("================================== sent otp "+ sentOtp);
		String rcvdOtp = stu.getOtp();
		//System.out.println("================================== rcv otp "+ rcvdOtp);
		if(sentOtp.equalsIgnoreCase(rcvdOtp)) {
			System.out.println("true");
			res.setResCode(new ResopnseCodes().ok);
			res.setResMsg(new ResopnseCodes().okMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
			}
		else {
			res.setResCode(new ResopnseCodes().invalid);
			res.setResMsg(new ResopnseCodes().invalidMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		}
		catch (Exception e) {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
	}
	

}
