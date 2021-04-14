package com.example.TM.Contrloller;


import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class FeeController {

	@Autowired
	FeeRepo feeRepo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/fee")
	public FeeModel getFee(@RequestParam int std) {
		return feeRepo.findOneByStd(std);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/fee")
	public FeeModel addFee(@RequestBody FeeModel fee) {
		feeRepo.save(fee);
		return fee;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/feeUpdate")
	public void updateFee(@RequestBody FeeModel fee) {
		FeeModel f= feeRepo.findOneByStd(fee.getStd());
		f.setFee(fee.getFee());
	}
}
