package badCode;

public class FrenchTaxPayable extends TaxCalculator {

	@Override
	public double taxPayable(double income) {
		if (income < 8000.00){
			return 0.0;
		} else if (income < 12000.00){
			return 0.15 * income;
		} else {
			return 0.25 * income;
		} 
	}


}
