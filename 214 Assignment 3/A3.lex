import java_cup.runtime.*;
%%
%implements Scanner
%type Symbol
%function next_token
%class A3_103693282_Scanner
%eofval{ return null;
%eofval}
$ = \"+[^\"]*\"
$$ = [0123456789]*\.[0123456789]*|[01234567899]+
$$$ = [abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ][abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789]*
%state $$$$, $$$$$
%%

<YYINITIAL> "/**" {yybegin($$$$);}
<YYINITIAL> "REAL" {yybegin($$$$$); return new Symbol(13); }
<YYINITIAL> "STRING" {yybegin($$$$$); return new Symbol(26); }
<YYINITIAL> "INT" {yybegin($$$$$); return new Symbol(12); }

<$$$$$> {$} { return new Symbol(2); }
<$$$$> "**/" {yybegin($$$$$);}
<$$$$$> "/**" {yybegin($$$$);}
<$$$$$> "*" { return new Symbol(20); }
<$$$$$> "READ" { return new Symbol(7); }
<$$$$$> "ELSE" { return new Symbol(5); }
<$$$$$> "+" { return new Symbol(18); }
<$$$$$> ")" { return new Symbol(17); }
<$$$$$> "INT" { return new Symbol(12); }
<$$$$$> ";" { return new Symbol(14); }
<$$$$$> "END" { return new Symbol(10); }
<$$$$$> "RETURN" { return new Symbol(8); }
<$$$$$> "IF" { return new Symbol(4); }
<$$$$$> "(" { return new Symbol(16); }
<$$$$$> "WRITE" { return new Symbol(6); }
<$$$$$> "BEGIN" { return new Symbol(9); }
<$$$$$> "STRING" { return new Symbol(26); }
<$$$$$> "," { return new Symbol(15); }
<$$$$$> "!=" { return new Symbol(24); }
<$$$$$> "/" { return new Symbol(21); }
<$$$$$> "MAIN" { return new Symbol(11); }
<$$$$$> "-" { return new Symbol(19); }
<$$$$$> ":=" { return new Symbol(22); }
<$$$$$> "REAL" { return new Symbol(13); }
<$$$$$> "==" { return new Symbol(23); }
<$$$$$> {$$} {return new Symbol(25); }
<$$$$$> {$$$} { return new Symbol(3); }
\r|\n {}
. {}
