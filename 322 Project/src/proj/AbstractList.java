package proj;


public abstract class AbstractList {
	abstract AbstractIterator createIterator();
	abstract class AbstractIterator implements Iterator{
	}
	abstract boolean add(Shape a);
	abstract int size();
	abstract Shape get(int index);
	abstract Shape remove(int index);
}
