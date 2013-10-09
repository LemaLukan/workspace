
public class Movie {
	
	public static final int CHILDRENS = 2;
	public static final int NEW_RELEASE = 1;
	public static final int REGULAR = 0;
	
	private String title;
	private int priceCode;
	private Price moviePrice;
	public Movie(String title, int priceCode) {
		this.title = title;
		if (priceCode == CHILDRENS)
		{
			moviePrice = new ChildrenPrice();
		}
		else if (priceCode == NEW_RELEASE)
		{
			moviePrice = new NewReleasePrice();
		}
		else if (priceCode == REGULAR)
		{
			moviePrice = new ReqularPrice();
		}
		else
		{
			System.out.println("Unsupported Movie price code.");
		}
		this.priceCode = priceCode;
	}
	
	public int getPriceCode() {
		return priceCode;
	}
	
	public void setPriceCode(int arg) {
		this.priceCode = arg;
	}
	
	public String getTitle() {
		return title;
	}

	public int getPoints(int daysRented) {
		int frequentRenterPoints = 1;
		if ((this.getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
		{	
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	public double getCharge(int daysRented) {
		
		return this.getMovieType().getCharge(daysRented);
	}
	public Price getMovieType()
	{
		return this.moviePrice;
	}
}
