package com.example.TM.ResDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetLinkRes {

	public String message;
	public int responseCode;
	public String email;
}
