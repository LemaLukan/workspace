import java.io.*;
import java.util.*;

public class A11 {

	public static void main(String[] args) throws IOException {
		List<String> $ = Arrays.asList("IF", "ELSE", "WRITE", "READ", "RETURN", "BEGIN", "END", "MAIN", "INT", "REAL");
		String _ = "", __ = "";
		HashSet<String> $$ = new HashSet<String>();
		BufferedReader ___ = new BufferedReader(new FileReader(args[0]));
        BufferedWriter ____ = new BufferedWriter(new FileWriter("A1.output"));
        while ((_ = ___.readLine()) != null)
        {
        	if (_.contains("\""))
        	{
        		int $$$ = _.indexOf("\"");
        		int $$$$ = _.lastIndexOf("\"");
        		int $$$$$ = _.length();
        		_ = _.substring(0, $$$-1) + _.substring($$$$+1, $$$$$);
        	}
        	__ += _;
        }
        StringTokenizer ______ = new StringTokenizer(__, " \t\n(),;+-*/:=!");
        while (______.hasMoreElements())
        {
        	_ = ______.nextToken();
        	if (!$.contains(_) && Character.isLetter(_.charAt(0)))
        	{
        		$$.add(_);
        	}
        }
        ____.append("identifiers:"+$$.size());
        ____.close();
	}
}