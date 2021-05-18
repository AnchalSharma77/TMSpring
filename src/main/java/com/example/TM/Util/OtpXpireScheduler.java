package com.example.TM.Util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.TM.Model.StudentLoginModel;
import com.example.TM.Service.CentralService;

@Component
public class OtpXpireScheduler extends CentralService{
	/***
	 * <p>
	 * In every 5 minutes, this scheduler checks if any 
	 * OTP is expired , if so it removes that record.
	 * </p>
	 * <p>
	 * An expired OTP is one , which has been sent 3hrs ago.
	 * </p>
	 */
	@Scheduled(fixedDelay = 300000)
	public void removeExpierdOtps() {
		System.out.println("scheduler");
		long currTime = System.currentTimeMillis();
		long expireAfter =3*60*60*1000; 
		Iterable<StudentLoginModel> allOtp=  studentLoginRepo.findAll();
		for(StudentLoginModel tmp:allOtp) {
			System.out.println("here");
			long otpTime=tmp.getTime();
			long diff = currTime-otpTime;
			if(diff>=expireAfter) {
				studentLoginRepo.delete(tmp);
			}
		}
	}
}
