package com.example.TM.Contrloller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.LoginReqDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.LoginService;
import com.example.TM.Util.ResopnseCodes;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<BaseResponse> login(@RequestBody LoginReqDto tutor) {
		String id= tutor.getId();
		String pass= tutor.getPass();
		BaseResponse res= new BaseResponse();
		try {
		if(loginService.authenticate(id, pass)) {
			res.setResCode(new ResopnseCodes().ok);
			res.setResMsg(new ResopnseCodes().okMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		else {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		}
		catch(Exception e) {
		res.setResCode(new ResopnseCodes().invalid);
		res.setResMsg(new ResopnseCodes().invalidMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		
			
	}
	
	
	
}
