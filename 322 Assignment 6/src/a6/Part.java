package a6;

import java.util.ArrayList;

public abstract class Part implements Costable {
	private ArrayList<Part> partList;
	private String name;
	private double cost;
	public Part(String name, double cost)
	{
		this.name = name;
		this.cost = cost;
		partList = new ArrayList<Part>(10);
		this.addPartToList(this);
	}
	public String getName()
	{
		return name;
	}
	public double getCost()
	{
		return cost;
	}
	public boolean addPartToList(Part part)
	{
		return partList.add(part);
	}
	public ArrayList<Part> getPartList()
	{
		return partList;
	}
	public int size()
	{
		return partList.size();
	}
	public Part getPart(int index)
	{
		return partList.get(index);
	}
	public String toString() {
		ArrayList<String> inList = new ArrayList<String>(10);
		String result = "[" + getName();
		inList.add(getName());
		for (int i = 1; i < partList.size(); ++i)
		{
			if (!inList.contains(partList.get(i).getName()))
			{
				ArrayList<String> found = getAllNames(new ArrayList<String>(10), partList.get(i));
				for (int k = 0; k < found.size(); ++k)
				{
					if (!inList.contains(found.get(k)))
					{
						inList.add(found.get(k));
						result += ", " + found.get(k);
					}
				}
			}
		}
		result += "]";
		return result;
	}
	
	private ArrayList<String> getAllNames(ArrayList<String> store, Part root) {
		store.add(root.getPartList().get(0).getName());
		for (int i = 1; i < root.getPartList().size(); ++i)
		{
			store = getAllNames(store, root.getPartList().get(i));
		}
		return store;
	}

	
}
