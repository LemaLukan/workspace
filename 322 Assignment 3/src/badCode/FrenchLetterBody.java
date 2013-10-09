package badCode;

public class FrenchLetterBody extends LetterBody {

	@Override
	public String displayBodyLetter(double taxPayable, String province) {
		String result = "Votre taxe est $" + taxPayable + "\n\n";
		result += "Sincerement votre"; 
		return result;
	}

	@Override
	public String displaySalutation(String name, String gender, String province) {
		String result = "";
		if (gender.equals("male")){
		   result += "Cher Mr " + name + ",\n";
		} else{
			result += "Chere Mme/Melle " + name + ",\n";	
		}
		return result;
	}

}
