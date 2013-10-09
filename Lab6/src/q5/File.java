package q5;

public class File extends Document {
	private String pathname;
	File()
	{
		super();
	}
	File(String con, String path) {
		super(con);
		pathname = path;
	}
	
	public String getPath()
	{
		return pathname;
	}
	public void setPath(String p)
	{
		pathname = p;
	}
	
	public String toString()
	{
		return "file at " + getPath() + " \n" + super.toString();
	}

}
