package com.example.TM.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrDate {
	
	public String getCurrDate() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	   LocalDateTime now = LocalDateTime.now(); 
	   String date=" "+dtf.format(now);
	   return date;
	}

}
