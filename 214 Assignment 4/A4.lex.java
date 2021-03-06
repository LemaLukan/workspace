import java_cup.runtime.*;


class A4Scanner implements Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	A4Scanner (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	A4Scanner (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private A4Scanner () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int $$$$ = 1;
	private final int YYINITIAL = 0;
	private final int $$$$$ = 2;
	private final int yy_state_dtrans[] = {
		0,
		68,
		64
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"13:9,32:2,13:2,32,13:18,32,24,12,13:5,20,16,2,15,23,27,30,1,29:10,28,17,13," +
"25,13:3,5,22,31,14,4,19,11,31,9,31:2,6,26,10,31:3,3,7,8,18,31,21,31:3,13:6," +
"31:26,13:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,88,
"0,1,2,1:6,3,1,4,5,1:6,6,7,5,1:4,7:10,8,1,9,1,10,1,11,12,13,14,15,16,17,18,1" +
"9,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,4" +
"4,45,46,47,48,49,50,7,51,52,53,54,55")[0];

	private int yy_nxt[][] = unpackFromString(56,33,
"1,2,37,43,37:3,47,37,51,37:22,3,-1:35,36,-1:32,66,-1:33,82,71,82:7,-1:2,82," +
"-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:2,67:11,21,67:20,-1:29,19,40,-1:" +
"5,82:9,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:3,4,-1:33,82:7,48" +
",82,-1:2,82,-1:3,82,20,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:30,40,-1:8,54,-1:31" +
",42,-1:31,82:9,-1:2,26,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:26,22,-1:" +
"10,56,-1:37,46,-1:27,82:5,27,82:3,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82" +
",-1,82,-1:26,23,-1:15,5,-1:34,50,-1:25,82:3,28,82:5,-1:2,29,-1:3,82:2,-1,82" +
":2,-1:3,82,-1:2,82,-1,82,-1:26,24,-1:13,6,-1:29,82,30,82:7,-1:2,82,-1:3,82:" +
"2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:10,58,-1:26,82:7,31,82,-1:2,82,-1:3,82:2" +
",-1,82:2,-1:3,82,-1:2,82,-1,82,-1:11,60,-1:25,82,32,82:7,-1:2,82,-1:3,82:2," +
"-1,82:2,-1:3,82,-1:2,82,-1,82,-1:12,7,-1:24,82:7,33,82,-1:2,82,-1:3,82:2,-1" +
",82:2,-1:3,82,-1:2,82,-1,82,-1:2,8,-1:34,82:7,34,82,-1:2,82,-1:3,82:2,-1,82" +
":2,-1:3,82,-1:2,82,-1,82,-1,1,9,10,11,70,82:2,84,82,38,82:2,12,39,82,13,14," +
"15,82:2,16,85,86,17,45,49,87,18,53,19,40,82,3,-1:3,82:8,35,-1:2,82,-1:3,82:" +
"2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:3,25,-1:31,67:11,41,67:20,1,37,69,37:29," +
"3,-1:2,62,-1:33,82:3,72,82:3,44,82,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,8" +
"2,-1,82,-1:4,82:2,52,82:2,77,82:3,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82" +
",-1,82,-1:4,82:4,55,82:4,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1" +
":4,83,82:8,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:6,78,82:" +
"2,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:8,79,-1:2,82,-1:3" +
",82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:6,57,82:2,-1:2,82,-1:3,82:2,-1," +
"82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:9,-1:2,82,-1:3,80,82,-1,82:2,-1:3,82,-1:" +
"2,82,-1,82,-1:4,82:5,59,82:3,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,8" +
"2,-1:4,82:6,61,82:2,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,63" +
",82:8,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:7,65,82,-1:2," +
"82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:6,81,82:2,-1:2,82,-1:3,8" +
"2:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1:4,82:5,73,82:3,-1:2,82,-1:3,82:2,-1,82" +
":2,-1:3,82,-1:2,82,-1,82,-1:4,74,82:8,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:" +
"2,82,-1,82,-1:4,82,75,82:7,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82," +
"-1:4,82:2,76,82:6,-1:2,82,-1:3,82:2,-1,82:2,-1:3,82,-1:2,82,-1,82,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
 return new java_cup.runtime.Symbol(0);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{yybegin($$$$);}
					case -5:
						break;
					case 5:
						{yybegin($$$$$);System.out.println(10);  return new java_cup.runtime.Symbol(10); }
					case -6:
						break;
					case 6:
						{yybegin($$$$$);System.out.println(11);  return new java_cup.runtime.Symbol(11); }
					case -7:
						break;
					case 7:
						{yybegin($$$$$);System.out.println(23);  return new java_cup.runtime.Symbol(23); }
					case -8:
						break;
					case 8:
						{ yybegin($$$$$); }
					case -9:
						break;
					case 9:
						{ System.out.println(19); return new java_cup.runtime.Symbol(19); }
					case -10:
						break;
					case 10:
						{ System.out.println(18); return new java_cup.runtime.Symbol(18); }
					case -11:
						break;
					case 11:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -12:
						break;
					case 12:
						{ System.out.println(1); return new java_cup.runtime.Symbol(1); }
					case -13:
						break;
					case 13:
						{ System.out.println(16); return new java_cup.runtime.Symbol(16); }
					case -14:
						break;
					case 14:
						{ System.out.println(15); return new java_cup.runtime.Symbol(15); }
					case -15:
						break;
					case 15:
						{ System.out.println(12); return new java_cup.runtime.Symbol(12); }
					case -16:
						break;
					case 16:
						{ System.out.println(14); return new java_cup.runtime.Symbol(14); }
					case -17:
						break;
					case 17:
						{ System.out.println(13); return new java_cup.runtime.Symbol(13); }
					case -18:
						break;
					case 18:
						{ System.out.println(17); return new java_cup.runtime.Symbol(17); }
					case -19:
						break;
					case 19:
						{System.out.println(26); return new java_cup.runtime.Symbol(26, yytext()); }
					case -20:
						break;
					case 20:
						{ System.out.println(2); return new java_cup.runtime.Symbol(2); }
					case -21:
						break;
					case 21:
						{ System.out.println(24); return new java_cup.runtime.Symbol(24, yytext()); }
					case -22:
						break;
					case 22:
						{ System.out.println(22); return new java_cup.runtime.Symbol(22); }
					case -23:
						break;
					case 23:
						{ System.out.println(21); return new java_cup.runtime.Symbol(21); }
					case -24:
						break;
					case 24:
						{ System.out.println(20); return new java_cup.runtime.Symbol(20); }
					case -25:
						break;
					case 25:
						{ yybegin($$$$);}
					case -26:
						break;
					case 26:
						{ System.out.println(8); return new java_cup.runtime.Symbol(8); }
					case -27:
						break;
					case 27:
						{ System.out.println(10); return new java_cup.runtime.Symbol(10); }
					case -28:
						break;
					case 28:
						{ System.out.println(11); return new java_cup.runtime.Symbol(11); }
					case -29:
						break;
					case 29:
						{ System.out.println(5); return new java_cup.runtime.Symbol(5); }
					case -30:
						break;
					case 30:
						{ System.out.println(3); return new java_cup.runtime.Symbol(3); }
					case -31:
						break;
					case 31:
						{ System.out.println(9); return new java_cup.runtime.Symbol(9); }
					case -32:
						break;
					case 32:
						{ System.out.println(4); return new java_cup.runtime.Symbol(4); }
					case -33:
						break;
					case 33:
						{ System.out.println(7); return new java_cup.runtime.Symbol(7); }
					case -34:
						break;
					case 34:
						{ System.out.println(6); return new java_cup.runtime.Symbol(6); }
					case -35:
						break;
					case 35:
						{ System.out.println(23); return new java_cup.runtime.Symbol(23); }
					case -36:
						break;
					case 37:
						{}
					case -37:
						break;
					case 38:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -38:
						break;
					case 39:
						{ System.out.println(1); return new java_cup.runtime.Symbol(1); }
					case -39:
						break;
					case 40:
						{System.out.println(26); return new java_cup.runtime.Symbol(26, yytext()); }
					case -40:
						break;
					case 41:
						{ System.out.println(24); return new java_cup.runtime.Symbol(24, yytext()); }
					case -41:
						break;
					case 43:
						{}
					case -42:
						break;
					case 44:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -43:
						break;
					case 45:
						{ System.out.println(1); return new java_cup.runtime.Symbol(1); }
					case -44:
						break;
					case 47:
						{}
					case -45:
						break;
					case 48:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -46:
						break;
					case 49:
						{ System.out.println(1); return new java_cup.runtime.Symbol(1); }
					case -47:
						break;
					case 51:
						{}
					case -48:
						break;
					case 52:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -49:
						break;
					case 53:
						{ System.out.println(1); return new java_cup.runtime.Symbol(1); }
					case -50:
						break;
					case 55:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -51:
						break;
					case 57:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -52:
						break;
					case 59:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -53:
						break;
					case 61:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -54:
						break;
					case 63:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -55:
						break;
					case 65:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -56:
						break;
					case 69:
						{}
					case -57:
						break;
					case 70:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -58:
						break;
					case 71:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -59:
						break;
					case 72:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -60:
						break;
					case 73:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -61:
						break;
					case 74:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -62:
						break;
					case 75:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -63:
						break;
					case 76:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -64:
						break;
					case 77:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -65:
						break;
					case 78:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -66:
						break;
					case 79:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -67:
						break;
					case 80:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -68:
						break;
					case 81:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -69:
						break;
					case 82:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -70:
						break;
					case 83:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -71:
						break;
					case 84:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -72:
						break;
					case 85:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -73:
						break;
					case 86:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -74:
						break;
					case 87:
						{ System.out.println(25); return new java_cup.runtime.Symbol(25, yytext()); }
					case -75:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
