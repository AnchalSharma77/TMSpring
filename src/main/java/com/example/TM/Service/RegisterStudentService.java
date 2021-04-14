package com.example.TM.Service;


import org.springframework.stereotype.Service;


@Service
public class RegisterStudentService extends CentralService{
		
	public int getFee(int std) {
			return feeRepo.findOneByStd(std).getFee();
	}

}
