package assign5Package;

public class IteratorTester {
	public static void main(String args[]) {
		int elements[] = { 5, 4, 2, 12,1, 15, 9, 3};
	    AbstractList myArray;
	    AbstractList.AbstractIterator myIterator;
	 //   
	    myArray = new NewArrayOfIntWithIterator();
	    for (int i = 0; i < elements.length; i++)
	           myArray.append(elements[i]);
	    myIterator = myArray.createIterator(4);
	    myIterator.first();
	    while (!myIterator.isDone()){
	       System.out.println(myIterator.currentItem().toString() + "\n");
	       myIterator.next();
	    }
	}
}
