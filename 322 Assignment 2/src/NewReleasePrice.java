
public class NewReleasePrice extends Price {

	@Override
	public double getCharge(int daysRented) {
		return daysRented * 3;
	}

}
