package q7;

public class Person {
	private String name;
	public Person()
	{
	}
	public Person(String theName)
	{
		name = theName;
	}
	public Person(Person theObject)
	{
		name = theObject.name;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String theName)
	{
		name = theName;
	}
	public String toString()
	{
		return "name " + name;
	}
	public boolean equals(Object other)
	{
		Person n = (Person)other;
		if (n.getName().compareTo(name) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
