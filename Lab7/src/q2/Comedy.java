package q2;

public class Comedy extends Movie {

	public Comedy(String MPAA, String ID, String Title) {
		super(MPAA, ID, Title);
		// TODO Auto-generated constructor stub
	}
	
	public double calcLateFees(int days)
	{
		return days*2.5;
	}

}
