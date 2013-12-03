package a6;

public class Assign6 {
	public static void main(String a[]){
		MyStore myStore = new MyStore();
		String listParts[] = {"P1", "P2", "P3", "P4", "P5", "P6"}; // a list of all the part numbers
		
		myStore.add(new PurchasedPart("P1", 10.00)); 
		// part "P1" is purchased from the market and cost $10.00
		
		myStore.add(new PurchasedPart("P2", 20.00)); 
		// part "P2" is purchased from the market and cost $20.00
		
		myStore.add(new AssembledPart("P3", 10.00));
		//part # "P3" is assembled in the store 
		// and the cost of assembly is $10.00
		myStore.includesComponent("P3", "P1");// Part "P3" has components "P1" and "P2"
		myStore.includesComponent("P3", "P2");
		
		myStore.add(new AssembledPart("P4", 20.00));
		//part # "P4" is assembled in the store 
		// and the cost of assembly is $20.00
		myStore.includesComponent("P4", "P3");// Part "P4" has components "P3" and "P2"
		myStore.includesComponent("P4", "P2");
		
		myStore.add(new AssembledPart("P5", 30.00));
		//part # "P5" is assembled in the store 
		// and the cost of assembly is $30.00
		myStore.includesComponent("P5", "P4");// Part "P5" has components "P4", "P3" and "P1"
		myStore.includesComponent("P5", "P3");
		myStore.includesComponent("P5", "P1");
		
		myStore.add(new AssembledPart("P6", 50.00));
		myStore.includesComponent("P6", "P4");
		
		for (String partNo:listParts){
			double found = myStore.findCost(partNo);
			System.out.println("Cost of " + partNo + " is " + (found == -1 ? "non-existent" : "$" + myStore.findCost(partNo)));
			System.out.println("Part list for "  + partNo + " is " + (myStore.findPartList(partNo) == null ? "non-existent" : myStore.findPartList(partNo).toString()));
		}
	}

}
