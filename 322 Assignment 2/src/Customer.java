import java.util.*;

public class Customer {
	
	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void addRental(Rental arg) {
		rentals.addElement(arg);
	}
	
	public String getName() {
		return name;
	}
	
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals_enum = rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		
		while (rentals_enum.hasMoreElements()) {
			Rental aRental = (Rental) rentals_enum.nextElement();
			// add frequent renter points
			frequentRenterPoints += aRental.getPoints();
			// show figures for this rental
			result += "\t" + aRental.getMovie().getTitle() + "\t" + String.valueOf(aRental.getCharge()) + "\n";
			totalAmount += aRental.getCharge();
		}
		
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		
		return result;
	}
}
