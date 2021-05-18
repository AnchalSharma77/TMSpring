package com.example.TM.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CurrDate {
	
	public String getCurrDate() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	   LocalDateTime now = LocalDateTime.now(); 
	   String date=""+dtf.format(now);
	   return date;
	}
	
	public String getRevCurrDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now = LocalDateTime.now(); 
		   String date=""+dtf.format(now);
		   return date;
		}
	
	/**
	 * 
	 * @param date in {@code String}
	 * @return  Day of month in {@code String} format
	 */
	public String getDay(String date) {
		 SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
		 String currentDay = formatNowDay.format(date);
		 return currentDay;
	}
	
	
	public Long getDateDiff(String startDate ) {
	
		 String currDate = getCurrDate();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 long diff=0L;
		    try {
	
		    	  Date firstDate = sdf.parse(startDate);
		    	  Date secondDate = sdf.parse(currDate);
				  long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
				  diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				  System.out.println(diff);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		   return diff;
		 
		
	}

}
