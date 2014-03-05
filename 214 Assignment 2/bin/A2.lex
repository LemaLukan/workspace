import java.io.*;
%%
%{    		
int _,__, ___, ____;
public static void main(String argv[]) throws java.io.IOException {
BufferedReader $$$$$$ = new BufferedReader(new FileReader("A2.input"));
$$$$$$$ _____ = new $$$$$$$($$$$$$);
_____.yylex();
}
%}
%type void
%class $$$$$$$
%eof{
BufferedWriter $$$$$$$ = new BufferedWriter(new FileWriter("A2.output"));;
$$$$$$$.append("identifiers: "+____+"\nkeywords: "+__+"\numbers: "+___+"\nlines: "+(yyline + 1)+"\nquotedString: "+_+"\n");
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
$$$ = [a-zA-Z][a-zA-Z0-9]*
$$$$ = [0-9]*\.[0-9]*|[0-9]+
%state $$$$$
%%
<$$$$$> "**/" {yybegin(YYINITIAL);}
<YYINITIAL> "/**" {yybegin($$$$$);}
<YYINITIAL> {$$} { ++__; }
<YYINITIAL> {$$$$} { ++___;}
<YYINITIAL> {$$$} { ++____;}
<YYINITIAL> {$} { ++_;}
. {}