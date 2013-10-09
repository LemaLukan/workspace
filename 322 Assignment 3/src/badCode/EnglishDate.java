package badCode;

public class EnglishDate extends MyDate {
	private static String monthNames[] = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October",
            "November", "December"};	
	public EnglishDate(String inputString) {
		super(inputString);
	}
	
	public String toString(){
			return (monthNames[this.getMonth()-1] + " " + this.getDay() +  ", " + this.getYear() );
		
	}

}
