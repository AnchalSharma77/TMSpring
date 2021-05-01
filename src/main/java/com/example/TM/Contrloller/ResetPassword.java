package com.example.TM.Contrloller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.ResDto.ResetPassDto;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;
import com.example.TM.Util.SendMail;


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class ResetPassword extends CentralService {


	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/resetPassLink")
	public ResponseEntity<BaseResponse> resetLink(@RequestParam String id){
		BaseResponse res= new BaseResponse();
		try {
		String link = "http://localhost:4200/tm/resetPass";
		String body = "Click the following link to reset your password : " +link;
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
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/resetPass")
	public ResponseEntity<BaseResponse>  resetPass (@RequestBody ResetPassDto resetpass){
		BaseResponse res= new BaseResponse();
		String email=resetpass.getEmail();
		String pass= resetpass.getPass();
		RegisterTutorModel tutor = new RegisterTutorModel();
		try {
		tutor = registerTutorRepo.findOneByEmail(email);
		tutor.setPass(new Encrypt().encode(pass));
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
