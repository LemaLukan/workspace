package a6;

public class PurchasedPart extends Part {
	public PurchasedPart(String name, double cost)
	{
		super(name, cost);
	}

	@Override
	public double calcCost() {
		return getCost();
	}

	


}
