package assign5Package;

public abstract class AbstractList {
	public abstract AbstractIterator createIterator(int numElements);
	public abstract void append(int v);
	public abstract class AbstractIterator implements MyIterator{
	}
}
