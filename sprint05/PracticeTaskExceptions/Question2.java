package sprint05.PracticeTaskExceptions;

//	Create a class Plant, which includes private fields String name, Color color and Type type, and constructor with three String parameters (String type, String color, String name) where these fields are initialized. Create getters for all fields.
//	Color and Type are Enum. 
//	Color contains White, Red, Blue entries.
//	Type contains Rare and Ordinary entries.
//	Override the method toString( ) for Plant class which returns result formatted like following: {type: Rare, color: Red, name: MyPlant}
//	Create classes ColorException and TypeException as derived from Exception. Both classes should have a constructor with one String parameter and pass this parameter to the base class.
//	The constructor of Plant should throw a corresponding exception whenever an inappropriate parameter is passed.

enum Color {
	WHITE("White"), RED("Red"), BLUE("Blue");
	
	private String string;
	
	Color (String name) {
		string = name;
	}
	
	@Override
	  public String toString() {
	   return string;
	  }
}

enum Type {
	RARE("Rare"), ORDINARY("Ordinary");
	
	private String string;
	
	Type (String name) {
		string = name;
	}
	
	@Override
	  public String toString() {
	   return string;
	  }
}

class ColorException extends Exception {
	public ColorException(String name) {
		super(name);
	}
}

class TypeException extends Exception {
	public TypeException(String name) {
		super(name);
	}
}

class Plant{
	private Type type;
	private Color color;
    private String name;

    public Plant(String type, String color, String name) throws ColorException, TypeException {
    	
    	try{
    		this.type = Type.valueOf(type.toUpperCase());
    	} catch (IllegalArgumentException e) {
    		throw new TypeException("Invalid value " + type + " for field type");
    	}
    	
    	try{
    		this.color = Color.valueOf(color.toUpperCase());
    	} catch (IllegalArgumentException e) {
    		throw new ColorException("Invalid value " + color + " for field color");
    	}
    	
    	this.name = name;
    }
    
    public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}
	
	@Override
    public String toString() {
        return "{type: " + type + ", color: " + color + ", name: " + name + "}";
    }
}