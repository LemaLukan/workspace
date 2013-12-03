package proj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Shape {
	private Coordinates referencePoint;
	private Coordinates lastMousePosition;
	private Color colorCircle;
	
	private Color originalColorCircle;

	private boolean shapeSelected = false;
	private boolean perimeterSelected = false;
	protected ArrayList<Shape> shapes;
	
	public Shape()
	{
		setReferencePoint(new Coordinates(200, 200));  // new figure is at the center of frame
		setColor(new Color(0, 0, 0));  // colour is black
		shapes = new ArrayList<Shape>();
		shapes.add(this);
	}
	
	public void changeColor(Color c){
		setColor(c);
	}
	public void changeColorTemporarily(){	
		originalColorCircle = getColor(); 
		setColor(Color.yellow) ;
		perimeterSelected = true;
		for (int i = 1; i < shapes.size(); ++i)
		{
			shapes.get(i).changeColorTemporarily();
		}
	}
	public void changeColorBack(){	
		setColor(originalColorCircle); 
		perimeterSelected = false;
		for (int i = 1; i < shapes.size(); ++i)
		{	 
			shapes.get(i).changeColorBack();
		}
	}
	public boolean isPerimeterSelected()
	{
		return perimeterSelected;
	}
	public abstract boolean onThePerimeter(Coordinates mousePosition);
	
	void moveShape(Coordinates currentPositionMouse){	     	
		if (isShapeSelected()) {		
			// If a shape is selected for a move operation, change the reference point 
			// as the mouse is being dragged.
			Coordinates diff = new Coordinates(currentPositionMouse.getX() - getLastMousePosition().getX(), currentPositionMouse.getY() - getLastMousePosition().getY());
			
			referencePoint.setX(referencePoint.getX() + diff.getX());
			referencePoint.setY(referencePoint.getY() + diff.getY());
			setLastMousePosition(currentPositionMouse);
			for (int index = 1; index < shapes.size(); ++index)
			{
				shapes.get(index).setShapeSelected(true);
				shapes.get(index).moveShape(new Coordinates(shapes.get(index).getLastMousePosition().getX() + diff.getX(),
															shapes.get(index).getLastMousePosition().getY() + diff.getY()));		
			}
		}
	}
	public abstract void changeShape(int currentPhase, int size);
	public abstract boolean shapeIsSelected(Coordinates positionOfMouse);
	
	protected double distance(Coordinates point1, Coordinates point2){
		double dist; // dist is the distance from point1 to point2
		dist = Math.sqrt((double) ((point1.getX() - point2.getX())*
				(point1.getX() - point2.getX()) +
				(point1.getY() - point2.getY())*(point1.getY() - point2.getY())));
		return dist ;
	} 
	public void resetShapeSelected(){
		setShapeSelected(false);
	}
	public abstract double calculateArea();
	
	public abstract void showMe(Graphics g);
	public void savePositionWhereUserPressedMouse(int x, int y){
		setLastMousePosition(new Coordinates(x, y));
	}
	public abstract String toString();
	// Getters and setters.
	public Coordinates getReferencePoint() {
		return referencePoint;
	}

	public void setReferencePoint(Coordinates referencePoint) {
		this.referencePoint = referencePoint;
	}

	public boolean isShapeSelected() {
		return shapeSelected;
	}
	public void setShapeSelected(boolean shapeSelected) {
		this.shapeSelected = shapeSelected;
	}

	public Coordinates getLastMousePosition() {
		return lastMousePosition;
	}

	public void setLastMousePosition(Coordinates lastMousePosition) {
		this.lastMousePosition = lastMousePosition;
	}

	public Color getColor() {
		return colorCircle;
	}

	public void setColor(Color colorCircle) {
		this.colorCircle = colorCircle;
	}

	public abstract String sizeDescription();
	public abstract String handlePhases(int currentPhase);
	public abstract boolean hasBigCircle();
	public abstract boolean hasRectOrSquare();
}