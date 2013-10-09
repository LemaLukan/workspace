package q2;

public class Drama extends Movie {

	public Drama(String MPAA, String ID, String Title) {
		super(MPAA, ID, Title);
		// TODO Auto-generated constructor stub
	}
	
	public double calcLateFees(int days)
	{
		return super.calcLateFees(days);
	}

}
