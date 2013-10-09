package q2;

public class Action extends Movie {

	public Action(String MPAA, String ID, String Title) {
		super(MPAA, ID, Title);

	}
	
	public double calcLateFees(int days)
	{
		return days*3;
	}

}
