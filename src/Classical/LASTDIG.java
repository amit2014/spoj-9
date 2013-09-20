package Classical;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class LASTDIG {
    String       PROB_NAME;
    InputScanner i_scan;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        LASTDIG program = new LASTDIG(args);
        program.begin();
    }

    public void begin(){
        int T = i_scan.nextInt();
        
        /* Lookup Tables */
        int[] table_num = {
                /*0*/   1,
                /*1*/   1,
                /*2*/   4,
                /*3*/   4,
                /*4*/   2,
                /*5*/   1,
                /*6*/   1,
                /*7*/   4,
                /*8*/   4,
                /*9*/   2
                        };
                       
        int[][] table_value = {
                /*0*/   {0},
                /*1*/   {0},
                /*2*/   {8,2,4,6},
                /*3*/   {1,3,9,7},
                /*4*/   {6,4},
                /*5*/   {5},
                /*6*/   {6},
                /*7*/   {1,7,9,3},
                /*8*/   {6,8,4,2},
                /*9*/   {1,9}
                        };
                        
        for(int cases = 0; cases <T; cases++){
            int a = i_scan.nextInt()%10;
            int b = i_scan.nextInt();
            
            if(b==0){
                System.out.println(1);
                continue;
            }
            
            System.out.println(table_value[a][b%table_num[a]]);
        }
    }

    public LASTDIG(String[] args) {
            i_scan = new InputScanner(System.in);
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
    }
}
