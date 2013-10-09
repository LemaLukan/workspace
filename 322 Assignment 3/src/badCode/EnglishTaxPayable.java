package badCode;

public class EnglishTaxPayable extends TaxCalculator {

	@Override
	public double taxPayable(double income) {
		if (income < 5000.00){
			return 0.0;
		} else if (income < 10000.00){
			return 0.1 * income;
		} else {
			return 0.2 * income;
		} 
	}


}
