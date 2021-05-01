package com.example.TM.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.TM.Model.AttendanceModel;
import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;

@Service
public class AttendanceService extends CentralService {

	public void markAttendance( List<AttendanceModel> data ,  String id) {
	
		for(AttendanceModel mrkAtndnc: data) {	
			String email=mrkAtndnc.getEmail();
			String date=new CurrDate().getCurrDate();
			String att=mrkAtndnc.getAttendance();
			Long tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
			
			
			AttendanceModel record= new AttendanceModel();
			if(attendanceRepo.existsOneByEmailAndDateAndTid(email,date,tid)) {
				record=attendanceRepo.findOneByEmailAndDateAndTid(email,date,tid);
				record.setAttendance(att);
			}
			else {
			record.setMobile(mrkAtndnc.getMobile());
			record.setEmail(email);
			record.setName(mrkAtndnc.getName());
			record.setDate(date);
			record.setAttendance(att);
			record.setTid(tid);
			}
			
			attendanceRepo.save(record);
		}
	}
	
}
	
