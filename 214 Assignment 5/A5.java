import java.util.*;
import java_cup.runtime.*;
import java.io.*;
public class A5
{
	static ArrayList<Symbol> tokens = new ArrayList<Symbol>();

	private static boolean program(int index)
	{
		// Program to check goes here.
		Result store;
		int i = index;
		if (!(store = methodDeclaration(i)).match)
		{
			return false;
		}
		i = store.end + 1;
		if (tokens.get(i).sym == 0)
		{
			return true;
		}
		else
		{
			return program(i);
		}
	}
	private static Result methodDeclaration(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = type(i)).match)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym == 11) // MAIN
		{
			i++;
			if (tokens.get(i).sym != 3) // IDENTIFIER
			{
				store.end = i;
				return store;
			}
		}
		else if (tokens.get(i).sym != 3) // IDENTIFIER
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 16) // (
		{
			store.end = i;
			return store;
		}
		i++;
		if (!(store = formalParameters(i)).match)
		{
			store.start = index;
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym != 17) // )
		{
			store.end = i;
			return store;
		}
		i++;
		if (!((store = block(i)).match))
		{
			store.start = index;
			return store;
		}
		store.match = true;
		return store; 
	}
	private static Result formalParameters(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym == 17) // )
		{
			store.end = i -1;
			store.match = true;
			return store;
		}
		if (!(store = formalParameter(i)).match)
		{
			store.start = index;
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym != 15)
		{
			return store;
		}
		i++;
		if (!(store = formalParameters(i)).match)
		{
			store.start = index;
			return store;
		}
		return store;
	}
	private static Result formalParameter(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = type(i)).match)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym == 3)
		{
			store.end = i;
			store.match = true;
			return store;
		}
		store.end = i;
		return store;
	}
	private static Result type(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym == 12 || tokens.get(i).sym == 13 || tokens.get(i).sym == 26)
		{
			store.match = true;
		}
		return store;
	}
	private static Result block(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym != 9)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym == 10)
		{
			store.match = true;
			store.end = i;
			return store;
		}
		if (!(store = statements(i)).match)
		{
			store.start = index;
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym == 10)
		{
			store.match = true;
		}
		store.end = i;
		return store;
	}
	private static Result statements(int index)
	{
		Result store = new Result(index, index, true);
		int i = index;
		if (!(store = statement(i)).match)
		{
			return store;
		}
		i = store.end + 1;
		if (!(store = statements(i)).match)
		{
			store.end -= 1;
		}
		store.match = true;
		return store;

	}
	private static Result statement(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if ((store = block(i)).match)
		{
			return store;
		}
		else if ((store = variableDeclaration(i)).match)
		{
			return store;
		}
		else if ((store = assignment(i)).match)
		{
			return store;
		}
		else if ((store = returnStatement(i)).match)
		{
			return store;
		}
		else if ((store = ifStatement(i)).match)
		{
			return store;
		}
		else if ((store = writeStatement(i)).match)
		{
			return store;
		}
		else if ((store = readStatement(i)).match)
		{
			return store;
		}
		// store.end will be when read failed, might not be helpful in debugging.
		return store;

	}
	private static Result variableDeclaration(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = type(i)).match)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym != 3)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym == 14)
		{
			store.match = true;
			store.end = i;
			return store;
		}
		if ((store = assignment(i-1)).match)
		{
			store.match = true;
			store.start = index;
			return store;
		}
		return store;

	}
	private static Result assignment(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym != 3)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym != 22)
		{
			store.end = i;
			return store;
		}
		i++;
		if (!(store = arithmeticExpression(i)).match)
		{
			store.start = index;
			return store;
		}
		store.start = index;
		i = store.end + 1;
		if (tokens.get(i).sym == 14)
		{
			store.match = true;
		}
		store.end = i;
		return store;

	}
	private static Result returnStatement(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym != 8)
		{
			return store;
		}
		i++;
		if (!(store = arithmeticExpression(i)).match)
		{
			store.start = index;
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym == 14)
		{
			store.match = true;
		}
		store.end = i;
		return store;

	}
	private static Result ifStatement(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym != 4)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym != 16)
		{
			store.end = i;
			return store;
		}
		i++;
		if (!(store = booleanExpression(i)).match)
		{
			store.start = i;
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym != 17)
		{
			store.end = i;
			return store;
		}
		i++;
		if ((store = statement(i)).match)
		{
			i = store.end;
			store.match = true;
			if (tokens.get(i+1).sym == 5)
			{
				store.match = false;
				if ((store = statement(i+2)).match)
				{
					store.start = index;
					store.match = true;
				}
				return store;
			}
		}
		store.start = index;
		return store;

	}
	private static Result writeStatement(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym != 6)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym != 16)
		{
			store.end = i;
			return store;
		}
		i++;
		if (!(store = arithmeticExpression(i)).match)
		{
			store.start = index;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 15)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 2)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 17)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym == 14)
		{
			store.match = true;
		}
		store.end = i;
		return store;

	}
	private static Result readStatement(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym != 7)
		{
			return store;
		}
		i++;
		if (tokens.get(i).sym != 16)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 3)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 15)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 2)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym != 17)
		{
			store.end = i;
			return store;
		}
		i++;
		if (tokens.get(i).sym == 14)
		{
			store.match = true;
		}
		store.end = i;
		return store;

	}
	private static Result arithmeticExpression(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = multiplicationExpression(i)).match)
		{
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym != 18 && tokens.get(i).sym != 19)
		{
			store.match = true;
			store.end = i-1;
			return store;
		}
		i++;
		if ((store = arithmeticExpression(i)).match)
		{
			store.start = index;
			store.match = true;
		}
		return store;

	}
	private static Result multiplicationExpression(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = primaryExpression(i)).match)
		{
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym != 21 && tokens.get(i).sym != 20)
		{
			store.match = true;
			store.end = i - 1;
			return store;
		}
		i++;
		if ((store = multiplicationExpression(i)).match)
		{
			store.match = true;
		}
		i = store.end;
		return store;

	}
	private static Result primaryExpression(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (tokens.get(i).sym == 25)
		{
			store.match = true;
			return store;
		}
		if (tokens.get(i).sym == 16) // (
		{
			i++;
			if (!(store = arithmeticExpression(i)).match)
			{
				store.end = i;
				return store;
			}
			i = store.end + 1;
			if (tokens.get(i).sym == 17)
			{
				store.match = true;
			}
			store.end = i;
			return store;
		}
		if (tokens.get(i).sym == 3 && tokens.get(i+1).sym != 16)
		{
			store.match = true;
			return store;
		}
		i = i + 2;
		if (tokens.get(i).sym == 17)
		{
			store.match = true;
			return store;
		}
		if ((store = realParameters(i)).match)
		{
			if (tokens.get(store.end + 1).sym == 17)
			{
				store.match = true;
			}
			store.end++;
			return store;
		}
		return store;

	}
	private static Result booleanExpression(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = arithmeticExpression(i)).match)
		{
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym == 23 || tokens.get(i).sym == 24)
		{
			i++;
			if ((store = arithmeticExpression(i)).match)
			{
				i = store.end;
				store.match = true;
				store.start = index;
			}
			store.end = i;
			return store;
		}
		return store;

	}
	private static Result realParameters(int index)
	{
		Result store = new Result(index, index, false);
		int i = index;
		if (!(store = arithmeticExpression(i)).match)
		{
			return store;
		}
		i = store.end + 1;
		if (tokens.get(i).sym == 15)
		{
			i++;
			if ((store = realParameters(i)).match)
			{
				store.start = index;
				i = store.end;
				store.match = true;
			}
			store.end = i;
			return store;
		}	
		store.match = true;
		return store;

	}
	public static void main(String[] args) {
		try {
			//construct the token array
			A5Scanner scanner = new A5Scanner(new FileInputStream(new File("A5.tiny")));
			Symbol token = scanner.next_token();
			for (; token.sym != A5Sym.EOF;) {
				System.out.println(token);
				tokens.add(token);
				token = scanner.next_token();
			}
			tokens.add(token);
			boolean legal;
			if (program(0))
				legal = true;
			else
				legal = false;
			System.out.println(legal);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static class Result
	{
		public int start, end;
		public boolean match;
		public Result(int start, int end, boolean match)
		{
			this.start = start;
			this.end = end;
			this.match = match;
		}
	}

}

