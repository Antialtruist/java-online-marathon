package sprint01.PracticeTaskClassDesignEncapsulationQuiz;

//		Suppose we have the next class:
//		Create a new version of the Employee class that correspond next principles:
//		All class fields must be private.
//		Get and set methods must be used to access the class fields.
//		The class must have a constructors with and without parameters.

class Employee {
    private String fullName;
    private float salary;
    
    public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public Employee(String fullName, float salary) {
		this.fullName = fullName;
		this.salary = salary;
	}
	
	public Employee() {}
}