package badCode;

public class FrenchIncomeTaxCalculator extends IncomeTaxCalculator {

	public FrenchIncomeTaxCalculator()
	{
		super();
		this.setDate(new FrenchDate(this.getSimpleDateFormat().format(this.getCalendar().getTime())));
	}
	@Override
	public String createDateAddress(String address, String province) {
		FrenchDateAddress a = new FrenchDateAddress(address, this.getDate(), province);
		return a.displayDateAddress();
	}

	@Override
	public double computeTax(double income) {
		FrenchTaxPayable a = new FrenchTaxPayable();
		return a.taxPayable(income);
	}

	@Override
	public String createBodyLetter(double taxPayable, String province,
			String name, String gender) {
		FrenchLetterBody a = new FrenchLetterBody();
		return a.displaySalutation(name, gender, province) + a.displayBodyLetter(taxPayable, province);
	}
	
}
