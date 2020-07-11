package sprint09.DateTimeIOStreamsStreamAPI;

//	Write a method to get the date n-years m-months and k-days after today using new DateTime API.
//	
//	Return the obtained date in the format ISO_LOCAL_DATE.

import java.time.LocalDate;

class Question2 {
	public static String getDateAfterToday(int years, int months, int days) {
		
		return LocalDate.now().plusYears(years).plusMonths(months).plusDays(days).toString();
	
	}
}