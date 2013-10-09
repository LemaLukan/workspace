package lab3;

public class Fraction {
	int numerator, denominator;
	public Fraction()
	{
		numerator = 0;
		denominator = 1;
	}
	public Fraction(int num, int denom)
	{
		numerator = num;
		if (denom != 0)
		{
			denominator = denom;
		}
		else
		{
			throw new RuntimeException("denominator is equal to 0");
		}
	}
	public void setNumerator(int num)
	{
		numerator = num;
	}
	public void setDenominator(int denom)
	{
		if (denom != 0)
		{
			denominator = denom;
		}
		else
		{
			throw new RuntimeException("denominator is equal to 0");
		}
	}
	public double fracVal()
	{
		return numerator/denominator;
	}
	public String findSmall()
	{
		int biggest;
		if (numerator >= denominator)
		{
			biggest = gcd(numerator, denominator);
		}
		else
		{
			biggest = gcd(denominator, numerator);
		}
		numerator = numerator/biggest;
		denominator = denominator/biggest;
		return "" + numerator + "/" + denominator;
	}
	private int gcd(int biggest, int smallest)
	{
		int value=biggest%smallest;
		if (value==0)
		{
			return smallest;
		}
		else
		{
			return gcd(smallest, biggest%smallest);
		}
	}
}
