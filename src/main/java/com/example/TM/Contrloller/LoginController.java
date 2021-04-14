package com.example.TM.Contrloller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.LoginReqDto;
import com.example.TM.Service.LoginService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public void login(@RequestBody LoginReqDto tutor) {
		System.out.println("controller");
		String id= tutor.getId();
		String pass= tutor.getPass();
		loginService.login(id, pass);
		System.out.println("Login Succesfull");
	}
	
}
