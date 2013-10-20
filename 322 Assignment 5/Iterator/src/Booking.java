package assign5Package;

public class Booking {
	private int covers; // how many people are in the party
    private MyDate dayBooking; // date of the booking
    private int time;          // time of booking either 1 for lunch or 2 for dinner
    private Table tableAllotted; // which table object has been allotted
    public static final int LUNCH = 1, DINNER = 2;


    public Booking(int howManyPeople, MyDate dayBooking, int lunchOrDinner, Table whichTable){
		covers = howManyPeople;
		this.dayBooking = dayBooking;
		time = lunchOrDinner;
		tableAllotted = whichTable;
	}

    public MyDate getDate(){
		return dayBooking;
	}

	public Table getTable(){
		return tableAllotted;
	}

	public String toString(){
		if (time == LUNCH){
			return (tableAllotted.toString() + " Booked for lunch on " + dayBooking.toString());
		}
		else {
			return (tableAllotted.toString() + " Booked for dinner on " + dayBooking.toString());
		}
	}

}
