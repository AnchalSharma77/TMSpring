package com.example.TM.Service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ReqDto.RegisterTutorDto;
import com.example.TM.Util.Encrypt;


@Service
public class RegisterTutorService extends CentralService {
		
	/***
	 * 
	 * @param usrid
	 * @return{@literal true} if email is entered as usrid
	 */
	public boolean isEmail(String usrid) {
		System.out.println("in is email");
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
		Pattern pat = Pattern.compile(emailRegex); 
		if (usrid == null) 
		return false; 
		return pat.matcher(usrid).matches();
	}
	
	
	/***
	 * 
	 * @param usrid
	 * @return{@literal true} if tutor with the particular id(usrid) exists
	 * else @return{@literal false}
	 * 
	 */
	public boolean tutorExist(String usrid){
		if(usrid!=null) {
			if(registerTutorRepo.existsByEmail(usrid)|| registerTutorRepo.existsByMobile(usrid));
			return true;
		}	
		return false;
	}
	
	
	/***
	 * 
	 * @param usrid
	 * @return RegisterModel if record exist
	 * else @return{@literal null}
	 */
	public RegisterTutorModel getTutor(String usrid) {
		if(tutorExist(usrid)) {
			if(isEmail(usrid)) {
			  return registerTutorRepo.findOneByEmail(usrid);
			}
			else {
			  return registerTutorRepo.findOneByMobile(usrid);
			}
		}
		return null;
	}
	
	/***
	 * 
	 * @param reg
	 * adds tutor record to the register table
	 */
	
	public void addTutor(@RequestBody RegisterTutorDto reg) {
		RegisterTutorModel tutor = new RegisterTutorModel();
		tutor.setFn(reg.getFn());
		tutor.setLn(reg.getLn());
		tutor.setMobile(reg.getMobile());
		tutor.setEmail(reg.getEmail());
		tutor.setPass(Encrypt.encode(reg.getPass()));
		
		registerTutorRepo.save(tutor);
	}

}

