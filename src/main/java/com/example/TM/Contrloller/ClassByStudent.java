package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ResDto.ScheduledClassRes;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class ClassByStudent extends CentralService {

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/classByStudent", method = RequestMethod.GET)
	public ResponseEntity<ScheduledClassRes> scheduleClass( @RequestParam("id") String id) throws Exception  {
		 try {
	            return classByStudentService.scheduledClassesByStudent(id);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}
}
