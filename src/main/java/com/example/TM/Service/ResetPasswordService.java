package com.example.TM.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.ResDto.ResetPassDto;
import com.example.TM.Util.ResopnseCodes;
import com.example.TM.Util.SendMail;

@Service
public class ResetPasswordService extends CentralService {

	public ResponseEntity<BaseResponse> resetLink(String id){
		BaseResponse res= new BaseResponse();
		try {
		String link = "http://localhost:4200/tm/resetPass";
//		String body = "Click the following link to reset your password : " +link;
		String body ="<div style='color:#777;'><p><h3> Hello, </h3> "
				+ "<h3> Please use the following link to reset your password :</h3>"
				+ "<h2> " +link+" </h2>"
				+ "<h3 style='color:#777;'> If you didn't request this, you can ignore this email or let us know. </h3></p>"
				+ "<h3 style='color:#777;'> Thanks!</h3></div>";
		new SendMail().sendFromGMail(id, body);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch(Exception e) {
		res.setResCode(new ResopnseCodes().notFound);
		res.setResMsg(new ResopnseCodes().notFoundMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
		
	}
	
	
	public ResponseEntity<BaseResponse>  resetPass ( ResetPassDto resetpass){
		BaseResponse res= new BaseResponse();
		String email=resetpass.getEmail();
		String pass= resetpass.getPass();
		RegisterTutorModel tutor = new RegisterTutorModel();
		try {
		tutor = registerTutorRepo.findOneByEmail(email);
		tutor.setPass(pass);
		registerTutorRepo.save(tutor);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch(Exception e) {
		res.setResCode(new ResopnseCodes().notFound);
		res.setResMsg(new ResopnseCodes().notFoundMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	}
}