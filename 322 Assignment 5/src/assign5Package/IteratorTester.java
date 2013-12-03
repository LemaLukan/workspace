package assign5Package;



public class IteratorTester {
	public static void main(String args[]) {
	    int elements[] = { 5, 4, 2, 12,1, 15, 9, 3};
	    AbstractList myCollection;
	    AbstractList.AbstractIterator myIterator;
	    //myCollection = new NewArrayOfIntWithIterator();
	    myCollection = new ArrayListWithIterators();
	    for (int i = 0; i < elements.length; i++){
	           myCollection.append(elements[i]);
	    }
	    myIterator = myCollection.createIterator(10);
	    myIterator.first();
	    System.out.println("Print the entire list to see.");
	    while (!myIterator.isDone()){
	       System.out.println(myIterator.currentItem().toString());
	       myIterator.next();
	    }
	    System.out.println("only 4 will be changed to the value 3");
	    myIterator = myCollection.createIterator(4);
	    myIterator.first();
	    while (!myIterator.isDone()){
	       System.out.println(myIterator.currentItem().toString());
	       Integer a = 3;
	       myIterator.currentItem().setVal(a);
	       myIterator.next();
	    }
	    System.out.println("Final, note how the first 4 has changed");
	    myIterator = myCollection.createIterator(10);
	    myIterator.first();
	    while (!myIterator.isDone()){
	       System.out.println(myIterator.currentItem().toString());
	       myIterator.next();
	    }
	  }
}
