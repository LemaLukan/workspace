import java.io.*;


class A2 {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
    		
int _,__, ___, ____;
public static void main(String argv[]) throws java.io.IOException {
BufferedReader $$$$$$ = new BufferedReader(new FileReader("A2.input"));
A2 _____ = new A2($$$$$$);
_____.yylex();
}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	A2 (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	A2 (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private A2 () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private void yy_do_eof ()
		throws 
java.io.IOException

		{
		if (false == yy_eof_done) {

	BufferedWriter $$$$$$$ = new BufferedWriter(new FileWriter("A2.output"));
	$$$$$$$.append("identifiers: "+____+"\nkeywords: "+__+"\numbers: "+___+"\nlines: "+ (yyline + 1)+ "\nquotedString: "+_+"\n" );
	$$$$$$$.close();
		}
		yy_eof_done = true;
	}
	private final int YYINITIAL = 0;
	private final int $$$$$ = 1;
	private final int yy_state_dtrans[] = {
		0,
		18
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
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
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
		/* 10 */ YY_NOT_ACCEPT,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NOT_ACCEPT,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NOT_ACCEPT,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NOT_ACCEPT,
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
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"22:10,23,22:2,23,22:20,21,22:7,1,22:3,19,2,18:10,22:7,11,15,20,12,5,4,16,20" +
",3,20:2,6,17,14,20:3,9,7,10,13,20,8,20:3,22:6,20:26,22:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,38,
"0,1:2,2,3,1,4,5,1:2,6,7,4,8,1,9,5,10,11,12,13,14,15,16,17,18,19,20,21,22,23" +
",24,25,26,27,28,29,30")[0];

	private int yy_nxt[][] = unpackFromString(31,24,
"1,2,11,3,12,25,12:2,36,31,12:5,37,12,32,4,13,12,16,2,5,-1:27,12,6,12:9,17,1" +
"2:4,-1,12,-1:21,4,13,-1:7,12:16,-1,12,-1:4,15:20,7,15:2,-1,8,-1:23,10,-1:40" +
",13,-1:6,15:20,14,15:2,-1:3,12:7,6,12:8,-1,12,-1:3,1,19,2:21,5,-1,21,-1:25," +
"12:9,6,12:6,-1,12,-1:5,9,-1:24,12:2,6,12:13,-1,12,-1:6,12:3,6,12:5,6,12:6,-" +
"1,12,-1:6,12:11,6,12:4,-1,12,-1:6,12:3,26,12:7,20,12:4,-1,12,-1:6,12:4,22,1" +
"2:11,-1,12,-1:6,12:7,35,23,12:7,-1,12,-1:6,24,12:15,-1,12,-1:6,12:7,22,12:8" +
",-1,12,-1:6,12:6,24,12:9,-1,12,-1:6,12:2,27,12:13,-1,12,-1:6,12:8,28,12:7,-" +
"1,12,-1:6,29,12:15,-1,12,-1:6,12:13,28,12:2,-1,12,-1:6,12:10,30,12:5,-1,12," +
"-1:6,12:6,33,12:9,-1,12,-1:6,12:2,34,12:13,-1,12,-1:3");

	public void yylex ()
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
				yy_do_eof();
 return;
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
						{ ++____;}
					case -4:
						break;
					case 4:
						{ ++___;}
					case -5:
						break;
					case 5:
						{}
					case -6:
						break;
					case 6:
						{ ++__; }
					case -7:
						break;
					case 7:
						{ ++_;}
					case -8:
						break;
					case 8:
						{yybegin($$$$$);}
					case -9:
						break;
					case 9:
						{yybegin(YYINITIAL);}
					case -10:
						break;
					case 11:
						{}
					case -11:
						break;
					case 12:
						{ ++____;}
					case -12:
						break;
					case 13:
						{ ++___;}
					case -13:
						break;
					case 14:
						{ ++_;}
					case -14:
						break;
					case 16:
						{}
					case -15:
						break;
					case 17:
						{ ++____;}
					case -16:
						break;
					case 19:
						{}
					case -17:
						break;
					case 20:
						{ ++____;}
					case -18:
						break;
					case 22:
						{ ++____;}
					case -19:
						break;
					case 23:
						{ ++____;}
					case -20:
						break;
					case 24:
						{ ++____;}
					case -21:
						break;
					case 25:
						{ ++____;}
					case -22:
						break;
					case 26:
						{ ++____;}
					case -23:
						break;
					case 27:
						{ ++____;}
					case -24:
						break;
					case 28:
						{ ++____;}
					case -25:
						break;
					case 29:
						{ ++____;}
					case -26:
						break;
					case 30:
						{ ++____;}
					case -27:
						break;
					case 31:
						{ ++____;}
					case -28:
						break;
					case 32:
						{ ++____;}
					case -29:
						break;
					case 33:
						{ ++____;}
					case -30:
						break;
					case 34:
						{ ++____;}
					case -31:
						break;
					case 35:
						{ ++____;}
					case -32:
						break;
					case 36:
						{ ++____;}
					case -33:
						break;
					case 37:
						{ ++____;}
					case -34:
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
