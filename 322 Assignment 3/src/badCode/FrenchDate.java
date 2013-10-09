package badCode;

public class FrenchDate extends MyDate {
    private static String monthNames[] = {"janvier", "fevrier", "mars",
		"avril", "mai", "juin", "juillet", "aout", 
		"septembre", "octobre", "novembre", "decembre"};
	public FrenchDate(String inputString) {
		super(inputString);
	}

	public String toString(){	
			return ( this.getDay() + " " + monthNames[this.getMonth()-1] +  ", " + this.getYear() );
		
	}
}
