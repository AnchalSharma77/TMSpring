package com.example.TM.Contrloller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.RegisterModel;
import com.example.TM.Repo.RegistrationRepo;
import com.example.TM.ReqDto.RegisterDto;
import com.example.TM.Service.RegisterService;
import com.example.TM.Util.Encrypt;


@RestController
@RequestMapping(value="/app",produces =" application/json")
public class RegisterController {
	
	@Autowired
	RegistrationRepo registrationRepo;
	@Autowired
	RegisterService registerService;
	
	@GetMapping("/register")
	public RegisterModel getTutor(@RequestParam String id) {
		return registerService.getTutor(id);
	}
	
	
	@PostMapping("/register")
	public void addTutor(@RequestBody RegisterDto reg) {
		registerService.addTutor(reg);
		
	}

}
