package proj;


public abstract class ShapeFactory {
	
	public static Shape createShape(String shape) throws Exception
	{
		if (shape.compareTo("rectangle") == 0)
		{
			return new Rectangle();
		}
		else if (shape.compareTo("circle") == 0)
		{
			return new Circle();
		}
		else if (shape.compareTo("square") == 0)
		{
			return new Square();
		}
		else
		{
			throw new Exception("Unknown shape type");
		}
	}
}
