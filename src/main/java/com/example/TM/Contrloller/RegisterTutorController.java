package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ReqDto.RegisterTutorDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.ResDto.TutorRes;
import com.example.TM.Service.CentralService;


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class RegisterTutorController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/registertutor")
	public RegisterTutorModel getTutor(@RequestParam String id) {
		return registerTutorService.getTutor(id);
	}
	
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllTutors")
	public ResponseEntity<TutorRes> getAllTutors() throws Exception  {
		 try {
			 return registerTutorService.getAllTutors();

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/registertutor")
	public ResponseEntity<BaseResponse> addTutor(@RequestBody RegisterTutorDto reg) throws Exception {
		try {
		 return registerTutorService.addTutor(reg);
				}
		catch(Exception e) {
		throw new Exception(e.getMessage());
		}
		
	}

}
