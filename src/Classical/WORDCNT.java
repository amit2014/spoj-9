package Classical;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class WORDCNT {
    public static void main(String[] args) {
        WORDCNT sol = new WORDCNT();
        try{
            sol.begin();
        }catch(Exception e){
            return;
        }
    }

    public void begin() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            String str = br.readLine();
            if(str.length()==0){
                i--;
                continue;
            }
            System.out.println(maxWords(str));
        }
    }
    
    int maxWords(String str){
        String[] array = str.split(" ");
        int maxCount = 0;
        int prevLen = 0;
        int prevCount = 0;
        for(int i = 0; i < array.length; i++){
            int thisLen = array[i].length();
            if(thisLen==prevLen){
                prevCount++;
            }else{
                prevLen = thisLen;
                if(prevCount > maxCount){
                    maxCount = prevCount;
                }
                prevCount = 1;
            }
        }
        return maxCount;
    }

}
