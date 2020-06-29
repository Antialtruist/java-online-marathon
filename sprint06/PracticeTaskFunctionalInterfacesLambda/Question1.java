package sprint06.PracticeTaskFunctionalInterfacesLambda;

//	Implement a static method getCount(...) that takes an array of integers as the first parameter. 
//	The second parameter is a functional interface that works with integers and defines a some condition.
//	
//	The method should return the count of elements in the array that satisfy the condition defined by the second argument.

import java.util.function.Predicate;

class MyUtils {

    public static int getCount(int[] integers, Predicate<Integer> condition) {
    	int countOfElements = 0;
    	for(int i : integers) {
    		if(condition.test(i)) {
    			countOfElements++;
    		}
    	}
        return countOfElements;
    }
}