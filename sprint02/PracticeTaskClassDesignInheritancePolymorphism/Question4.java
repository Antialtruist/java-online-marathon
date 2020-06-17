//	Create classes Employee (fields String name, int experience and BigDecimal basePayment) and Manager (field double coefficient) 
//	with methods which return the general working experience and payment for work done.
//			
//	A getter methods of class Employee return value of all fields: getName(), getExperience() and getPayment().
//			
//	Classes Manager is derived from class Employee and override getPayment() method to return multiplication of a coefficient and 
//	base payment.
//	
//	Create a largestEmployees() method of the MyUtils class to return a List of unique employees with maximal working experience and 
//	payment without duplicate objects.
//	
//	The original list must be unchanged.
//	
//	For example, for a given list
//	 [Employee [name=Ivan, experience=10, basePayment=3000.00], 
//	 Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5],  
//	 Employee [name=Stepan, experience=8, basePayment=4000.00], 
//	 Employee [name=Andriy, experience=7, basePayment=3500.00], 
//	 Employee [name=Ihor, experience=5, basePayment=4500.00], 
//	 Manager [name=Vasyl, experience=8, basePayment=2000.00, coefficient=2.0]]
//			 
//	you should get
//	[Employee [name=Ivan, experience=10, basePayment=3000.00], 
//	 Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5], 
//	 Employee [name=Ihor, experience=5, basePayment=4500.00]].

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment){
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public String getName(){
        return name;
    }

    public int getExperience(){
        return experience;
    }

    public BigDecimal getPayment(){
        return basePayment;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePayment == null) ? 0 : basePayment.hashCode());
		result = prime * result + experience;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (basePayment == null) {
			if (other.basePayment != null)
				return false;
		} else if (!basePayment.equals(other.basePayment))
			return false;
		if (experience != other.experience)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

class Manager extends Employee {
    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient){
    	super(name, experience, basePayment);
    	this.coefficient = coefficient;
    }
    
    public String getName(){
        return super.getName();
    }

    public int getExperience(){
        return super.getExperience();
    }

    public BigDecimal getPayment(){
        return new BigDecimal(coefficient).multiply(super.getPayment());
    }

    public double getCoefficient(){
        return coefficient;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(coefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		if (Double.doubleToLongBits(coefficient) != Double.doubleToLongBits(other.coefficient))
			return false;
		return true;
	}
}

class MyUtils {
	public List<Employee> largestEmployees(List<Employee> workers) {

        Comparator<Employee> maxExperience = new Comparator<Employee>() {
            
        	@Override
            public int compare(Employee o1, Employee o2) {
                return o2.getExperience() - o1.getExperience();
            }
        };

       Comparator<Employee> maxSalary = new Comparator<Employee>() {
           
    	   @Override
           public int compare(Employee o1, Employee o2) {
               return o2.getPayment().compareTo(o1.getPayment());
           }
       };

       List<Employee> employees = new ArrayList<>(new ArrayList<>(workers));
       List<Employee> resultList = new ArrayList<>();

       employees.sort(maxExperience);

       for (Employee e : employees) {
            if(e != null && e.getExperience() == employees.get(0).getExperience())
                resultList.add(e);
        }

       employees.sort(maxSalary);
       for (Employee e : employees) {
            if(e != null && e.getPayment().compareTo(employees.get(0).getPayment()) == 0)
            	resultList.add(e);
       }
       return new ArrayList<>(new LinkedHashSet<>(resultList));
    }
}