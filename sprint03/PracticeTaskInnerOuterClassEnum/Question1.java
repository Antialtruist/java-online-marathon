package sprint03.PracticeTaskInnerOuterClassEnum;

//	Create public static inner class named PizzaBuilder inside Pizza class that correspond the next class diagram:
//	Inside the cook method create and return an instance of Pizza class with your at least three favorite ingredients.	

class Pizza {
    private String cheese;
    private String meat;
    private String seafood;
    private String vegetable;
    private String mushroom;
   
	private Pizza() {
    }

    public static PizzaBuilder base() {
        return new PizzaBuilder();
    }

    public static class PizzaBuilder {
        private Pizza pizza;

        private PizzaBuilder() {
        	pizza = new Pizza();
        }

        public PizzaBuilder addCheese(String cheese) {
            pizza.cheese = cheese;
            return this;
        }

        public PizzaBuilder addMeat(String meat) {
            pizza.meat = meat;
            return this;
        }

        public PizzaBuilder addSeafood(String seafood) {
            pizza.seafood = seafood;
            return this;
        }

        public PizzaBuilder addVegetable(String vegetable) {
            pizza.vegetable = vegetable;
            return this;
        }

        public PizzaBuilder addMushroom(String mushroom) {
            pizza.mushroom = mushroom;
            return this;
        }

        public Pizza build() {
            return pizza;
        }
    }
    
    public String getCheese() {
		return cheese;
	}

	public void setCheese(String cheese) {
		this.cheese = cheese;
	}

	public String getMeat() {
		return meat;
	}

	public void setMeat(String meat) {
		this.meat = meat;
	}

	public String getSeafood() {
		return seafood;
	}

	public void setSeafood(String seafood) {
		this.seafood = seafood;
	}

	public String getVegetable() {
		return vegetable;
	}

	public void setVegetable(String vegetable) {
		this.vegetable = vegetable;
	}

	public String getMushroom() {
		return mushroom;
	}

	public void setMushroom(String mushroom) {
		this.mushroom = mushroom;
	}
}

class Oven {
    public static Pizza cook() {
        return Pizza.base().addCheese("Brie").addMeat("Chicken").addMushroom("Champignons").build();
    }
}
