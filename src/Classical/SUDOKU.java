package Classical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringTokenizer;

class SUDOKU {
	String PROB_NAME = "";
	String TEST_NAME = "input00.txt";
	InputScanner i_scan;
	OutputWriter o_writ;
	Boolean is_debug = false;
	long start_time = 0;
	long end_time = 0;

	public static void main(String[] args) {
		SUDOKU program = new SUDOKU(args);
		program.begin();
		program.end();
	}

	public void begin() {
		int T = i_scan.nextInt();
		boolean result;
		for (int cases = 0; cases < T; cases++) {
			int[][] board = new int[16][16];
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					board[i][j] = i_scan.nextInt();
				}
			}
			result = solveRecur(board, 0);
			if (result) {
				printBoard(board);
			} else {
				o_writ.printLine("No solution");
			}
		}
	}

	int[][] charify(String[] inputs) {
		int[][] ans = new int[inputs.length][inputs[0].length()];
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[0].length; j++) {
				int value = Integer.parseInt("" + inputs[i].charAt(j));
				if (value < 1 || value > 9)
					value = 0;
				ans[i][j] = value;
			}
		}
		return ans;
	}

	boolean solveRecur(int[][] board, int pos) {
		if (pos > 80)
			return true;
		int x = pos / 9;
		int y = pos % 9;
		if (board[x][y] > 0)
			return solveRecur(board, pos + 1);

		boolean[] possible = getPossible(board, pos);
		for (int i = 1; i <= 9; i++) {
			if (possible[i])
				continue;
			board[x][y] = i;
			if (solveRecur(board, pos + 1)) {
				return true;
			} else {
				board[x][y] = 0;
			}
		}
		return false;
	}

	void printBoard(int[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				o_writ.print(board[i][j]);
				if (j < 8)
					o_writ.print(' ');
			}
			o_writ.printLine();
		}
		// o_writ.printLine();
	}

	boolean[] getPossible(int[][] board, int pos) {
		// forget 0th index, start from 1 up to 9
		boolean[] ans = new boolean[10];
		int x = pos / 9;// row
		int y = pos % 9;// col

		/* row */
		for (int i = 0; i < 9; i++) {
			ans[board[x][i]] = true;
		}
		/* col */
		for (int i = 0; i < 9; i++) {
			ans[board[i][y]] = true;
		}
		/* box */
		int qx = x / 3;
		int qy = y / 3;
		for (int i = 3 * qx; i < 3 * qx + 3; i++) {
			for (int j = 3 * qy; j < 3 * qy + 3; j++) {
				ans[board[i][j]] = true;
			}
		}

		return ans;
	}

	public SUDOKU(String[] args) {
		if (args.length > 0 && args[0].equals("Test")) {
			i_scan = new InputScanner(new File(
					"/home/sagar/code_arena/Eclipse Workspace/InterviewStr/inputs/"
							+ PROB_NAME + "/" + TEST_NAME));
		} else {
			i_scan = new InputScanner(System.in);
		}
		o_writ = new OutputWriter(System.out);
		if (is_debug) {
			start_time = System.currentTimeMillis();
		}
	}

	public void end() {
		if (is_debug) {
			end_time = System.currentTimeMillis();
			o_writ.printLine("Time (milisec): " + (end_time - start_time));
		}
		o_writ.close();
	}

	class InputScanner {
		private final BufferedReader br;
		StringTokenizer tokenizer;

		public InputScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
		}

		public InputScanner(File file) {
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			int num;
			try {
				num = Integer.parseInt(next());
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
			return num;
		}

		public float nextFloat() {
			float num;
			try {
				num = Float.parseFloat(next());
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
			return num;
		}

		public float nextLong() {
			long num;
			try {
				num = Long.parseLong(next());
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
			return num;
		}

		public String nextLine() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken("\n").trim();
		}

	}

	class OutputWriter {
		private final PrintWriter pw;

		public OutputWriter(OutputStream op) {
			pw = new PrintWriter(op);
		}

		public OutputWriter(Writer writer) {
			pw = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					pw.print(' ');
				pw.print(objects[i]);
			}
		}

		public void printLine(Object... objects) {
			print(objects);
			pw.println();
		}

		public void close() {
			pw.close();
		}
	}
}