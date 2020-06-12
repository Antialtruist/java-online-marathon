package sprint01.PracticeTaskClassDesignEncapsulationQuiz;

//		Suppose we have the next class:
//		1. Create an instances of Employee class named 'emp1' and 'emp2'.
//		2. Set not null values for 'fullName' and 'salary' properties.
//		3. Create array of Employee type with name 'employees' and add two objects created before.
//		4. Create variable with name 'employeesInfo' of String type.
//		5. Using a loop, iterrate across array and write to variable named 'employeesInfo' info about each employee in next fommat:

Employee emp1 = new Employee();
Employee emp2 = new Employee();

emp1.fullName = "Demis";
emp1.salary = 1000.0f;
	
emp2.fullName = "Roza";
emp2.salary = 500.0f;
	
Employee [] employees = {emp1, emp2};
	
String employeesInfo = "[{fullName: \"";

for (int i = 0; i < employees.length; i++) {
    	
if(i != employees.length-1) { 
employeesInfo += employees[i].fullName + "\", salary: " + employees[i].salary + "}, {fullName: \"";
} else {
employeesInfo += employees[i].fullName + "\", salary: " + employees[i].salary;
}
}

employeesInfo += "}]";
