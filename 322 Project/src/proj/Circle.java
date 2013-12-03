package proj;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Written by Subir Bandyopadhyay Sept 1, 2012
 * Class Rectangle represents a rectangle. 
 * 
 * Properties of class Circle
 * referencePoint - the left top corner of enclosing square - the reference point for the circle.
 * diameter  - the diameter of the circle
 * lastMousePosition - the last position where the mouse was pressed, 
 * 						useful when moving the rectangle
 * colorCircle - color of the circle
 * shapeSelected - a boolean variable which is true if the circle is 
 * 					selected for moving 
 * 
 * capabilities of class Circle (See below for more details)
 * onThePerimeter - returns true if mouse is pressed very close to the circumference of the circle
 * changeColor - modify the color of the circle
 * changeColorTemporarily - modify the color of the circle to yellow - useful when selecting a circle
 * changeColorBack - revert back to original color
 * changeShape - modify the width or the height of the circle
 * moveShape - move the circle by dragging the mouse 
 * shapeIsSelected - mark a circle as "selected" by pressing the mouse button inside the trctangle
 * resetShapeSelected - reset the shapeSelected flag
 * calculateArea - determine the area of the circle
 * showMe - display the circle inside the frame
 * toString - return a string describing the circle.
 * savePositionWhereUserPressedMouse - useful when dragging a shape
 * 
 */
public class Circle extends Shape {
	private int diameter;

	public Circle(){
		super();
		diameter = 50;
	}

	/* 
	 * changeColor(c) modifies the color of the circle to the new color c
	 */

	
	
	
	
	/*
	 * Method onThePerimeter checks if the user pressed the mouse button on the perimeter. 
	 * If so, it returns true; otherwise it return false.
	 */

	public boolean onThePerimeter(Coordinates mousePosition)
	{// the perimeter is selected if the distance from the point (x, y) to the center  is 
		// between radius - 5 and radius + 5
		int distanceFromCenter;
		Coordinates centreOfCircle;
		
		centreOfCircle = new Coordinates(getReferencePoint().getX() + diameter/2,
				                         getReferencePoint().getY() + diameter/2);
		distanceFromCenter = (int) distance(mousePosition, centreOfCircle);
		if ((distanceFromCenter >= diameter/2 - 5) && 
				(distanceFromCenter <= diameter/2 + 5)) {
			return true;
		} else{
			return false;
		}
	}

	/* 
	 * Method shapeIsSelected() returns true if the user previously selected the shape by 
	 * pressing the mouse button with the cursor inside the shape.
	 */



	/*
	 * Method shapeIsSelected(positionOfMouse) checks if the position of the mouse where the user
	 * pressed the left mouse button is within the shape (at least 5 pixels away from the perimeter.
	 * If so, the flag shapeSelected is set and the method returns true.
	 * Otherwise, the flag shapeSelected is reset and the method returns false.
	 */

	public boolean shapeIsSelected(Coordinates positionOfMouse){ 
		// Check if the user pressed the mouse button inside the shape
		double distance;

		Coordinates centreOfCircle;
		centreOfCircle = computeCenterOfCircle(getReferencePoint(), diameter);
		distance = distance(positionOfMouse, centreOfCircle);
		if (distance < diameter/2 - 5){
			setShapeSelected(true);
			setLastMousePosition(positionOfMouse);
			return true; 
		} else {
			setShapeSelected(false);
			return false;
		}
	}

	/*
	 * changeShape changes the diameter of the circle, if
	 * currentPhase is 3 .
	 * currentPhase = 0, means the user is moving the shape
	 *              = 1, a new shape is being created
	 *              = 2, the the color of the new shape is being modified,
	 *              = 3, the diameter of the circle is being modified,
	 */

	public void changeShape(int currentPhase, int size){
		int newReferencePointX, newReferencePointY;
		if (currentPhase == 3){
			newReferencePointX = getReferencePoint().getX() + (diameter - size)/2;
			newReferencePointY = getReferencePoint().getY() + (diameter - size)/2;
			setReferencePoint(new Coordinates(newReferencePointX, newReferencePointY));
			diameter = size; 
		} 

	}

	/*
	 * Method moveShape(currentPositionMouse) allows the user to drag the circle by dragging the mouse
	 * button. The shape is first selected by pressing the mouse button with the cursor inside the shape.
	 * Then, if the user drags the mouse, the shape should move with the mouse.
	 * Moving a circle simply means modifying the reference point of the circle so that the
	 * circle moves with the mouse position.
	 * The idea is that if the x and the y coordinate of the position of the mouse is moved by specified
	 * amounts,the reference point must change by exactly the same amount.
	 */



	
	public Coordinates computeCenterOfCircle(Coordinates referencePoint, int diameter){
		int xValueOfCenter,yValueOfCenter;
		xValueOfCenter = referencePoint.getX() + diameter/2;;
		yValueOfCenter = referencePoint.getY() + diameter/2;
		return new Coordinates(xValueOfCenter, yValueOfCenter);
	}
	/* 
	 * resetShapeSelected() simply resets the value of shapeSelected to false.
	 */


	/*
	 * calculateArea() returns the area of the rectangle
	 */

	public double calculateArea(){ 
		return (Math.PI * diameter * diameter/4);
	}

	/*
	 * showMe(g) displays the rectangle using the Graphic object g.
	 * It sets the color to be used, and draws the rectangle using the specified 
	 * reference point, the width and the height.
	 */
	public void showMe(Graphics g){
		g.setColor(getColor());
		g.drawOval(getReferencePoint().getX(), // Draw a circle with the specified 
				// corner point and diameter
				getReferencePoint().getY(),   
				diameter,
				diameter);
	}
	

	/* 
	 * toString() returns the description of the rectangle - the color, 
	 * the reference point and the size.
	 */

	public String toString(){
		return ("Circle with reference point " + getReferencePoint() + " having diameter " + diameter + "\n");
	}

	public String sizeDescription()
	{
		return "diameter of Circle.";
	}
	
	public String handlePhases(int currentPhase) {
		if (currentPhase < 3)
		{
			return "change the diameter of the Circle?";
		} 
		else
		{
			return "";
		}
	}

	@Override
	public boolean hasBigCircle() {
		return this.calculateArea() > 5000;
	}

	@Override
	public boolean hasRectOrSquare() {
		return false;
	}
}

