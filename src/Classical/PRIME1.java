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

class PRIME1 {
    String       PROB_NAME;
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = true;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        PRIME1 program = new PRIME1(args);
        program.begin();
        program.end();
    }

    public void begin() {
        int T = i_scan.nextInt(); // number of test cases;
        for (int count = 0; count < T; count++) {
            int m = i_scan.nextInt();
            int n = i_scan.nextInt();
            Boolean is_prime = true;
            if (m <= 1)
                m = 2;

            for (int p = m; p <= n; p++) {
                is_prime = true;
                int sqrt = (int) Math.sqrt(p);
                for (int q = 2; q <= sqrt; q++) {
                    if (p % q == 0) {
                        is_prime = false;
                        break;
                    }
                }
                if (is_prime) {
                    o_writ.printLine(p);
                }
            }
            if (count < T - 1) {
                o_writ.printLine();
            }
        }

    }

    public PRIME1(String[] args) {
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
