package q3;

public class Triangle extends GeometricObject {
	private double side1, side2, side3;
	Triangle()
	{
		super();
		side1 = 1.0;
		side2 = 1.0;
		side3 = 1.0;
	}
	Triangle(double s1, double s2, double s3)
	{
		super();
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}
	
	public double getSide1()
	{
		return side1;
	}
	public double getSide2()
	{
		return side2;
	}
	public double getSide3()
	{
		return side3;
	}
	public void setSide1(int s)
	{
		side1 = s;
	}
	public void setSide2(int s)
	{
		side2 = s;
	}
	public void setSide3(int s)
	{
		side3 = s;
	}
	
	public double getArea()
	{
		double s = .5*getPerimeter();
		return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
	}
	public double getPerimeter()
	{
		return side1 + side2 + side3;
	}
	public String toString()
	{
		return super.toString() + " side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3 +
				" area = " + getArea() + " perimetor = " + getPerimeter();
	}
}
