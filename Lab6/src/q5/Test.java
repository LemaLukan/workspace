package q5;

public class Test {
	public static void main(String args[])
	{
		Email e1 = new Email("bob", "fred", "why am I doing this", "this is really below my skill level and I feel like this is pointless");
		Email e2 = new Email("fred", "bob", "re: why am I doing this", "because you have to regardless of your feelings about the easyness of the assignment");
		File f1 = new File("there is nothing even in here", "C:/hi.txt");
		File f2 = new File("even less", "C:/no.dat");
		
		System.out.println("does email1 contain why? " + ContainsKeyword(e1, "why"));
		System.out.println("does email1 contain even? " + ContainsKeyword(e1, "even"));
		System.out.println("does email2 contain why? " + ContainsKeyword(e2, "why"));
		System.out.println("does email2 contain even? " + ContainsKeyword(e2, "even"));
	

		System.out.println("does File1 contain why? " + ContainsKeyword(f1, "why"));
		System.out.println("does File1 contain even? " + ContainsKeyword(f1, "even"));
		System.out.println("does File2 contain why? " + ContainsKeyword(f2, "why"));
		System.out.println("does File2 contain even? " + ContainsKeyword(f2, "even"));
	}
	
	public static boolean ContainsKeyword(Document docObject, String keyword){
		if (docObject.toString().indexOf(keyword,0) >= 0)
		return true ;
		return false ;
	}
}
