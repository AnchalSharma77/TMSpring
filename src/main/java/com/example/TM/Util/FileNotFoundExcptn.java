package com.example.TM.Util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileNotFoundExcptn extends RuntimeException {
	public FileNotFoundExcptn(String msg) {
		super(msg);
	}

	public FileNotFoundExcptn(String msg ,Throwable cause) {
		super(msg ,cause);
	}
}
