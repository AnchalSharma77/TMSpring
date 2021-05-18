package com.example.TM.ResDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DueRes {

	public String message;
	public int responseCode;
	private Long dm;
	private Long dueFee;
}
