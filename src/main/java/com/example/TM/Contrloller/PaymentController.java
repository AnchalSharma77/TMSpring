package com.example.TM.Contrloller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.PayReqDto;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.ResopnseCodes;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class PaymentController extends CentralService{
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pay")
	public ResponseEntity<BaseResponse> payFee(@RequestBody PayReqDto payReq ,@RequestParam String eid) {
		
		BaseResponse res= new BaseResponse();
		
		try {
			paymentService.payFee(payReq,eid);
			res.setResCode(new ResopnseCodes().ok);
			res.setResMsg(new ResopnseCodes().okMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch(Exception e) {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
	}


}
