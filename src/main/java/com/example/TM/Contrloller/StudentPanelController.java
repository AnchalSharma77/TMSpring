package com.example.TM.Contrloller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Service.CentralService;
import com.example.TM.Util.Encrypt;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class StudentPanelController extends CentralService {
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/getStudentTotalDue")
//	public Long getStudentTotalDue(@RequestParam String sid) {
//		return studentPanelService.getStudentTotalDue(sid);
//		
//	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/studentPanel")
	public Object[] stuPanel(@RequestParam String id) {
		String semail=new Encrypt().decode(id);
		return studentPanelService.stuPanel(semail);
		
	}
	
}
