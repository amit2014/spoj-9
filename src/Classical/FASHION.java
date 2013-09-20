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
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class FASHION {
    String       PROB_NAME;
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        FASHION program = new FASHION(args);
        program.begin();
        program.end();
    }

    public void begin(){
        int T = i_scan.nextInt();
        for(int cases = 0; cases < T; cases++){
            int[] M = new int[11];
            int[] W = new int[11];
            int ans = 0;
            int N = i_scan.nextInt();
            
            for(int i = 0; i<N; i++){
                M[i_scan.nextInt()]++;
            }
            for(int i = 0; i<N; i++){
                W[i_scan.nextInt()]++;
            }
            
            int m = 10; int w = 10;
            while(m>=0 && w >= 0){
                if(M[m]==0){
                    m--; continue;
                }
                if(W[w]==0){
                    w--; continue;
                }
                if(M[m]>W[w]){
                    ans+=m*w*W[w];
                    M[m]-=M[m]-W[w];
                    w--;
                }else if(M[m] < W[w]){
                    ans+=m*w*M[m];
                    W[w]-=W[w]-M[m];
                    m--;
                }else{
                    ans+=m*w*W[w];
                    m--;
                    w--;
                }
            }
            
            o_writ.printLine(ans);
        }
    }

    public FASHION(String[] args) {
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
