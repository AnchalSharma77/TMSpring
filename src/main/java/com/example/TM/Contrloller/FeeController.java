package com.example.TM.Contrloller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.FeeModel;
import com.example.TM.Repo.FeeRepo;
import com.example.TM.ReqDto.FeeDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.ResopnseCodes;


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class FeeController extends CentralService {

	@Autowired
	FeeRepo feeRepo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/fee")
	public FeeModel getFee(@RequestParam int std) {
		return feeRepo.findOneByStd(std);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/fee")
	public ResponseEntity<BaseResponse> addFee(@RequestBody FeeDto fee,@RequestParam String id) {
		BaseResponse res= new BaseResponse();
		try {
		//feeRepo.save(fee);
		feeService.addFee(fee, id);
		res.setResCode(new ResopnseCodes().ok);
		res.setResMsg(new ResopnseCodes().okMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch(Exception e) {
		res.setResCode(new ResopnseCodes().invalid);
		res.setResMsg(new ResopnseCodes().invalidMsg);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/feeUpdate")
	public void updateFee(@RequestBody FeeModel fee) {
		FeeModel f= feeRepo.findOneByStd(fee.getStd());
		f.setFee(fee.getFee());
	}
}
