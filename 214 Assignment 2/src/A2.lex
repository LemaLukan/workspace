import java.io.*;
import java.util.*;
%%
%{    

	public static void main(String argv[]) throws java.io.IOException {
	  A2 yy = new A2(new BufferedReader(new FileReader("A2.input")));
	  while (true){
	     yy.yylex();
        }
      }
%}
%notunix
%type void
%class A2
%eofval{ return;
%eofval}

IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_]*

%%
"int" { System.out.println("INT recognized");}
{IDENTIFIER} { System.out.println("ID is ..." + yytext());}
\r|\n {}
. {}