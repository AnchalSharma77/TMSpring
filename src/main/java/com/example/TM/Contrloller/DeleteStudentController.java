package com.example.TM.Contrloller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class DeleteStudentController extends CentralService {

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteStudent")
	public ResponseEntity<BaseResponse> deleteStudennt(@RequestParam String id ,@RequestParam String tid) throws Exception {
		try {
			return deleteStudentService.deleteStudent(id, tid);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
