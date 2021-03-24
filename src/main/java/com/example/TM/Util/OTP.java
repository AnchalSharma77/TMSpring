package com.example.TM.Util;

import java.security.SecureRandom;

public class OTP {
	
	String otp="";
	 public String getOtp() {
		 SecureRandom rand = new SecureRandom();
		 
		 for(int i=0;i<6;i++) {
		 int n = rand.nextInt(9);
		 System.out.println(n );
		 otp= otp.concat(String.valueOf(n));
		 }
		 System.out.println(otp);
		 return otp;
	 }

}
