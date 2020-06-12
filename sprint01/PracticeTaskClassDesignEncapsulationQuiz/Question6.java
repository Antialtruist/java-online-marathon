package sprint01.PracticeTaskClassDesignEncapsulationQuiz;

//		Create a Product class with fields name of type String and price of type double.
//		The Product class must meet the following principles:
//		All class fields must be private.
//		Get and set methods must be used to access the class fields.
//		The class must have a constructors with and without parameters.
//		In the Product class write count() static method that returns count of created objects of Product type.
//		For example, next fragment of code:
//		Will be print to console:

public class Product {
    private static int count;
	private String name;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
		count++;
	}
	
	public Product() {
	    count++;
	}
	
	public static int count() {
		return count;
	}
}