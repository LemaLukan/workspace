package assignment1;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class MyDateTest extends TestCase {

	MyDate d1;
	MyDate d2;
	MyDate d3;

	MyDate d4;
	MyDate d5;
	MyDate d6;
	@Before
	public void setUp() throws Exception {
		try
		{
			d1 = new MyDate("26/05/1999");
			d2 = new MyDate("08/06/1994");
			d3 = new MyDate("15/03/2000");
			d4 = new MyDate("25/05/1999");
			d5 = new MyDate("25/01/2000");
			d6 = new MyDate("26/05/1999");
			
		}
		catch (Exception e)
		{
			System.out.println("Should not have thrown exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testConstructor() {
		assertEquals(26,d1.getDay());
		assertEquals(05,d1.getMonth());
		assertEquals(1999,d1.getYear());
		MyDate d7;
		// Day is a number.
		try 
		{
			d7 = new MyDate("x5/12/2008");
			fail("Exception was Expected.");
		}
		catch (Exception e)
		{
			System.out.println("Day Exception did correctly fire.");
		}
		// Month is a number.
		try 
		{
			d7 = new MyDate("25/1a/2008");
			fail("Exception was Expected.");
		}
		catch (Exception e)
		{
			System.out.println("Month Exception did correctly fire.");			
		}
		// Year is a number.
		try 
		{
			d7 = new MyDate("25/12/200h");
			fail("Exception was Expected.");
		}
		catch (Exception e)
		{
			System.out.println("Year Exception did correctly fire.");			
		}
		// Month is less than or equal to 12.
		try 
		{
			d7 = new MyDate("25/19/2008");
			fail("Exception was Expected.");
		}
		catch (Exception e)
		{
			System.out.println("<= Exception did correctly fire.");			
		}
		// Month is greater than or equal to 01.
		try 
		{
			d7 = new MyDate("25/00/200h");
			fail("Exception was Expected.");
		}
		catch (Exception e)
		{
			System.out.println(">= Exception did correctly fire.");			
		}
	}
	public void testLessThan()
	{
		assertEquals(true,d4.lessThan(d1));
		assertEquals(false,d4.lessThan(d2));
		assertEquals(true,d4.lessThan(d3));


		assertEquals(false,d5.lessThan(d1));
		assertEquals(false,d5.lessThan(d2));
		assertEquals(true,d5.lessThan(d3));
		

		assertEquals(false,d6.lessThan(d1));
		assertEquals(false,d6.lessThan(d2));
		assertEquals(true,d6.lessThan(d3));	
	}
	public void testToString()
	{
		assertEquals("May 25, '99", d4.toString());
		assertEquals("January 25, '00", d5.toString());
		assertEquals("May 26, '99", d6.toString());
	}
}
