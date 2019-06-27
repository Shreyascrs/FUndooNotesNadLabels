package com.bridgeit.fundoo.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
	
	public static String todayDate()
	{
		LocalDateTime time=LocalDateTime.now();
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
		String datetime=time.format(format);
		return datetime;
		
	}
}
