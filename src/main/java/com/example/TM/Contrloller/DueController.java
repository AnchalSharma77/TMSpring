package com.example.TM.Contrloller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.DueModel;
import com.example.TM.ReqDto.PayReqDto;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.CurrDate;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class DueController extends CentralService{
	
	

	CurrDate currDate ;
	
	/***
	 * 
	 * @param id
	 * @return due month
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/dueMonth")
	public Long getDueMonth(@RequestParam String id) {
	     System.out.println("API Hit........id : "+id); 
		return dueService.getDueData(id)[0];
	    
	}
	
	/***
	 * 
	 * @param id
	 * @return due fee
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/dueFee")
	public Long getDueFee(@RequestParam String id) {
		return dueService.getDueData(id)[1];
	    
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/due")
	public void setDue(@RequestBody String email) {
		DueModel due =new DueModel();
		//String prevLd = dueService.getLastDate(email);
		due.setLd(currDate.getCurrDate());//TODO: check its correctness
		due.setEmail(email);
		due.setDm(dueService.getDueMonth(currDate.getCurrDate()));//setting new due months as the current date(i.e. last date for which fee is paid)
		due.setDueFee(dueService.getDueData(email)[1]);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/totalDue")
	public Long getTotalDue() {
		return dueService.getDuesSum();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/totalDue")
	public void payFee(@RequestBody PayReqDto payReq) {
		String id =payReq.getId();
		CurrDate dt = new CurrDate();
		DueModel dueData = new DueModel();
		dueData =(!id.equalsIgnoreCase(""))? dueRepo.findOneByEmail(id):findOneByMobile(id);
		Long updateDueMonth = dueData.getDm()-payReq.getMonth();
		int fee = studentRepo.findOneByEmail(id).getFee();
		Long updateFee = dueData.getDueFee()-(updateDueMonth*fee);
		dueData.setDm(updateDueMonth);
		dueData.setLd(dt.getCurrDate());
		dueData.setDueFee(updateFee);
		dueRepo.save(dueData);
	}

	private DueModel findOneByMobile(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
