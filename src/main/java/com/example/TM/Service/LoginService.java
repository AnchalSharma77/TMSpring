package com.example.TM.Service;

import org.springframework.stereotype.Service;

@Service
public class LoginService extends CentralService {
	
	
	    /***
	     * <p>
	     * Authenticate tutor.
	     * </p>
	     * <p>
	     * {@literal usrpass} is the password provided by the user while logging in<br> 
	     * {@literal getPass} is the actual password set by user while creating account , fetched from database.<br>
	     * Both are encrypted 
	     * </p>
	     * @param usrpass must not be {@literal null}.
	     * @param getPass must not be {@literal null}.
	     * @return <code>true</code> if credentials are valid , else <code>false</code> 
	     */
		boolean checkPassword(String usrpass , String getPass) {
		//System.out.println("usr pass" +Encrypt.encode(usrpass)); if(getPass.equals(Encrypt.encode(usrpass)))
		if(getPass.equals(usrpass)){
			   return true;}
		return false;
	}
	public boolean authenticate(String id , String pass) {
		String password=null;
		
		boolean userExist=registerTutorService.tutorExist(id);
		if(userExist) {
			if(registerTutorService.isEmail(id)) {
				password=registerTutorRepo.findOneByEmail(id).getPass();
				return checkPassword(pass, password);
			}
			else {
				System.out.println("mobile");
				password=registerTutorRepo.findOneByMobile(id).getPass();
				return checkPassword(pass, password);
			}
			
			
			}
			return false;
		}

}
