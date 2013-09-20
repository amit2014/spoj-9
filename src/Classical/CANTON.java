package Classical;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.BufferedReader;
import java.util.StringTokenizer;

/**
 * Accepted
 * 
 * For any given (x,i) coordinates in the square grid, the value of N can be
 * found by: if (x+i) is even: N = ((i+x+1)*(i+x+2)/2-x) if (x+i) is odd: N =
 * ((i+x+1)*(i+x+2)/2-i)
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class CANTON {
    String       PROB_NAME;
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        CANTON program = new CANTON(args);
        program.begin();
        program.end();
    }

    public void begin() {
        /* Initialise an array with n(n+1)/2 */
        int[] array = new int[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = (i + 1) * (i + 2) / 2;
        }

        int T = i_scan.nextInt();
        int x, y;
        for (int cases = 0; cases < T; cases++) {
            int N = i_scan.nextInt();

            int diagonal = 0;
            while (N > array[diagonal]) {
                diagonal++;
            }

            if (diagonal % 2 == 0) {// even
                x = array[diagonal] - N;
            } else {// odd
                x = diagonal- (array[diagonal]-N);
            }
            y = diagonal - x;
            
            String[] ans = { "TERM", String.valueOf(N), "IS",
                    String.valueOf(x+1) + "/" + String.valueOf(y+1)};
            o_writ.printLine(ans);
        }
    }

    public CANTON(String[] args) {
        if (args.length > 0 && args[0].equals("Test")) {
            i_scan = new InputScanner(new File("inputs/input_" + PROB_NAME
                    + ".txt"));
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
        StringTokenizer              tokenizer;

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
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
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

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
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
