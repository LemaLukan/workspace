import java.io.*;
%%
%{    		
int _,__, ___, ____;
public static void main(String argv[]) throws java.io.IOException {
BufferedReader ______ = new BufferedReader(new FileReader("A3.input"));
A3 _____ = new A3(______);
_____.yylex();
}
%}
%type Symbol
%class A3
%eof{
	BufferedWriter _______ = new BufferedWriter(new FileWriter("A3.output"));
	_______.append("numberOfMethods "+____+);
	_______.close();
%eof}
%eofthrow{
java.io.IOException
%eofthrow}
%eofval{ return null;
%eofval}
%line
$ = \"+[^\"]*\"
$$ = [a-zA-Z][a-zA-Z0-9]*
$$$ = [0-9]*\.[0-9]*|[0-9]+
%state $$$$
$$$$$ = IF
$$$$$$ = ELSE
$$$$$$$ = WRITE
$$$$$$$$ = READ
$$$$$$$$$ = RETURN
$$$$$$$$$$ = BEGIN
$$$$$$$$$$$ = END
$$$$$$$$$$$$ = MAIN
$$$$$$$$$$$$$ = INT
$$$$$$$$$$$$$$ = REAL
%%
<$$$$> "**/" {yybegin(YYINITIAL);}
<YYINITIAL> "/**" {yybegin($$$$);}
"IF" { return new Symbol(A3Symbol.IF); }
<YYINITIAL> {$$$$$} { return new Symbol(A3Symbol.IF); }
<YYINITIAL> {$$$} { ++___;}
<YYINITIAL> {$$} { ++____;}
<YYINITIAL> {$} { ++_;}
\r|\n {}
. {}
