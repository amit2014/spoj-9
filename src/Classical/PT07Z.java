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
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Wrong solution, will work only for DAGs :(
 * We need to solve for UDAG
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class PT07Z {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        PT07Z program = new PT07Z(args);
        program.begin();
        program.end();
    }

    public void begin(){
        int T = i_scan.nextInt();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        boolean[] toNodes = new boolean[T+1];
        toNodes[0] = true; 

        for(int i = 1; i<= T-1; i++ ){
            int fromNode = i_scan.nextInt();
            int toNode = i_scan.nextInt();
            toNodes[toNode] = true;
            if(map.containsKey(fromNode)){
                map.get(fromNode).add(toNode);
            }else{
                ArrayList<Integer> newlist = new ArrayList<Integer>();
                newlist.add(toNode);
                map.put(fromNode, newlist);
            }    
        }
        //map generated
        
        int longestPath = 0;
        for (int j = 1; j<=T; j++){
            if(toNodes[j]) continue;
            longestPath = Math.max(getLongestPath(map,j), longestPath);
        }
        
        o_writ.printLine(longestPath);
    }

    int getLongestPath(HashMap<Integer, ArrayList<Integer>> map, Integer node){
        if(!map.containsKey(node)) return 0; //leaf node
        
        int longestPathFromNextNode = 0;
        for(Integer nextNode: map.get(node)){
            longestPathFromNextNode = Math.max(longestPathFromNextNode, 
            getLongestPath(map, nextNode));
        }
        
        return 1 + longestPathFromNextNode;

    }


    public PT07Z(String[] args) {
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
