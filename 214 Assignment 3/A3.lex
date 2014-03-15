import java_cup.runtime.*;
%%
%implements Scanner
%type Symbol
%function next_token
%class A3Scanner
%eofval{ return null;
%eofval}
QUOTE = \"+[^\"]*\"
NUMBER = [0-9]*\.[0-9]*|[0-9]+
IDENT = [a-zA-Z][a-zA-Z0-9]*
%state COMMENT
%%

<YYINITIAL> {QUOTE} { return new Symbol(A3Symbol.QUOTE); }
<COMMENT> "**/" {yybegin(YYINITIAL);}
<YYINITIAL> "/**" {yybegin(COMMENT);}
<YYINITIAL> "*" { return new Symbol(A3Symbol.TIMES); }
<YYINITIAL> "READ" { return new Symbol(A3Symbol.READ); }
<YYINITIAL> "ELSE" { return new Symbol(A3Symbol.ELSE); }
<YYINITIAL> "+" { return new Symbol(A3Symbol.PLUS); }
<YYINITIAL> ")" { return new Symbol(A3Symbol.RPAREN); }
<YYINITIAL> "INT" { return new Symbol(A3Symbol.INT); }
<YYINITIAL> ";" { return new Symbol(A3Symbol.SEMI); }
<YYINITIAL> "END" { return new Symbol(A3Symbol.END); }
<YYINITIAL> "RETURN" { return new Symbol(A3Symbol.RETURN); }
<YYINITIAL> "IF" { return new Symbol(A3Symbol.IF); }
<YYINITIAL> "(" { return new Symbol(A3Symbol.LPAREN); }
<YYINITIAL> "WRITE" { return new Symbol(A3Symbol.WRITE); }
<YYINITIAL> "BEGIN" { return new Symbol(A3Symbol.BEGIN); }
<YYINITIAL> "STRING" { return new Symbol(A3Symbol.STRING); }
<YYINITIAL> "," { return new Symbol(A3Symbol.COMMA); }
<YYINITIAL> "!=" { return new Symbol(A3Symbol.NEQUAL); }
<YYINITIAL> "/" { return new Symbol(A3Symbol.DIVIDE); }
<YYINITIAL> "MAIN" { return new Symbol(A3Symbol.MAIN); }
<YYINITIAL> "-" { return new Symbol(A3Symbol.MINUS); }
<YYINITIAL> ":=" { return new Symbol(A3Symbol.CEQUAL); }
<YYINITIAL> "REAL" { return new Symbol(A3Symbol.REAL); }
<YYINITIAL> "==" { return new Symbol(A3Symbol.EEQUAL); }
<YYINITIAL> {NUMBER} {return new Symbol(A3Symbol.NUMBER); }
<YYINITIAL> {IDENT} { return new Symbol(A3Symbol.IDENTIFIER); }
\r|\n {}
. {}
