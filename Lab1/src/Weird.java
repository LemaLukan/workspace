
public class Weird {
	public static void main(String[] args) {
		int i;
		for (i = 0; i <= 1000; ++i)
			if (isPrime(i))
			{
				System.out.println("primes are funny");
			}
			else
			{
				System.out.println("so silly" + i);
			}
	}
	public static boolean isPrime (int input) {
		int check = 0;
		for (int i = 0; i <= 1000; i++)
		{
			if (check == 1)
			{
				return true;
			}
		}
		return false;
	}
}
