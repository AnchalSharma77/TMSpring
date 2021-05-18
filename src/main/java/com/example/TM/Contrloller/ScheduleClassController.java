package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.ScheduleClassDto;
import com.example.TM.ResDto.ScheduleClassRes;
import com.example.TM.ResDto.ScheduledClassRes;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class ScheduleClassController extends CentralService {
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/scheduleClass", method = RequestMethod.POST)
	public ResponseEntity<ScheduleClassRes> scheduleClass( @RequestParam("id") String id, @RequestBody ScheduleClassDto classData) throws Exception  {
		 try {
	            return scheduleClassService.scheduleClass(id,classData);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/scheduldeClasses", method = RequestMethod.GET)
	public ResponseEntity<ScheduledClassRes> scheduleClass( @RequestParam("id") String id) throws Exception  {
		 try {
	            return scheduleClassService.scheduledClasses(id);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}

}
