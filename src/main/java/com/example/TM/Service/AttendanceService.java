package com.example.TM.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.TM.Model.AttendanceModel;
import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;

/***
 * Provides the functionality needed to 
 * mark attendance of all the students 
 * registered with the particular tutor.
 * @author Anchal
 *
 */
@Service
public class AttendanceService extends CentralService {

	/***
	 * Marks/Updates the attendance of the student for current day .
	 * @param data basic student data along with today's attendance
	 * @param tid id with which the particular tutor in registered
	 */
	public void markAttendance( List<AttendanceModel> data ,  String tid) {
	
		for(AttendanceModel mrkAtndnc: data) {	
			String email=mrkAtndnc.getEmail();
			String date=new CurrDate().getCurrDate();
			String att=mrkAtndnc.getAttendance();
			Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(tid)).getTid();
			
			
			AttendanceModel record= new AttendanceModel();
			if(attendanceRepo.existsOneByEmailAndDateAndTid(email,date,Tid)) {
				record=attendanceRepo.findOneByEmailAndDateAndTid(email,date,Tid);
				record.setAttendance(att);
			}
			else {
			record.setMobile(mrkAtndnc.getMobile());
			record.setEmail(email);
			record.setName(mrkAtndnc.getName());
			record.setDate(date);
			record.setAttendance(att);
			record.setTid(Tid);
			}
			
			attendanceRepo.save(record);
		}
	}
	
}
	
