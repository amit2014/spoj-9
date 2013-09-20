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
class CANDY3 {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) throws IOException{
        CANDY3 program = new CANDY3(args);
        program.begin();
        program.end();
    }

    public void begin() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Long> list;
        int T = Integer.parseInt(br.readLine());
        long ans;
        
        for(int cases=0; cases<T; cases++){
            ans = 0;
            list = new ArrayList<Long>();
            String str = br.readLine();
            str = br.readLine();

            while(str!=null && str.length()!=0){
                list.add(Long.parseLong(str));        
                str = br.readLine();
            }
            if(str==null) break;
            int size = list.size();
            for(int i=0; i<size; i++){
                ans = (ans +list.get(i))%size;
            }
            if(ans==0)o_writ.printLine("YES");
            else o_writ.printLine("NO");
            
        }
    }

    public CANDY3(String[] args) {
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
