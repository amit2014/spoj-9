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
 * TOANDFRO2 accepted at spoj. Trying to speed up further.
 * Reduced 0.01 Seconds! :D
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class TOANDFRO3 {
    OutputWriter   o_writ;
    BufferedReader br;

    public static void main(String[] args) throws IOException {
        TOANDFRO3 program = new TOANDFRO3(args);
        program.begin();
        program.end();
    }

    public void begin() throws IOException{
        int rows = 0;
        int cols = Integer.parseInt(br.readLine());
        String str = null;
        int new_idx;
        while (cols != 0) {
            str = br.readLine();
            // char[] array = str.toCharArray();
            int len = str.length();
            char[] ans_array = new char[len];
            rows = len / cols;

            for (int i = 0; i < len; i++) {
                if ((i / cols) % 2 == 0) {
                    new_idx = ((i / cols) + rows * (i % cols));
                } else {
                    new_idx = ((i / cols) + rows * (cols - 1 - i % cols));
                }
                ans_array[new_idx] = str.charAt(i);
            }
            for (int i = 0; i < len; i++) {
                o_writ.print(ans_array[i]);
            }
            o_writ.printLine();
            cols = Integer.parseInt(br.readLine());
        }

    }

    public TOANDFRO3(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        o_writ = new OutputWriter(System.out);
    }

    public void end() {
        o_writ.close();
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
