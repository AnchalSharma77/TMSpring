package com.example.TM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.TM.Util.Encrypt;
import com.example.TM.Util.OTP;

@SpringBootApplication
public class TmApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmApplication.class, args);
//		OTP o = new OTP();
//		o.getOtp();
//		
//		System.out.println(Encrypt.encode("abc"));
//		System.out.println(Encrypt.encode("abc"));
		
		
	}

}

