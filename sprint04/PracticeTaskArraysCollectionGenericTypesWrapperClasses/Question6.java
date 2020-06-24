package sprint04.PracticeTaskArraysCollectionGenericTypesWrapperClasses;

//	Create classes with name PersonComparator, EmployeeComparator, DeveloperComparator that implement the Comparator<Type> generic interface.
//	
//	In the Utility class create public static method named sortPeople(...) that takes an array of Person type and derivative from it types, 
//	and comparator as input, and returns the value of void type.
//	
//	Also, as second argument the method sortPeople(...) can takes generic comparator for elements of Object type.
//	
//	The sortPeople(...) method should sorted records by ascending. At first by name, then by age, then by salary, and then by Level (JUNIOR < MIDDLE < SENIOR)

import java.util.*;

class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int comparingNames = o1.getName().compareTo(o2.getName());
        if(comparingNames == 0){
            if(o1.getAge() == o2.getAge()){
                return 0;
            }
            if(o1.getAge() > o2.getAge()){
                return 1;
            }
            return -1;
        }
        return comparingNames;
    }
}

class EmployeeComparator implements Comparator<Employee>{
    @Override
    public int compare(Employee o1, Employee o2) {
        int comparingPerson = new PersonComparator().compare(o1, o2);
        if(comparingPerson == 0){
            if(o1.getSalary() == o2.getSalary()){
                return 0;
            }
            if(o2.getSalary() > o2.getSalary()){
                return 1;
            }
            return -1;
        }
        return comparingPerson;
    }
}

class DeveloperComparator implements Comparator<Developer>{
    @Override
    public int compare(Developer o1, Developer o2) {
        int comparingEmployee = new EmployeeComparator().compare(o1, o2);
        if(comparingEmployee == 0){
            return o1.getLevel().compareTo(o2.getLevel());
        }
        return comparingEmployee;
    }
}

class Utility {
    public static<T extends Person> void sortPeople(T[] people, Comparator<? super T> comparator){
        Arrays.sort(people, comparator);
    }
}