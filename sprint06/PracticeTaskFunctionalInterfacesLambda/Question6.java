package sprint06.PracticeTaskFunctionalInterfacesLambda;

//	Implement a static method findMaxByCondition(...) of MyUtils class that takes List of integers as a first parameter and 
//	predicate as a second and returns the max value from the list that satisfies the condition of the predicate.
//	
//	Also, implement getFilterdValue(...) method of User class. The getFilteredValue(...) method should be able 
//	to take MyUtils.findMaxByCondition(...) as a first parameter and Predicate as a second. 
//	This method should return an integer value, evaluated from User's field values using the first and second parameters of getFilterdValue(...) method.
//	
//	One more method that needs to be implemented in User class is getMaxValueByCondition(...). This method has one Predicate parameter. 
//	The implementation should call getFilterdValue(...) method with MyUtils.findMaxByCondition(...) as a first parameter and passed along Predicate as a second.

import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;

class MyUtils{
    public static int findMaxByCondition(List<Integer> numbers, Predicate<Integer> pr) {
    	return numbers.stream().filter(num -> pr.test(num)).max(Integer::compareTo).get();
    }
}

class User {
    public final List<Integer> values = new ArrayList<Integer>();   
    
    int getFilterdValue(BiFunction<List<Integer>, Predicate<Integer>, Integer> max, Predicate<Integer> predicate) {
    	return max.apply(values, predicate);
    }
    
    int getMaxValueByCondition(Predicate<Integer> predicate) {
    	return getFilterdValue(MyUtils::findMaxByCondition, predicate);
    }
}