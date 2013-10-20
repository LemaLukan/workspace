package assign5Package;
import java.util.* ;
public class BookingSystem {
	MyDate currentDate ;  // Date for which a request for retrieving booking info is made

	  // Associations:

	  Restaurant restaurant = null ;  // pointer to the restaurant object
	  ArrayList<Booking> currentBookings;  // All bookings on the specfied date are saved here

	  // Singleton pattern to be discussed in class

/*	  private static BookingSystem uniqueInstance ;

	  public static BookingSystem getInstance(){
	    if (uniqueInstance == null) {
	      uniqueInstance = new BookingSystem() ;
	    }
	    return uniqueInstance ;
	  }

	  private BookingSystem(){
	    restaurant = new Restaurant() ;
	  }  */
	  
	  public BookingSystem(){
		    restaurant = new Restaurant() ;
		  }


	  public MyDate getDate(){
		  return currentDate;
	  }

	  public void display(MyDate date){ // Retrieve all bookings on specified date from restaurant object
	    currentDate = date ;
	    currentBookings = restaurant.getBookings(currentDate);
	  }



	  public ArrayList<Booking>  getBookings(){ //return all bookings retrieved by method display
	    return currentBookings;
	  }

}
