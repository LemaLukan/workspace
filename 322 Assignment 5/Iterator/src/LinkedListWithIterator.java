package assign5Package;

public class LinkedListWithIterator extends AbstractList{
		private class Node{      // This inner class contains one Object and one
        // self-referential link.
			private Object value;
			private Node nextNode;

			public Node(Integer value, Node nextNode){  // The constructor inserts the
                              // arguments in the new object.
				this.value = value;
				this.nextNode = nextNode;
			}
		}

		private Node firstNode;// firstNode is the starting node of the linked list.

		/* An iterator class gives the capability of stepping through the objects in a list.
			An object of the iterator class allows you to examine and possibly modify the elements
			in a list.
			Here Iterator is an inner class giving many of the features of the List iterators
			available in Java.
		 */
		
		public class Iterator extends AbstractIterator implements MyIterator{
			private Node currentNode, previousNode;
			int howManyNumbersNeeded; // This keeps track of how many numbers are needed
			private boolean endOfListToBeRetrieved;
			private int nodeCounter; // This keeps track of which node we are currently looking at.
			// currentNode points at the object on the list that is now being considered
			// This node will, from now on, be called the current node
			// previousNode is the object in the list preceding  the current node
			// If the current node is the first node in the list, previousNode will be null
			// since there is nothing before the first node.


			public Iterator(int howManyNumbersNeeded) { // The constructor sets the first node in the list as the current node
				currentNode = firstNode;
				previousNode = null;
				this. howManyNumbersNeeded = howManyNumbersNeeded;
			}



			/* The  method addBeforeCurrent adds a new node with value x before
				the current node.
			 */

			public void addBeforeCurrent(Integer x){
				if (firstNode == null){// The list is empty
					firstNode = new Node(x, null);
					currentNode = firstNode;
				}
				else {// create the new node and set up the links properly
					Node newNode = new Node(x, currentNode);
					if (previousNode != null) {
						previousNode.nextNode = newNode;
					}
					else{// The new node will now become the first node in the list
						firstNode = newNode;
					}
					currentNode = newNode;
				}
			}
	
	
	
			// Method currentItem() returns the object saved in the object in the current node
			public Integer currentItem(){
				return (Integer) currentNode.value;
			}
	
	// first() sets the first node as the current node
			public void first(){
				currentNode = firstNode;
				previousNode = null;
				nodeCounter = 1;
				if (currentNode != null) endOfListToBeRetrieved = false;
			}
	
	//   endOfList() is true if current is not pointing to any node.
	
	
	
			public boolean endOfList(){
				return currentNode == null;
			}
	
			public boolean isDone(){
				return endOfListToBeRetrieved;
			}
	
	/* next makes the next node the current node. The method returns true
	if the operation was successful otherwise it returns false.
	*/
			public void next(){
				if (nodeCounter >= howManyNumbersNeeded) {
					endOfListToBeRetrieved = true;
				}
				if  (!endOfList()){
					previousNode = currentNode;
					currentNode = currentNode.nextNode;
					nodeCounter++;
				}
			}
	}
	// The method createIterator returns an object of class Iterator.
	// After calling this method, to manipulate and navigate through the list,
	// we can invoke methods in Iterator on the object returned.
	
	public Iterator createIterator(int numElements){
		return new Iterator(numElements);
	}
	
	public LinkedListWithIterator(){ // Constructor creates an
	                          // empty list.
		firstNode = null;
	}
	
	/* We are assuming that all append operations will be over before any
	retrieval operations will be carried out. */
	
	public void append(int v){ // Inserts new int value into the next available cell in array
		boolean notDone = true; // This flag is true until the position to insert new node
	             //is found.
		LinkedListWithIterator. Iterator myIterator = createIterator(0);
		myIterator.first();
		while (notDone){
			if (myIterator.endOfList()) {
				notDone = false; // the new value is less than the smallest value
			}
			else if (myIterator.currentItem().intValue() < v){
				notDone = false;// found the insertion point
			}
			else myIterator.next();
		}
		myIterator. addBeforeCurrent(new Integer(v));
	}
	
	public String toString(){
		String s = "";
		Node listPtr;
		listPtr = firstNode;
	
		while (listPtr != null){
			s += listPtr.value.toString() + "\n";
			listPtr = listPtr. nextNode;
		}
		return s;
	}

/* I used this tester just to make sure that the class definition is OK */
	public static void main(String args[]) {
	int elements[] = { 5, 4, 2, 12,1, 15, 9, 3};
	Integer result[];
	LinkedListWithIterator myList;
	LinkedListWithIterator.Iterator anIterator;
	myList = new LinkedListWithIterator();
	for (int i = 0; i < elements.length; i++){
		myList.append(elements[i]);
	}
	
	anIterator = myList.createIterator(5);
	for (anIterator.first(); !anIterator.isDone(); anIterator.next())
		System.out.println(anIterator.currentItem());
	}   
	
}