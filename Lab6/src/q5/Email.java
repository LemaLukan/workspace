package q5;

public class Email extends Document 
{
	private String sender, recipient, title;
	Email()
	{
		super();
	}
	Email(String con, String sen, String rec, String tit) 
	{
		super(con);
		sender = sen;
		recipient = rec;
		title = tit;
		
	}
	public String getSender()
	{
		return sender;
	}
	public void setSender(String send)
	{
		sender = send;
	}
	public String getRecipient()
	{
		return recipient;
	}
	public void setRecipient(String rec)
	{
		recipient = rec;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String tit)
	{
		title = tit;
	}
	public String toString()
	{
		return sender + " to " + recipient + " subject " + title + " \n" + super.toString();
	}
}
