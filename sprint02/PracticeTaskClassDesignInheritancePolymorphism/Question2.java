//	Create interface DrinkReceipt with methods String getName() and DrinkReceipt addComponent(String componentName, int componentCount). 
//	Create interface DrinkPreparation with method Map<String, Integer> makeDrink() to return a drink components. 
//	Create interface Rating with method int getRating().
//	
//	Class Caffee contains fields String name, int rating, Map of ingredients and implements interfaces DrinkReceipt, 
//	DrinkPreparation and Rating. Method makeDrink() prepare coffee with typically components: {Water=100, Arabica=20}. 
//	Espresso and Cappuccino classes extends the Caffee Class and override method makeDrink(). 
//	Espresso caffee has 50 g. of Water. Cappuccino caffee has an additional of 50 g. of Milk.
//	
//	Create a averageRating() method of the MyUtils class to return a Map with coffee name as key and coffee average rating as value.
//	For example, for a given list
//	[Espresso [name=Espresso, rating=8], Cappuccino [name=Cappuccino, rating=10], Espresso [name=Espresso, rating=10], 
//	Cappuccino [name=Cappuccino, rating=6], Caffee [name=Caffee, rating=6]]
//			
//	you should get
//	{Espresso=9.00, Cappuccino=8.00, Caffee=6.00}

import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface DrinkReceipt {
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}

interface DrinkPreparation {
	Map<String, Integer> makeDrink();
}

interface Rating {
	int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
	private String name; 
	private int rating; 
	private Map<String, Integer> ingredients;
	
	public Caffee(String name, int rating) {
		this.name = name;
		this.rating = rating;
		this.ingredients = new HashMap<String, Integer>();
	}
	
	@Override
	public Map<String, Integer> makeDrink() {
		addComponent("Water", 100);
		addComponent("Arabica", 20);
		return ingredients;
	}
	

	@Override
	public DrinkReceipt addComponent(String componentName, int componentCount) {
		ingredients.put(componentName, componentCount);
		return this;
	}
	
	@Override
	public int getRating() {
		return rating;
	}
	
	@Override
	public String getName() {
		return name;
	}
}

class Espresso extends Caffee {
	private Map<String, Integer> ingredients;

	public Espresso(String name, int rating) {
		super(name, rating);
		this.ingredients = new HashMap<String, Integer>();
	}

	@Override
	public Map<String, Integer> makeDrink() {
		addComponent("Water", 50);
		addComponent("Arabica", 20);
		return ingredients;
	}
	
	@Override
	public DrinkReceipt addComponent(String componentName, int componentCount) {
		ingredients.put(componentName, componentCount);
		return this;
	}
    
}

class Cappuccino extends Caffee {
	private Map<String, Integer> ingredients;

	public Cappuccino(String name, int rating) {
		super(name, rating);
		this.ingredients = new HashMap<String, Integer>();
	}

	@Override
	public Map<String, Integer> makeDrink() {
		super.makeDrink();
		addComponent("Milk", 50);
		
		return ingredients;
	}
	
	@Override
	public DrinkReceipt addComponent(String componentName, int componentCount) {
		ingredients.put(componentName, componentCount);
		return this;
	}
    
}

class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
    	double amountOfCaffee = 0;
    	double amountOfEspresso = 0;
        double amountOfCappuccino = 0;

        int numCaffee = 0;
        int numEspresso = 0;
        int numCappuccino = 0;

        for (Caffee c : coffees){
        	if(c instanceof Espresso){
        		amountOfEspresso += c.getRating();
        		numEspresso++;
        	}
        	else if(c instanceof Cappuccino){
        		amountOfCappuccino += c.getRating();
                numCappuccino++;
            }
            else if(c != null){
                amountOfCaffee += c.getRating();
                numCaffee++;
            }
        }

        Map<String, Double> result = new HashMap<>();
        if(numCaffee > 0){
        	result.put("Caffee", (amountOfCaffee / numCaffee));
        }
        if(numEspresso > 0) {
            result.put("Espresso", (amountOfEspresso / numEspresso));
        }
        if(numCappuccino > 0) {
            result.put("Cappuccino", (amountOfCappuccino / numCappuccino));
        }

        return result;
    }
}