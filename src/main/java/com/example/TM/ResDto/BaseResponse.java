package com.example.TM.ResDto;

public class BaseResponse {
	public String resMsg =null;
	public int resCode= 200;
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public int getResCode() {
		return resCode;
	}
	public void setResCode(int resCode) {
		this.resCode = resCode;
	}
}
