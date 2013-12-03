package proj;

import java.awt.*;
/*
 * Written by Subir Bandyopadhyay Sept 1, 2012
 * Class Rectangle represents a rectangle. 
 * 
 * Properties of class Square
 * referencePoint - where is the square located
 * height  of the square. The width of the square will be automatically the same as the height.
 * lastMousePosition - the last position where the mouse was pressed, 
 * 						useful when moving the square
 * colorSquare - color of the square
 * shapeSelected - a boolean variable which is true if the square is 
 * 					selected for moving 
 * 
 * Properties of class Square (See below for more details)
 * changeColor - modify the color of the square
 * changeShape - modify the width or the height of the square
 * moveShape - move the square by dragging the mouse 
 * shapeIsSelected - mark a square as "selected" by pressing the mouse button inside the square
 * resetShapeSelected - reset the shapeSelected flag
 * calculateArea - determine the area of the square
 * showMe - display the square inside the frame
 * toString - return a string describing the square.
 * savePositionWhereUserPressedMouse - useful when dragging the shape
 * 
 */
public class Square extends Shape{

	Rectangle delegation;

	// Constructor creates a square of size 50 X 50 with the left corner point at (200, 200)
	public Square(){
		super();
		delegation = new Rectangle();
	}
	
	 /*
	 * Method onThePerimeter checks if the user pressed the mouse button on the perimeter. 
	 * If so, it returns true; otherwise it return false.
	 */

	public boolean onThePerimeter(Coordinates mousePosition){
		return delegation.onThePerimeter(mousePosition);
	}

	/* 
	 * changeColor(c) modifies the color of the square to the new color c
	 */

	

	/*
	 * changeShape changes the width (or the height) of the square, if
	 * currentPhase is 3 (or 4).
	 * currentPhase = 0, means the user is moving the shape
	 *              = 1, a new shape is being created
	 *              = 2, the the color of the new shape is being modified,
	 *              = 3, the width of a square is being modified.
	 */

	public void changeShape(int currentPhase, int size){
		if (currentPhase == 3)
		{
			delegation.changeShape(currentPhase, size); 
			delegation.changeShape(4, size);
		}
	}

	/*
	 * Method moveShape(currentPositionMouse) allows the user to drag the square by dragging the mouse
	 * button. The shape is first selected by pressing the mouse button with the cursor inside the shape.
	 * Then, if the user drags the mouse, the shape should move with the mouse.
	 * Moving a square simply means modifying the reference point (upper left corner point) so that
	 * square moves with the mouse position.
	 * The idea is that if the x and the y coordinate of the position of the mouse is moved by specified
	 * amounts,the reference point must change by exactly the same amount.
	 */



	/*
	 * Method shapeIsSelected(positionOfMouse) checks if the position of the mouse where the user
	 * pressed the left mouse button is within the shape (at least 5 pixels away from the perimeter.
	 * If so, the flag shapeSelected is set and the method returns true.
	 * Otherwise, the flag shapeSelected is reset and the method returns false.
	 */

	public boolean shapeIsSelected(Coordinates positionOfMouse){ 
		setShapeSelected(delegation.shapeIsSelected(positionOfMouse));
		setLastMousePosition(positionOfMouse);
		setReferencePoint(delegation.getReferencePoint());
		return isShapeSelected();
	}

	
	/*
	 * calculateArea() returns the area of the rectangle
	 */

	public double calculateArea(){ 
		return delegation.calculateArea();
	}

	/*
	 * showMe(g) displays the square using the Graphic object g.
	 * It sets the color to be used, and draws the square using the specified 
	 * reference point, the size (representing both the width and the height).
	 */

	public void showMe(Graphics g){
		delegation.setColor(getColor());
		delegation.showMe(g);
	}

	/* 
	 * toString() returns the description of the square - the color, 
	 * the reference point and the size.
	 */

	public String toString(){
		return ("Square with reference point " + getReferencePoint() + " having size " + delegation.getHeight() + "\n");
	}
	public String sizeDescription()
	{
		return "size of square.";
	}
	public String handlePhases(int currentPhase) {
		if (currentPhase < 3)
		{
			return "change the size of the Square?";
		} 
		else
		{
			return "";
		}
	}

	@Override
	public boolean hasBigCircle() {
		return false;
	}

	@Override
	public boolean hasRectOrSquare() {
		return true;
	}
}
