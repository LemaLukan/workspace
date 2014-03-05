import java.io.*;
%%
%{    		
int quotes, keywords, numbers, identifiers;
public static void main(String argv[]) throws java.io.IOException {
BufferedReader read = new BufferedReader(new FileReader("A2.input"));
A2 lex = new A2(read);
lex.yylex();
}
%}
%type void
%class A2
%eof{
	BufferedWriter write = new BufferedWriter(new FileWriter("A2.output"));
	write.append("identifiers: "+identifiers+"\nkeywords: "+keywords+"\nnumbers: "+numbers+"\nlines: " + yyline+ "\nquotedString: "+quotes+"\n");
	write.close();
%eof}
%eofthrow{
java.io.IOException
%eofthrow}
%eofval{ return;
%eofval}
%line
QUOTES = \"[^\"]*\"
KEYWORDS = IF|ELSE|WRITE|READ|RETURN|BEGIN|END|MAIN|INT|REAL
NUMBERS = [0-9]+\.[0-9]+|[0-9]+
IDENTIFIERS = [a-zA-Z][a-zA-Z0-9]*
%state COMMENTS
%%
<COMMENTS> "**/" {yybegin(YYINITIAL);}
<YYINITIAL> "/**" {yybegin(COMMENTS);}
<YYINITIAL> {QUOTES} { quotes++;}
<YYINITIAL> {KEYWORDS} { keywords++; }
<YYINITIAL> {NUMBERS} { numbers++;}
<YYINITIAL> {IDENTIFIERS} { identifiers++;}
\r|\n {}
. {}
