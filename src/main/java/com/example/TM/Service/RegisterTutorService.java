package com.example.TM.Service;

import java.util.List;
import java.util.regex.Pattern;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ReqDto.RegisterTutorDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.ResDto.TutorRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;


@Service
public class RegisterTutorService extends CentralService {
		
	/***
	 * 
	 * @param usrid must not be {@code null}
	 * @return {@literal true } if email is entered as usrid
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
	 * @param usrid must not be {@literal null}.
	 * @return {@literal true} if tutor with the particular id(usrid) exists
	 * else {@literal false}
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
	 * <p>
	 * Find for the tutor registered with <code>usrid</code>
	 * </p>
	 * @param usrid must not be {@literal null}.
	 * @return RegisterModel if record exist
	 * else {@literal null}
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
	 * <p>
	 *  adds tutor record to the register tutor table
	 * </p>
	 * @param reg must not be {@code null}
	 * @throws Exception 
	 *
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/registertutor")
	public ResponseEntity<BaseResponse> addTutor(RegisterTutorDto reg) throws Exception {
		BaseResponse res= new BaseResponse();
		try {
			RegisterTutorModel tutor = new RegisterTutorModel();
			tutor.setFn(reg.getFn());
			tutor.setLn(reg.getLn());
			tutor.setMobile(reg.getMobile());
			tutor.setEmail(reg.getEmail());
			tutor.setPass(Encrypt.encode(reg.getPass()));
			tutor.setDescription(reg.getDescription());
			registerTutorRepo.save(tutor);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
			
		}
		catch(Exception e) {
		throw new Exception(e.getMessage());
		}
		
	}
	
	
	/***
	 * <p>
	 * List all the registered tutors
	 * </p>
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
	public ResponseEntity<TutorRes> getAllTutors() throws ApplicationException{
		TutorRes res = new TutorRes();
		HttpStatus httpSts = HttpStatus.OK;
		try {
			List<RegisterTutorModel> tutorList = (List<RegisterTutorModel>)registerTutorRepo.findAll();
		if (tutorList == null) {
			res.setMessage(new ResopnseCodes().notFoundMsg);
			res.setResponseCode(new ResopnseCodes().notFound);
		} else {
			res.setTutorList(tutorList);
			res.setResponseCode(new ResopnseCodes().ok);
			res.setMessage(new ResopnseCodes().okMsg);
		}
		return ResponseEntity.status(httpSts).body(res);
		}catch (Exception e) {
			throw new ApplicationException(e.getMessage(), null);
		}

		
	}


}

