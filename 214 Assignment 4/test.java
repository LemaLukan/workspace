import java.io.*;
import java.lang.*;
public class test
{
	public static void main(String args[]) throws Exception
	{
		java.lang.Runtime rt = java.lang.Runtime.getRuntime();
		// Start a new process: UNIX command ls
		java.lang.Process p = rt.exec("ls");
		java.io.InputStream is = p.getInputStream();
		java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
		// And print each line
		String s = null;
		BufferedWriter breaking = new BufferedWriter(new FileWriter("/tmp/nothinghere"));
		while ((s = reader.readLine()) != null) {
			breaking.write(s);
			System.out.println(s);
		}
		breaking.close();
		is.close();
	}
}
