import java.io.*;
import java.util.*;
import java.util.regex.*;

public class A12 {

	public static void main(String[] args) throws IOException {
		List $ = Arrays.asList("IF", "ELSE", "WRITE", "READ", "RETURN", "BEGIN", "END", "MAIN", "INT", "REAL");
		String _ = "", __ = "";
		HashSet $$$$ = new HashSet();
		BufferedReader $$ = new BufferedReader(new FileReader(args[0]));
        BufferedWriter $$$ = new BufferedWriter(new FileWriter("A1.output"));
        while ((_ = $$.readLine()) != null)
        {
        	__ += _;
        }
        __ = __.replaceAll("\".*\"", "");
        Pattern ___ = Pattern.compile("\\b[a-zA-Z][a-zA-Z0-9]*");
        Matcher ____ = ___.matcher(__);
        while (____.find())
        {
        	_ = ____.group();
        	if (!$.contains(_))
        	{	$$$$.add(_);
        	}
        }
        $$$.append("identifiers:"+$$$$.size());
        $$$.close();
	}
}
