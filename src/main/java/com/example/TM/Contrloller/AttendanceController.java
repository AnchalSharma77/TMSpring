package com.example.TM.Contrloller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.AttendanceModel;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.CurrDate;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class AttendanceController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/markAttendance")
	public void markAttendance(@RequestBody List<AttendanceModel> data) {
		for(AttendanceModel mrkAtndnc: data) {	
		AttendanceModel record= new AttendanceModel();
		record.setMobile(mrkAtndnc.getMobile());
		record.setEmail(mrkAtndnc.getEmail());
		record.setName(mrkAtndnc.getName());
		record.setDate(new CurrDate().getCurrDate());
		record.setAttendance(mrkAtndnc.getAttendance());
		
		attendanceRepo.save(record);
		}
	}

}
