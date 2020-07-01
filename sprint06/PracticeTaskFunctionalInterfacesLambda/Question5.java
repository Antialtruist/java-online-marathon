package sprint06.PracticeTaskFunctionalInterfacesLambda;

//	Implement a static method getPredicateFromSet(...) in MyUtils class.
//	
//	The getPredicateFromSet method should take a Set of predicates working with integers as a parameter and 
//	return a predicate with the functionality of all predicates in the set invoked and connected by logical AND.

import java.util.function.Predicate;
import java.util.Set;

class MyUtils{
	public static Predicate getPredicateFromSet(Set<Predicate<Integer>> predicate) {
        return predicate.stream().reduce(x -> true, Predicate::and);
    }
}