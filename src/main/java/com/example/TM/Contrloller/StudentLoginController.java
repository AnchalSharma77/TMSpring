package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.LoginStudentDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class StudentLoginController extends CentralService{
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/getStudentLoginOtp")
	public ResponseEntity<BaseResponse> sendLoginOtp(@RequestParam("id") String id) throws Exception {
		try {
            return studentLoginService.sendLoginOtp(id);

        } catch (Exception e) {
        	 throw new Exception(e.getMessage());
        }
	}
	
	
				
			

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/studentLogin")
	public ResponseEntity<BaseResponse> loginStudent(@RequestBody LoginStudentDto stu) throws Exception {
		try {
            return studentLoginService.loginStudent(stu);

        } catch (Exception e) {
        	 throw new Exception(e.getMessage());
        }
	}
	
}
