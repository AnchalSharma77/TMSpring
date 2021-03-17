package com.example.TM.Contrloller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.FeeModel;
import com.example.TM.Model.StudentModel;
import com.example.TM.Repo.FeeRepo;


@RestController
@RequestMapping(value="/app",produces =" application/json")
public class FeeController {

	
	@Autowired
	FeeRepo feeRepo;
	
	
	@GetMapping("/fee")
	public Optional<FeeModel> getFee(@RequestParam int id) {
		return feeRepo.findById(id);
	}
	
	@PostMapping("/fee")
	public FeeModel addFee(@RequestBody FeeModel fee) {
		feeRepo.save(fee);
		return fee;
	}
}
