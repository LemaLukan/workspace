package test;
/*	Name: Stephen Nusko
 * 	Modifed: Feb 11/13
 *  Purpose: Rectangle contains the required information to describe a rectangle
 *  through width and height input.
 */
public class Rectangle {
	double width, height;
	/* default constructor
	 * sets the default values for width and height to 1.
	 */
	Rectangle()
	{
		width = 1;
		height = 1;
	}
	
	/*
	 * Constructor sets the value of width and height to the
	 * inputted values.
	 */
	Rectangle(double newWidth, double newHeight)
	{
		width = newWidth;
		height = newHeight;
	}
	
	/*
	 * getArea returns the area of the rectangle represented by
	 * the width and height multiplied together.
	 */
	double getArea()
	{
		return width * height;
	}
	
	/*
	 * getPerimeter returns the perimeter of the rectangle Represented by
	 * the width and height added together twice.
	 */
	double getPerimeter()
	{
		return width*2 + height*2;
	}
	
	/*
	 * compareTo takes in a Rectangle object (rec) and compares it to the
	 * current Rectangle return 1 if the current is greater or equal
	 * and -1 otherwise.
	 */
	int compareTo(Rectangle rec)
	{
		if (this.getArea() >= rec.getArea())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
}
