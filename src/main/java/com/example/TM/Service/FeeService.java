package com.example.TM.Service;

import org.springframework.stereotype.Service;

import com.example.TM.Model.FeeModel;
import com.example.TM.ReqDto.FeeDto;
import com.example.TM.Util.Encrypt;

@Service
public class FeeService extends CentralService {
	/***
	 * <p>
	 * {@literal fee} object contain std and its corresponding fee.<br>
	 * {@literal email} is the unique id , with which the particular tutor is registered .
	 * @param fee must not be {@literal null}.
	 * </p>
	 * @param email must not be {@literal null}.
	 */
	public void addFee( FeeDto fee , String email) {
		FeeModel f = new FeeModel();
		int std=fee.getStd();
		int amount=fee.getFee();
		Long tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(email)).getTid();
		if(feeRepo.existsOneByStdAndTid(std,tid)) {
			f=feeRepo.findOneByStdAndTid(std,tid);
			f.setFee(amount);
		}
		else {
			f.setStd(std);
			f.setFee(amount);
			f.setTid(tid);
		}
		feeRepo.save(f);
	}

}
