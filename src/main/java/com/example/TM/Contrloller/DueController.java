package com.example.TM.Contrloller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ResDto.DueDto;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class DueController extends CentralService{
	
	
	
	/***
	 * 
	 * @param id
	 * @return due month
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/dueMonth")
	public Long getDueMonth(@RequestParam String sid , @RequestParam String eid) { 
		return dueService.getDueData(sid,eid)[0];
	}
	
	/***
	 * 
	 * @param id
	 * @return due fee
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/dueFee")
	public Long getDueFee(@RequestParam String sid,@RequestParam String eid) {
		return dueService.getDueData(sid,eid)[1];
	    
	}

	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/totalDue")
	public Long getTotalDue(@RequestParam String id) {
		return dueService.getDuesSum(id);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/dueById")
	public Long getDueById(String id) {
		return dueService.getDueById(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listDueStudents")
	public List<DueDto> listDueStudents(@RequestParam String id) {
		return dueService.listDueStudents(id);
	}
}


/*
  //@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/due")
	public ResponseEntity<BaseResponse> setDue(@RequestBody String email) {
		BaseResponse res= new BaseResponse();
		try {
		DueModel due =new DueModel();
		//String prevLd = dueService.getLastDate(email);
		due.setLd(currDate.getCurrDate());
		due.setEmail(email);
		due.setDm(dueService.getDueMonth(currDate.getCurrDate()));//setting new due months as the current date(i.e. last date for which fee is paid)
		due.setDueFee(dueService.getDueData(email)[1]);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		catch (Exception e) {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);

		}
	}
 */
