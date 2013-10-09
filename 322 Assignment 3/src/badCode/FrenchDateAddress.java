package badCode;

public class FrenchDateAddress extends DateAddress {
	public FrenchDateAddress(String address, MyDate aDate, String province) {
		super(address, aDate, province);
	}
	public String displayDateAddress() {
		String result = this.getDate().toString() + "\n\n";
		result += this.getAddress() + "\n";
		result += this.getProvince() + "\n\n";
		return result;
	}

}
