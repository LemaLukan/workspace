package assign5Package;

public class TestTree extends AbstractList{
	private Node root = null;

    private class Node{
		private int value;
		private TestTree leftPtr;// Points to the subtree with smaller values
		private TestTree rightPtr;// points to the subtree with greater or
		                          // equal values

		public Node(int v){
			value = v;
			leftPtr = new TestTree();
			rightPtr = new TestTree();
		}
	}

	/* traverse visits the largest numberNodesToVisit values in the tree
	   and saves the results in result  */

	public int traverse(int numberNodesToVisit, Integer result[]){
		if (numberNodesToVisit == 0) return 0;
		if (root == null) return numberNodesToVisit;
		else { // visit right subtree, then root then left subtree
		    numberNodesToVisit = root.rightPtr.traverse(numberNodesToVisit, result);
			if (numberNodesToVisit == 0) return 0;
			else result[result.length - numberNodesToVisit] = root.value;
			numberNodesToVisit--; // Just visted the root node
			if (numberNodesToVisit == 0) return 0;
				numberNodesToVisit = root.leftPtr.traverse(numberNodesToVisit, result);
			return numberNodesToVisit;
		}
	}
	
	public class Iterator extends AbstractIterator implements MyIterator{
	       private Integer result[];
	       private int currentElement;
	       private int numElements = 0; // Stores the number of elements in result
	       private boolean endOfTable;


	       public Iterator(int numberNodesToVisit){
			   int remainingNumberNodesToVisit;

			   result = new Integer[numberNodesToVisit];
			   remainingNumberNodesToVisit = traverse(numberNodesToVisit, result); // result stores the array to be returned
			   if (remainingNumberNodesToVisit > 0 ) System.out.println("Invalid value of " + numberNodesToVisit);
	       }

	       public void first(){
			   currentElement = 0;
			   if (result.length > 0) endOfTable = false;
			   else endOfTable = true;
	       }

	       public void next(){
			   if (currentElement < result.length - 1)
			       currentElement ++;
	              else endOfTable = true;
	       }

	       public boolean isDone(){
	          return endOfTable;
	       }

	       public Integer currentItem(){
			   return result[currentElement];
	       }
	    }

	    public Iterator createIterator(int numElements){
			        return new Iterator(numElements);
		}


	public void append(int v){ // Inserts new int value into tree
                                 // Left tree always has values less than root
                                 // right tree has values less than or equal to the root
         if (root == null) {
			 root = new Node(v);
		 }
         else if(v < root.value) {
			 root.leftPtr.append(v);
         }
	     else {
			  root.rightPtr.append(v);
		 }

     }


}
