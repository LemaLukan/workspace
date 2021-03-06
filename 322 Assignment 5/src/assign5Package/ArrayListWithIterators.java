package assign5Package;

import java.util.ArrayList;

public class ArrayListWithIterators extends AbstractList {
	
	private ArrayList<tester> table;
	private int numElements = 0;
	
	@Override
	public AbstractIterator createIterator(int numElements) {
		return new Iterator(numElements);
	}

	@Override
	public void append(int v) {
		if (numElements == 0)
		{
			table.add(0, new tester(v));
			++numElements;
		}
		else
		{
			if (table.get(numElements-1).getVal() >= v)
			{
				table.add(new tester(v));
				++numElements;
			}
			else
			{
				for (int i = 0; i < numElements; i++)
				{
					if (table.get(i).getVal() < v)
					{
						table.add(i, new tester(v));
						++numElements;
						break;
					}
				}
			}
		}
	}
	
	public ArrayListWithIterators()
	{
		table = new ArrayList<tester>();
	}
	
	public class Iterator extends AbstractIterator implements MyIterator
	{
		private ArrayList<tester> result;
		private int currentElement;
		
		public Iterator(int n)
		{
			result = new ArrayList<tester>(); // result stores the array to be returned
			for (int i = 0; i < numElements && i < n; i++){
				result.add(table.get(i));
			}
		}

		@Override
		public void first() {
			currentElement = 0;
		}

		@Override
		public void next() {
				currentElement++;
		}
		
		@Override
		public boolean isDone() {
			return currentElement >= result.size();
		}

		@Override
		public tester currentItem() {
			return result.get(currentElement);
		}
		
	}

}
