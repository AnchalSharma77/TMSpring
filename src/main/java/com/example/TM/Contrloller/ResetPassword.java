package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ResDto.BaseResponse;
import com.example.TM.ResDto.ResetPassDto;
import com.example.TM.Service.CentralService;


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class ResetPassword extends CentralService {


	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/resetPassLink")
	public ResponseEntity<BaseResponse> resetLink(@RequestParam String id) throws Exception{
		try {
			 return resetPasswordService.resetLink(id);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
		
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/resetPass")
	public ResponseEntity<BaseResponse>  resetPass (@RequestBody ResetPassDto resetpass) throws Exception{
		try {
			 return resetPasswordService.resetPass(resetpass);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
		
	}
}
