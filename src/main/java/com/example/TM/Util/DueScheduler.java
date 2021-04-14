package com.example.TM.Util;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.TM.Model.DueModel;
import com.example.TM.Service.CentralService;


@Component
public class DueScheduler extends CentralService{
	
	/***
	 * Updates the due months and due fee
	 */
	@Scheduled(fixedDelay=1000000)
	public void schDueUpdate() {
		List<DueModel> dmodel= dueService.getAll();
		if(dmodel.equals(null)) {
			System.out.println(" no record");
		}
		else {
		for(DueModel stu : dmodel) {
			stu.setDm(stu.getDm()+1);
			int dueFee= studentRepo.findOneByMobile(stu.getMobile()).getFee();
			stu.setDueFee(stu.getDueFee()+dueFee);
			dueRepo.save(stu);
		}
		}
	}
		
	
}

