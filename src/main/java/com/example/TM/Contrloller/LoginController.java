package com.example.TM.Contrloller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.LoginModel;
import com.example.TM.Service.LoginService;

@RestController
@RequestMapping(value="/app",produces =" application/json")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@GetMapping("/Login")
	public void login(@RequestBody LoginModel tutor) {
		System.out.println("controller");
		String id= tutor.getId();
		String pass= tutor.getPass();
		loginService.login(id, pass);
	}
}
