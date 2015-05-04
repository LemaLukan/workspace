import java_cup.runtime.*;

%%
%implements Scanner
%type java_cup.runtime.Symbol
%function next_token
%class A4Scanner
%eofval{ return new java_cup.runtime.Symbol(0);
%eofval}
$ = \"+[^\"]*\"
$$ = [0123456789]*\.[0123456789]*|[01234567899]+
$$$ = [abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ][abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789]*
%state $$$$, $$$$$
%%

<YYINITIAL> "/**" {yybegin($$$$);}
<YYINITIAL> "REAL" {yybegin($$$$$);System.out.println(11);  return new java_cup.runtime.Symbol(11); }
<YYINITIAL> "STRING" {yybegin($$$$$);System.out.println(23);  return new java_cup.runtime.Symbol(23); }
<YYINITIAL> "INT" {yybegin($$$$$);System.out.println(10);  return new java_cup.runtime.Symbol(10); }

<$$$$$> {$} { System.out.println(24); return new java_cup.runtime.Symbol(24, yytext()); }
<$$$$> "**/" { yybegin($$$$$); }
<$$$$$> "/**" { yybegin($$$$);}
<$$$$$> "*" { System.out.println(18); return new java_cup.runtime.Symbol(18); }
<$$$$$> "READ" { System.out.println(5); return new java_cup.runtime.Symbol(5); }
<$$$$$> "ELSE" { System.out.println(3); return new java_cup.runtime.Symbol(3); }
<$$$$$> "+" { System.out.println(16); return new java_cup.runtime.Symbol(16); }
<$$$$$> ")" { System.out.println(15); return new java_cup.runtime.Symbol(15); }
<$$$$$> "INT" { System.out.println(10); return new java_cup.runtime.Symbol(10); }
<$$$$$> ";" { System.out.println(12); return new java_cup.runtime.Symbol(12); }
<$$$$$> "END" { System.out.println(8); return new java_cup.runtime.Symbol(8); }
<$$$$$> "RETURN" { System.out.println(6); return new java_cup.runtime.Symbol(6); }
<$$$$$> "IF" { System.out.println(2); return new java_cup.runtime.Symbol(2); }
<$$$$$> "(" { System.out.println(14); return new java_cup.runtime.Symbol(14); }
<$$$$$> "WRITE" { System.out.println(4); return new java_cup.runtime.Symbol(4); }
<$$$$$> "BEGIN" { System.out.println(7); return new java_cup.runtime.Symbol(7); }
<$$$$$> "STRING" { System.out.println(23); return new java_cup.runtime.Symbol(23); }
<$$$$$> "," { System.out.println(13); return new java_cup.runtime.Symbol(13); }
<$$$$$> "!=" { System.out.println(22); return new java_cup.runtime.Symbol(22); }
<$$$$$> "/" { System.out.println(19); return new java_cup.runtime.Symbol(19); }
<$$$$$> "MAIN" { System.out.println(9); return new java_cup.runtime.Symbol(9); }
<$$$$$> "-" { System.out.println(17); return new java_cup.runtime.Symbol(17); }
<$$$$$> ":=" { System.out.println(20); return new java_cup.runtime.Symbol(20); }
<$$$$$> "REAL" { System.out.println(11); return new java_cup.runtime.Symbol(11); }
<$$$$$> "==" { System.out.println(21); return new java_cup.runtime.Symbol(21); }
<$$$$$> {$$} {System.out.println(26); return new java_cup.runtime.Symbol(26, yytext()); }
<$$$$$> {$$$} { System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
\r|\n|\t|" " {}
<$$$$$> . { System.out.println(1); return new java_cup.runtime.Symbol(1); }
. {}
