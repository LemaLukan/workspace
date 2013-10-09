package q5;

public class Document {
	private String content;
	Document()
	{
	}
	Document(String con)
	{
		content = con;
	}
	
	public String getContent()
	{
		return content;
	}
	public void setContent(String con)
	{
		content = con;
	}
	public String toString()
	{
		return getContent();
	}
}
