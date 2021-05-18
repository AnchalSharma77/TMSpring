package com.example.TM.Util;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.TM.Model.ScheduleClassModel;
import com.example.TM.Service.CentralService;

@Component
public class ClassScheduler extends CentralService {

	@Scheduled(fixedDelay = 100000)
	public void removeExpierdClsses() {
		String date = new CurrDate().getRevCurrDate();
		List<ScheduleClassModel> cls= scheduleClassRepo.findByDate(date);
		for(ScheduleClassModel tmp:cls) {
			scheduleClassRepo.delete(tmp);
		}
	}
}
