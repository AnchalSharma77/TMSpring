package com.example.TM.ResDto;

import java.util.List;

import com.example.TM.Model.ScheduleClassModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduledClassRes {

	int responseCode;
	String Message;
	List<ScheduleClassModel> allClasses;
}
