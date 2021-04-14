package com.example.TM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TM.Repo.RegisterTutorRepo;
import com.example.TM.Util.Encrypt;

@Service
public class LoginService extends CentralService {
	@Autowired
	RegisterTutorRepo registrationRepo;
	@Autowired
	RegisterTutorService registerService;
	
	boolean checkPassword(String usrpass , String getPass) {
		System.out.println("usr pass" +Encrypt.encode(usrpass));
		if(getPass.equals(Encrypt.encode(usrpass))) {
		
			   return true;}
		return false;
	}
	public boolean authenticate(String id , String pass) {
		String password=null;
		
		boolean userExist=registerService.tutorExist(id);
		if(userExist) {
			if(registerService.isEmail(id)) {
				password=registrationRepo.findOneByEmail(id).getPass();
				return checkPassword(pass, password);
			}
			else {
				System.out.println("mobile");
				password=registrationRepo.findOneByMobile(id).getPass();
				return checkPassword(pass, password);
			}
			
			}
			return false;
		}
			
		
		
	
	public void login(String id, String pass) {
		if(authenticate(id, pass)) {
			System.out.println("user exist");
			//TODO login
		}
		else
			System.out.println("invalid");
	}
	

}
