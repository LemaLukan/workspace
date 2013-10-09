package badCode;

public class EnglishLetterBody extends LetterBody {

	@Override
	public String displayBodyLetter(double taxPayable, String province) {
		String result = "Your tax is $" + taxPayable + "\n\n";
		result += "Yours sincerely"; 
		return result;
	}

	@Override
	public String displaySalutation(String name, String gender, String province) {
		String result = "";
		if (gender.equals("male")){
			   result += "Dear Mr " + name + ",\n";
			} else{
				result += "Dear Ms " + name + ",\n";	
			}
		return result;
	}

}
