package proj;

import java.awt.Graphics;
import java.util.ArrayList;

public class Compound extends Shape {

	private static int numCompound = 1;
	private int id;
	public Compound()
	{
		super();
		id = numCompound++;
	}
	public Compound(ArrayList<Shape> a)
	{
		super();
		shapes = a;
		id = numCompound++;
	}
	public void addShape(Shape a)
	{
		shapes.add(a);
	}
	@Override
	public boolean onThePerimeter(Coordinates mousePosition) {
		boolean allValues = false;
		for (int i = 1; i < shapes.size(); ++i)
		{
			allValues = allValues || shapes.get(i).onThePerimeter(mousePosition);
		}
		return allValues;
	}

	@Override
	public void changeShape(int currentPhase, int size) {		
		for (int i = 1; i < shapes.size(); ++i)
		{
			shapes.get(i).changeShape(currentPhase, size);
		}

	}

	@Override
	public boolean shapeIsSelected(Coordinates positionOfMouse) {
		boolean allValues = false;
		
		for (int i = 1; i < shapes.size(); ++i)
		{
			allValues = allValues || shapes.get(i).shapeIsSelected(positionOfMouse);
		}

		setShapeSelected(allValues);
		setLastMousePosition(positionOfMouse);
		return allValues;
	}

	@Override
	public double calculateArea() {
		double allValues = 0.0;
		for (int i = 1; i < shapes.size(); ++i)
		{
			allValues += shapes.get(i).calculateArea();
		}
		return allValues;
	}

	@Override
	public void showMe(Graphics g) {
		for (int i = 1; i < shapes.size(); ++i)
		{
			shapes.get(i).showMe(g);
		}
	}

	@Override
	public String toString() {
		String allValues = "Compound Shape: " + id + "\n";
		for (int i = 1; i < shapes.size(); ++i)
		{
			allValues += shapes.get(i).toString();
		}
		return allValues+="\nEnd of Compound Shape: " + id + "\n";
	}

	@Override
	public String sizeDescription() {
		return "";
	}

	@Override
	public String handlePhases(int currentPhase) {
		return "";
	}

	@Override
	public boolean hasBigCircle() {
		boolean allValues = false;
		for (int i = 1; i < shapes.size(); ++i)
		{
			allValues = allValues || shapes.get(i).hasBigCircle();
		}
		return allValues;
	}

	@Override
	public boolean hasRectOrSquare() {
		boolean allValues = false;
		for (int i = 1; i < shapes.size(); ++i)
		{
			allValues = allValues || shapes.get(i).hasRectOrSquare();
		}
		return allValues;
	}

}
