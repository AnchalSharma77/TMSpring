package com.example.TM.ReqDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStuReq {
	
	private Long mobile;
	private String email;
	private String name;
	private String std;
	
}
