
public class Hexagon extends GeometricObject implements Compare {
	private int side;
	Hexagon()
	{
		side = 1;
	}
	Hexagon(int side)
	{
			this.side = side;
	}
	public int getSide()
	{
		return side;
	}
	public void setSide(int side)
	{
		this.side = side;
	}
	// area = 3 * sqrt(3)/2*side * side;
	public double getArea()
	{
		return 3 * Math.sqrt(3)/2*side*side;
	}
	public double getPerimeter()
	{
		return side*6;
	}
	// this is less then o -1
	// this is equal to o 0
	// this is greater then o 1
	public int compare(Object o) {
		if (o instanceof Hexagon)
		{
			Hexagon i = (Hexagon)o;
			if(this.getArea() < i.getArea())
			{
				return -1;
			}
			else if (this.getArea() == i.getArea())
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		// return -2 if error.
		return -2;
	}
	
}
