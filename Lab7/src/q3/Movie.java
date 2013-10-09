package q3;


public class Movie {
	String rating, idNum, title;
	public Movie(String MPAA, String ID, String Title)
	{
		rating = MPAA;
		idNum = ID;
		title = Title;
	}
	
	public String getRating()
	{
		return rating;
	}
	public void setRating(String rate)
	{
		rating = rate;
	}
	public String getID()
	{
		return idNum;
	}
	public void setID(String id)
	{
		idNum = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTItle(String Tit)
	{
		title = Tit;
	}
	
	public boolean equals(Object o)
	{
		if (!(o instanceof Movie))
		{
			return false;
		}
		else
		{
			Movie m = (Movie)o;
			if (m.idNum.compareTo(idNum) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public double calcLateFees(int days)
	{
		return days*2;
	}
	public String toString()
	{
		return idNum + " " + title + " " + rating;
	}
}
