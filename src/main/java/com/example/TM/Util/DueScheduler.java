package com.example.TM.Util;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.TM.Model.DueModel;
import com.example.TM.Service.CentralService;

/***
 * 
 * This scheduler updates the due month
 * and due fee of every registered student,
 * on first of every month (at 12:00 AM).
 * 
 * @author Anchal
 * @see https://github.com/AnchalSharma77
 */
@Component
public class DueScheduler extends CentralService{
	
	
	/***
	 * Updates the due months and due fee
	 */
	//cron = "0 12 1 * ?"
	@Scheduled(fixedDelay = 10000000)
	public void schDueUpdate() {
		List<DueModel> dmodel= dueService.getAll();
		if(dmodel.equals(null)) {
			System.out.println(" no record");
		}
		else {
		for(DueModel stu : dmodel) {
			stu.setDm(stu.getDm()+1);
			int dueFee= studentRepo.findOneByMobileAndTid(stu.getMobile(),stu.getTid()).getFee();
			stu.setDueFee(stu.getDueFee()+dueFee);
			dueRepo.save(stu);
		}
		}
	}
		
	
}

