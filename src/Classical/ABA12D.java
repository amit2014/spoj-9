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
class ABA12D {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        ABA12D program = new ABA12D(args);
        program.begin();
        program.end();
    }

    public void begin() {
        int T = i_scan.nextInt();
        for (int cases = 0; cases < T; cases++){
            int low = i_scan.nextInt();
            int high = i_scan.nextInt();
            o_writ.printLine(count(low,high));
        }
        
    }
    int count(int low, int high){
        int count = 0;
        int[] array = { 1, 4, 9, 16, 25, 64, 289, 729, 1681, 2401, 3481, 4096,
                5041, 7921, 10201, 15625, 17161, 27889, 28561, 29929, 65536,
                83521, 85849, 146689, 262144, 279841, 458329, 491401, 531441,
                552049, 579121, 597529, 683929, 703921, 707281, 734449, 829921};
        for (int i = 0; i < array.length; i++){
            if (array[i]>high) break;
            if(array[i]>=low) count++;
        }
        return count;
    }
    
    void printSequence(){

//        System.out.println("n\tsqrt(n)\tsum\t\tprime");
//        System.out.println("------------------------------------");
        for(int n = 1; n<=1000000; n++){
            boolean isPerfectSquare = false;
            if(Math.pow((int)Math.sqrt(n),2)==n){
                isPerfectSquare = true;
            }
            else{
                continue;
            }
            int sum = 0;
            for(int i = 1; i <= n; i++){
                if(n%i==0) sum+=i;
            }
            
            int sqrt = (int)Math.sqrt(sum);
            boolean isPrime = true;
            for (int j = 2; j<=sqrt; j++){
                if(sum%j==0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                System.out.print("" + n + ", ");
            }
//            if(isPrime)
//                System.out.println("" + n + "\t" + (int)Math.sqrt(n) +"\t"+ sum + "\tYES");
//            else if(isPerfectSquare)
//                System.err.println("" + n + "\t" + (int)Math.sqrt(n) +"\t"+ sum + "\t");
            
        }
    }

    public ABA12D(String[] args) {
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
