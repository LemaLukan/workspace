import java.io.*;
import java.util.*;


public class A11 {

	public static void main(String[] args) throws IOException {
		List<String> keywords = Arrays.asList("IF", "ELSE", "WRITE", "READ", "RETURN", "BEGIN", "END", "MAIN", "INT", "REAL");
		String line = "", totalLine = "";
		HashSet<String> wordsFound = new HashSet<String>();
		BufferedReader read = new BufferedReader(new FileReader(args[0]));
        BufferedWriter write = new BufferedWriter(new FileWriter("A1.output"));
        while ((line = read.readLine()) != null)
        {
        	if (line.contains("\""))
        	{
        		int firstIndex = line.indexOf("\"");
        		int LastIndex = line.lastIndexOf("\"");
        		int length = line.length();
        		line = line.substring(0, firstIndex-1) + line.substring(LastIndex+1, length);
        	}
        	totalLine += line;
        }
        StringTokenizer st = new StringTokenizer(totalLine, " \t\n(),;+-*/:=!");
        while (st.hasMoreElements())
        {
        	line = st.nextToken();
        	if (!keywords.contains(line))
        	{
        		Character.isLetter(line.charAt(0));
        		wordsFound.add(line);
        	}
        }
        write.append("identifiers:"+wordsFound.size());
        write.close();
        read.close();
	}
}