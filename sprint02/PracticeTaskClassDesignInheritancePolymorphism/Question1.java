//	We know that adult  doesn't have childIDNumber.
//	Child doesn't have passportNumber.
//	Create a public constructor in each class to initialize all their fields (make the first parameter of type int).

class Person {
	int age;
    String healthInfo;
    String name;
    
	public Person(int age, String healthInfo, String name) {
		this.age = age;
		this.healthInfo = healthInfo;
		this.name = name;
	}
	
	String getHealthStatus(){ return name +" " + healthInfo; }
}

class Child extends Person{
	String childIDNumber;
	
	public Child(int age, String healthInfo, String name, String childIDNumber) {
		super(age, healthInfo, name);
		this.childIDNumber = childIDNumber;
	}
	
    String getHealthStatus(){ return name +" " + healthInfo; }
}

class Adult extends Person{
	String passportNumber;  
    
	public Adult(int age, String healthInfo, String name, String passportNumber) {
		super(age, healthInfo, name);
		this.passportNumber = passportNumber;
	}
    
    
    String getHealthStatus(){ return name +" " + healthInfo; }
}
