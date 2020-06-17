//	Create classes Square and Rectang with method (double getPerimeter()) for calculating of perimeter.
//	Find solution for avoiding of duplicate code.
//	Create a double sumPerimeter(List<?> firures) method of the MyUtils class to return a sum perimeters of all figures.
//	For example, for a given list
//	[[Square [width=4.00], Square [width=5.00], Rectang [height=2.00, width=3.00]]
//	you should get 46

import java.util.*;

class Square{
    private double width;

    public Square(double width){
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getPerimeter(){
        return width * 4;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Square other = (Square) obj;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}
}

class Rectang extends Square{
    private double height;
    public Rectang(double height, double width) {
        super(width);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getPerimeter() {
        return getWidth() * 2 + height * 2;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(height);
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
		Rectang other = (Rectang) obj;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		return true;
	}
}

class MyUtils {
	public double sumPerimeter(List<Square> firures) {
        
        double sum = 0;
        
        for(Square sq : firures){
            if(sq != null){
                sum += sq.getPerimeter();
            }
        }
        return sum;
    }
}