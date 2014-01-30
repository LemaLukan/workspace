import java.io.*;
%%
%{    		
int _,__, ___, ____;
public static void main(String argv[]) throws java.io.IOException {
BufferedReader $$$$$$ = new BufferedReader(new FileReader("A2.input"));
A2 _____ = new A2($$$$$$);
_____.yylex();
}
%}
%type void
%class A2
%eof{
	BufferedWriter $$$$$$$ = new BufferedWriter(new FileWriter("A2.output"));
	$$$$$$$.append("identifiers: "+____+"\nkeywords: "+__+"\nnumbers: "+___+"\nlines: " + yyline+ "\nquotedString: "+_+"\n");
	$$$$$$$.close();
%eof}
%eofthrow{
java.io.IOException
%eofthrow}
%eofval{ return;
%eofval}
%line
$ = \"+[^\"]*\"
$$ = IF|ELSE|WRITE|READ|RETURN|BEGIN|END|MAIN|INT|REAL
$$$ = [0-9]+\.[0-9]+|[0-9]+
$$$$ = [a-zA-Z][a-zA-Z0-9]*
%state $$$$$
%%
<$$$$$> "**/" {yybegin(YYINITIAL);}
<YYINITIAL> "/**" {yybegin($$$$$);}
<YYINITIAL> {$} { ++_;}
<YYINITIAL> {$$} { ++__; }
<YYINITIAL> {$$$} { ++___;}
<YYINITIAL> {$$$$} { ++____;}
\r|\n {}
. {}
