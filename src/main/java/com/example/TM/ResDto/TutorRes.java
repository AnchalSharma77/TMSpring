package com.example.TM.ResDto;

import java.util.List;

import com.example.TM.Model.RegisterTutorModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorRes {
	public String message;
	public int responseCode;
	public List<RegisterTutorModel> tutorList;

}
