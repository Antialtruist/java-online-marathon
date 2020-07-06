package sprint07.Annotation;

//	Create annotation Review with two string elements: reviewer and date.
//	Element date has default string value today.
//	This annotation can be applied to class when we execute static method review(String className) of class Util and 
//	the result of this method will be printed Class <ClassName> was reviewed <date in format yyyy-mm-dd> by <reviewer> to standard output (console).
//	If the class <className> isn’t annotated we show message: Class <ClassName> isn't marked as Reviewed.
//	If the class was not found we show message: Class <ClassName> was not found.

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Review {
	String reviewer();
	String date() default "today";
}

class Util {
	
	public static void review(String className) {
		try {
			Class<?> cls = Class.forName(className);
			if (cls.isAnnotationPresent(Review.class)) {
				Review review = cls.getAnnotation(Review.class);
				String dateString = review.date();
				LocalDate date = "today".equals(dateString) ? LocalDate.now() : LocalDate.parse(dateString);
				System.out.printf("Class %s was reviewed %s by %s.%n", className, date, review.reviewer());
			} else {
				System.out.printf("Class %s isn't marked as Reviewed%n", className);
			}
		} catch (ClassNotFoundException e) {
			System.out.printf("Class %s was not found%n", className);
		}
	}
}