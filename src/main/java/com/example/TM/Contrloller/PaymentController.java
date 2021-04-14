package com.example.TM.Contrloller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class PaymentController {
	
	@PostMapping("/payment")
	public void pay() {
		//TODO : payment functionality
	}
	

}
