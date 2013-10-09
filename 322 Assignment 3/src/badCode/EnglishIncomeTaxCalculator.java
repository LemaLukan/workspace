package badCode;

public class EnglishIncomeTaxCalculator extends IncomeTaxCalculator {

	public EnglishIncomeTaxCalculator()
	{
		super();
		this.setDate(new EnglishDate(this.getSimpleDateFormat().format(this.getCalendar().getTime())));
	}
	@Override
	public String createDateAddress(String address,	String province) {
		EnglishDateAddress a = new EnglishDateAddress(address, this.getDate(), province);
		return a.displayDateAddress();
	}

	@Override
	public double computeTax(double income) {
		EnglishTaxPayable a = new EnglishTaxPayable();
		return a.taxPayable(income);
	}

	@Override
	public String createBodyLetter(double taxPayable, String province,
			String name, String gender) {
		EnglishLetterBody a = new EnglishLetterBody();
		return a.displaySalutation(name, gender, province) + a.displayBodyLetter(taxPayable, province);
	}
	
}
