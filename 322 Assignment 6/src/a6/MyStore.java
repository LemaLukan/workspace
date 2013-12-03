package a6;

import java.util.ArrayList;

public class MyStore {
	private ArrayList<Part> inStore;
	public MyStore()
	{
		inStore = new ArrayList<Part>(10);
	}
	
	public boolean add(Part part) {
		if (!inStore.contains(part))
		{
			return inStore.add(part);
		}
		else
		{
			return false;
		}
	}

	public boolean includesComponent(String part, String contains) {
		int indexPart = -1;
		int indexContains = -1;
		for (int i = 0; i < inStore.size(); ++i)
		{
			if (inStore.get(i).getName().compareTo(part) == 0)
			{
				indexPart = i;
			}
			if (inStore.get(i).getName().compareTo(contains) == 0)
			{
				indexContains = i;
			}
		}
		if (indexPart == -1 || indexContains == -1)
		{
			return false;
		}
		else
		{
			return inStore.get(indexPart).addPartToList(inStore.get(indexContains));
		}	
	}

	public double findCost(String partNo) {
		int indexPart = -1;
		for (int i = 0; i < inStore.size(); ++i)
		{
			if (inStore.get(i).getName().compareTo(partNo) == 0)
			{
				indexPart = i;
				break;
			}
		}
		if (indexPart == -1)
		{
			return -1.0;
		}
		else
		{
			return inStore.get(indexPart).calcCost();
		}
	}

	public Part findPartList(String partNo) {
		int indexPart = -1;
		for (int i = 0; i < inStore.size(); ++i)
		{
			if (inStore.get(i).getName().compareTo(partNo) == 0)
			{
				indexPart = i;
				break;
			}
		}
		if (indexPart == -1)
		{
			return null;
		}
		return inStore.get(indexPart);
	}

}
