package a6;


public class AssembledPart extends Part {
	public AssembledPart(String name, double cost) {
		super(name, cost);
	}

	@Override
	public double calcCost() {
		double cost = getCost();
		for (int i = 1; i < size(); ++i)
		{
			cost += getPart(i).calcCost();
		}
		return cost;
	}

}
