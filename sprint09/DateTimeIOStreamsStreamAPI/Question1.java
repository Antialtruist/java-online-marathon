package sprint09.DateTimeIOStreamsStreamAPI;

//	Write a method to check if a year is a leap year or not, using for this the LocalDate class.
//	
//	If a year is leap then method should return true, otherwise - false.

import java.time.LocalDate;

class Question1 {

	public static boolean isLeapYear(int year) {

		return LocalDate.of(year, 1, 1).isLeapYear();

	}
}