package assign5Package;
import java.util.*;
public class Restaurant {
	ArrayList<Booking> bookingDataBase; // Since we are not interacting with a database
    // All bookings are saved using an arraylist
public static final int LUNCH = 1, DINNER = 2;

public Restaurant(){ // This mainly creates the booking database
Table table1, table2;
MyDate date1, date2;

bookingDataBase = new ArrayList<Booking>(10);
table1 = new Table(1, 6); ; //table1 seats 6 people
table2 = new Table(2, 8); ; //table2 seats 8 people
date1 = new MyDate("25/7/2009"); // we only handle
date2 = new MyDate("24/7/2009");
bookingDataBase.add(new Booking(3, date1, LUNCH, table1)); // table1 booked for lunch (3 people) on 25/7/09
bookingDataBase.add(new Booking(7, date1, LUNCH, table2)); // table2 booked for lunch (7 people) on 25/7/09
bookingDataBase.add(new Booking(5, date1, DINNER, table1)); // table1 booked for lunch (3 people) on 25/7/09
bookingDataBase.add(new Booking(6, date2, LUNCH, table1)); // table1 booked for lunch (3 people) on 25/7/09
bookingDataBase.add(new Booking(3, date2, DINNER, table1)); // table1 booked for lunch (3 people) on 25/7/09

}

public String toString(){
String s = "";
for (Booking aBooking:bookingDataBase){
s += aBooking.toString()+ "\n";
}
return s;
}


public ArrayList<Booking> getBookings(MyDate date){ // retrieve all bookings on a specified date from bookingDataBase

ArrayList<Booking> bookingForSpecifiedDate = new ArrayList<Booking>(10);
for (Booking aBooking:bookingDataBase){
if (date.sameDay(aBooking.getDate())) bookingForSpecifiedDate.add(aBooking);
}
return bookingForSpecifiedDate;
}


}
