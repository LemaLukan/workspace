package test;
/*	Name: Stephen Nusko
 * 	Modifed: Feb 11/13
 *  Purpose: To test the class Rectangle with two predefined 
 *  Rectangles.
 */
public class testRect {
	/*
	 * main method to start testing.
	 */
	public static void main(String[] args)
	{
		// The first Rectangle declared.
		Rectangle first = new Rectangle(4.0, 48.6);
		// The Second Rectangle declared.
		Rectangle second = new Rectangle(3.5, 35.9);
		
		/* 
		 * The following three lines of code outputs all the different variables and
		 * methods associated with the first Rectangle object. 
		 */
		
		System.out.println("first rectangle has width " + first.width + " and height " + first.height + ".");
		System.out.println("The area is equal to " + first.getArea() + " with a perimeter of " + first.getPerimeter());
		System.out.println("when we compare the first with the second (ie first.compareTo(second)) we get " + first.compareTo(second) + ".\n");
	
		/* 
		 * The following three lines of code outputs all the different variables and
		 * methods associated with the second Rectangle object. 
		 */
		
		System.out.println("second rectangle has width " + second.width + " and height " + second.height + ".");
		System.out.println("The area is equal to " + second.getArea() + " with a perimeter of " + second.getPerimeter());
		System.out.println("when we compare the second with the first (ie second.compareTo(first)) we get " + second.compareTo(first) + ".\n");
	}
}
