package com.example.TM.Service;

import org.springframework.stereotype.Service;

import com.example.TM.Model.DueModel;
import com.example.TM.ReqDto.PayReqDto;
import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;

@Service
public class PaymentService extends CentralService {

	/***
	 * <p>
	 * Update the <code>Fee</code> of the specified {@literal id}
	 * as per the number of months and {@literal payFor} , provided in
	 * {@literal payReq}  , against particular <code>eid</code> (id with which
	 * the particular tutor is registered).
	 * </p>
	 * @param payReq must not be {@literal null}.
	 * @param eid must not be {@literal null}.
	 * @throws Exception
	 */
	public void payFee( PayReqDto payReq ,String eid) throws Exception {
		String id =payReq.getId();
		CurrDate dt = new CurrDate();
		DueModel dueData = new DueModel();
		Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(eid)).getTid();
		
		//dueData =registerTutorService.isEmail(id)? dueRepo.findOneByEmail(id):dueRepo.findOneByMobile(id);
		if(dueRepo.existsByEmailAndTid(id, Tid)) {
			dueData=dueRepo.findOneByEmailAndTid(id,Tid);
			Long totalDm=dueData.getDm();
			Long payFor=payReq.getPayFor();
			if(totalDm>=payFor) {
			Long updateDueMonth = totalDm-payFor;
			int fee = studentRepo.findOneByEmailAndTid(id,Tid).getFee();
			Long updateFee = (updateDueMonth*fee);
			dueData.setDm(updateDueMonth);
			dueData.setLd(dt.getCurrDate());
			dueData.setDueFee(updateFee);
			dueRepo.save(dueData);
			}
			else
				throw new Exception("error : payFor>=totalDm ");
		}
	}
}
