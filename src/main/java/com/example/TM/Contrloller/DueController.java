package com.example.TM.Contrloller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/app",produces =" application/json")
public class DueController {
	
	@GetMapping("/due")
	public String getDue(String id) {
		
		return null;
	}

}
