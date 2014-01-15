import java.io.*;
%%
%{    		
int quotes,keywords, numbers, id, lines;
public static void main(String argv[]) throws java.io.IOException {
	
BufferedReader read = new BufferedReader(new FileReader("A2.input"));
A2 yy = new A2(read);
yy.yylex();
}
%}
%type void
%class A2
%eof{
BufferedWriter write = new BufferedWriter(new FileWriter("A2.output"));;
write.append("identifiers: "+id+"\nkeywords: "+keywords+"\nnumbers: "+numbers+"\nlines: "+(yyline + 1)+"\nquotedString: "+quotes+"\n");
write.close();
%eof}
%eofthrow{
java.io.IOException
%eofthrow}
%eofval{ return;
%eofval}
%line

QUOTES = \"+[^\"]*\"
KEYWORD = IF|ELSE|WRITE|READ|RETURN|BEGIN|END|MAIN|INT|REAL
IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*
NUMBER = [0-9]*\.[0-9]*|[0-9]+

%state COMMENT

%%

<YYINITIAL> "/**" {yybegin(COMMENT);}
<COMMENT> "**/" {yybegin(YYINITIAL);}
<YYINITIAL> {KEYWORD} { ++keywords; }
<YYINITIAL> {NUMBER} { ++numbers;}
<YYINITIAL> {IDENTIFIER} { ++id;}
<YYINITIAL> {QUOTES} { ++quotes;}
\r|\n {}
. {}