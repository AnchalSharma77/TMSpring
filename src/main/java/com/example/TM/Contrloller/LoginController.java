package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.LoginReqDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class LoginController extends CentralService{
//	@Autowired
//	LoginService loginService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> login(@RequestBody LoginReqDto tutor) throws Exception {
		try {
		return loginService.login(tutor);
		}
		catch(Exception e) {
		throw new Exception();
		}
		
			
	}
	
	
	
}
