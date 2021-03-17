package com.example.TM.ReqDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStuReq {
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStd() {
		return std;
	}
	public void setStd(String std) {
		this.std = std;
	}
	private Long phone;
	private String name;
	private String std;
}
