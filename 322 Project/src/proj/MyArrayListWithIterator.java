package proj;

import java.util.ArrayList;


public class MyArrayListWithIterator extends AbstractList {
	private ArrayList<Shape> myShapes;
	
	public MyArrayListWithIterator()
	{
		myShapes = new ArrayList<Shape>();
	}
	@Override
	boolean add(Shape a) {
		return myShapes.add(a);
	}
	@Override
	AbstractIterator createIterator() {
		return new MyIterator(myShapes);
	}
	@Override
	int size() {
		return myShapes.size();
	}
	@Override
	Shape get(int index) {
		return myShapes.get(index);
	}
	public Shape remove(int index)
	{
		return myShapes.remove(index);
	}
	public class MyIterator extends AbstractIterator implements Iterator
	{

		private ArrayList<Shape> results;
		private int current;
		public MyIterator(ArrayList<Shape> myShapes)
		{
			current = 0;
			results = myShapes;
		}
		@Override
		public void first() {
			current = 0;
		}


		@Override
		public void next() {
			if (!isDone())
				++current;
		}


		@Override
		public boolean isDone() {
			if (current == results.size())
				return true;
			else
				return false;
		}


		@Override
		public Shape currentItem() {
			return results.get(current);
		}
	}
}
