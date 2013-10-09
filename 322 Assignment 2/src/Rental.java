
/**
 * The rental class represents a customer renting a movie.
 */
public class Rental {
	
	private Movie movie;
	private int daysRented;
	
	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	
	public int getDaysRented() {
		return daysRented;
	}
	
	public Movie getMovie() {
		return movie;
	}
	public double getCharge()
	{		
		return this.getMovie().getCharge(this.getDaysRented());
	}

	public int getPoints() {
		return this.getMovie().getPoints(this.getDaysRented());
	}
}
