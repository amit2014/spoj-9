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
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class ANARC05B {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        ANARC05B program = new ANARC05B(args);
        program.begin();
        program.end();
    }

    public void begin() {
        int len1 = i_scan.nextInt();
        while(len1>0){
            int[] a1 = new int[len1];
            
            for(int i = 0; i< len1; i++){
                a1[i] = i_scan.nextInt();
            }
            
            int len2 = i_scan.nextInt();
            int[] a2 = new int[len2];
            for(int i = 0; i< len2; i++){
                a2[i] = i_scan.nextInt();
            }
            
            o_writ.printLine(maxSum(a1,a2));
            
            len1 = i_scan.nextInt();
        }
    }
    
    int maxSum(int[] a1, int[] a2){
        int i = 0;
        int j = 0;
        int sum1 = 0;
        int sum2 = 0;
        int sumSoFar = 0;
        while(i < a1.length && j < a2.length){
            if(a1[i] < a2[j]){
                sum1+=a1[i];
                i++;
            }
            else if (a1[i] > a2[j]){
                sum2+=a2[j];
                j++;
            }
            else{ // intersection
                sumSoFar += Math.max(sum1,sum2);
                sumSoFar += a1[i];
                i++;
                j++;
                sum1 = 0;
                sum2 = 0;
            }
        }
        
        if(i>=a1.length){
            while (j < a2.length){
                sum2+=a2[j];
                j++;
            }
        }
        
        if(j>=a2.length){
            while (i < a1.length){
                sum1+=a1[i];
                i++;
            }
        }
        
        sumSoFar += Math.max(sum1, sum2);
        
        return sumSoFar;

    }

    public ANARC05B(String[] args) {
        if (args.length > 0 && args[0].equals("Test")) {
            i_scan = new InputScanner(new File(
                    "/home/sagar/code_arena/Eclipse Workspace/InterviewStr/inputs/"
                            + PROB_NAME + "/"+TEST_NAME));
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
        
        public float nextFloat(){
            float num;
            try{
                num = Float.parseFloat(next());
            }catch(NumberFormatException e){
                throw new RuntimeException(e);
            }
            return num;
        }
        
        public float nextLong(){
            long num;
            try{
                num = Long.parseLong(next());
            }catch(NumberFormatException e){
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
