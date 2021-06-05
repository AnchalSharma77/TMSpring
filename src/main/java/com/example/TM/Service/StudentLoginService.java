package com.example.TM.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.StudentLoginModel;
import com.example.TM.ReqDto.LoginStudentDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Util.OTP;
import com.example.TM.Util.ResopnseCodes;
import com.example.TM.Util.SendMail;

@Service
public class StudentLoginService extends CentralService {

	/***
	 * Sends the OTP required during Login to the <code>id</code> .
	 * @param id must not be {@literal null}.
	 * @return BaseResponse
	 */
	public ResponseEntity<BaseResponse> sendLoginOtp( String id) {
		StudentLoginModel student = new StudentLoginModel();
		BaseResponse res= new BaseResponse();
		try {
		String email=id;
		String otp = new OTP().getOtp();
	    //String body = "Your OTP to login on TM  is : "+otp;

		String body ="<div style='color:#777;'><p><h3> Hello, </h3> "
				+ "<h3> Please use the verification code below on the TM website. </h3>"
				+ "<h2> " +otp+" </h2>"
				+ "<h3 style='color:#777;'> If you didn't request this, you can ignore this email or let us know. </h3></p>"
				+ "<h3 style='color:#777;'> Thanks!</h3></div>";
		new SendMail().sendFromGMail(email ,body);
		long tmstmp = System.currentTimeMillis();
		student.setId(email);
		student.setOtp(otp);
		student.setTime(tmstmp);
		studentLoginRepo.save(student);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch (Exception e) {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
	}
	
	/**
	 * Authenticate the user.
	 * @param stu must not be {@literal null}.
	 * @return BaseResponse
	 */
	public ResponseEntity<BaseResponse> loginStudent(LoginStudentDto stu) {
		BaseResponse res= new BaseResponse();
		try {
		String id =stu.getId();
		String sentOtp= studentLoginRepo.findOneById(id).getOtp();
		String rcvdOtp = stu.getOtp();
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
	
	
//	public String body(String otp) {
//		String body ="<h3> Hello , </h3> <br>"
//				+ "<h3> Pleas use the verification code below on the TM website. </h3><br>"
//				+ "<h1> " +otp+"</h1><br>"
//				+ "<h3> If you didn't request this, you can ignore this email or let us know. </h3><br>"
//				+ "<h3> Thanks!</h3>"
//				;
//		
//		
//		return body;
//	}
//	
}
