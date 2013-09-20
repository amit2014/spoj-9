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
import java.math.BigInteger;

/**
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class temp {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        temp program = new temp();
        try{
        program.begin();
        } catch(Exception e){
            return;
        }
    }

    public void begin() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try{
            str = br.readLine();
        }catch(Exception e){return;}
        while(str.length()!=0){
            BigInteger bigint = new BigInteger(str);
            if(bigint.equals(new BigInteger("1"))){
                System.out.println(1);
            }else if (bigint.equals(new BigInteger("0"))){
                System.out.println(0);
            }else{
                String ans = bigint.subtract(new BigInteger("1"))
                        .multiply(new BigInteger("2")).toString();
                System.out.println(ans);
            }
            
            
            
            try{
                str = br.readLine();
            }catch(Exception e){return;}
        }
    }

}
