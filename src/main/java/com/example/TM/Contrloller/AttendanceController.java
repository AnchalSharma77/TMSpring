package com.example.TM.Contrloller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.AttendanceModel;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.ResopnseCodes;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class AttendanceController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/markAttendance")
	public ResponseEntity<BaseResponse>markAttendance(@RequestBody List<AttendanceModel> data , @RequestParam String id) {
		BaseResponse res= new BaseResponse();
		try {
			attendanceService.markAttendance(data, id);
		
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		
		}catch (Exception e) {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		
		
		
	}

}
