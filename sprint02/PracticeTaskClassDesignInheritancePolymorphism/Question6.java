//	Please create class Shape with abstract method to calculate area of figure and field name. 
//	Replace code in method getArea() according to principles of polymorphism i.e. 
//	Circle and Rectangle classes extends Shape class and override double getArea() method. 
//	Develop maxAreas() method of the MyUtils class to return a List with instances of maximum area.
//	The original list must be unchanged.
//	For example, for a given list
//	[Circle [radius=2.00], Rectangle [height=2.00, width=3.00], Circle [radius=1.00], Rectangle [height=3.00, width=2.00],  Circle [radius=0.50], Rectangle [height=1.00, width=2.00]]
//	you should get
//	[Circle [radius=2.00], Rectangle [height=2.00, width=3.00], Rectangle [height=3.00, width=2.00]]


import java.util.*;

abstract class Shape {
    private String name;

    public abstract double getArea();

    public Shape(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Shape other = (Shape) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

class Circle extends Shape {
    private double radius;

    public double getArea(){
        return Math.PI * radius * radius;
    }

    public Circle(String name, double radius){
        super(name);
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(radius);
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
		Circle other = (Circle) obj;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}
}

class Rectangle extends Shape {
    private double height;
    private double width;

    public double getArea(){
        return height * width;
    }

    public Rectangle(String name, double height, double width){
        super(name);
        this.height = height;
        this.width = width;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
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
		Rectangle other = (Rectangle) obj;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}
}

class MyUtils {
    public List<Shape> maxAreas(List<Shape> shapes) {
        double maxCircle = 0;
        double maxRectangle = 0;

        for(Shape shape : shapes){
            if(shape instanceof Circle){
                if(shape.getArea() > maxCircle){
                    maxCircle = shape.getArea();
                }
            }
            else{
                if(shape.getArea() > maxRectangle){
                    maxRectangle = shape.getArea();
                }
            }
        }

        List<Shape> result = new ArrayList<>();
        for(Shape shape : shapes){
            if(!result.contains(shape)) {
                if (shape instanceof Circle) {
                    if (shape.getArea() == maxCircle) {
                        result.add(shape);
                    }
                } else {
                    if (shape.getArea() == maxRectangle) {
                        result.add(shape);
                    }
                }
            }
        }

        return result;
    }
}