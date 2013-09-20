package Classical;


import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class JAVAC {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        JAVAC program = new JAVAC();
        try{
        program.begin();
        }catch(Exception e){
            return;
        }
    }

    /*
    if string contains '_':
        c_style
    else if string contains upper case:
        java_style
    else both

*/

/*** Imports ***
import java.io.BufferedReader;
import java.io.InputStreamReader;

**********/
public void begin(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = null;
    try{
        str = br.readLine();
    }catch(Exception e){return;}
    while(str.length()>0){
        if(str.contains("_")){
            if(!checkC(str)){
                System.out.println("Error!");
            }else{
                System.out.println(convertCToJava(str));
            }
        }else if(!str.equals(str.toLowerCase())){
            if(!checkJava(str)){
                System.out.println("Error!");
            }else{
                System.out.println(convertJavaToC(str));
            }
        }else{
            System.out.println(str);
        }
        
        try{
            str = br.readLine();
        }catch(Exception e){return;}
    }
}

boolean checkJava(String str){
    if(str.charAt(0)>='a' && str.charAt(0)<='z') return true;
    else return false;
}

boolean checkC(String str){
    if(!str.equals(str.toLowerCase())) return false;
    else if(str.charAt(0)=='_' || str.charAt(str.length()-1)=='_') return false;
    else if(str.contains("__")) return false;
    
    return true;
}

String convertCToJava(String str){
    //this_is_a_valie_c_name
    StringBuilder sb = new StringBuilder(str);
    
    int idx = sb.indexOf("_");
    while(idx!=-1){
        sb.deleteCharAt(idx);
        String c = String.valueOf(sb.charAt(idx));
        sb.replace(idx,idx+1,c.toUpperCase());
        
        idx = sb.indexOf("_");
    }
    
    return sb.toString();
}

String convertJavaToC(String str){
    //validJavaStringH
    StringBuilder sb = new StringBuilder(str);
    int idx = 0;
    while(idx < sb.length()){
        char c = sb.charAt(idx);
        if(c<'a'){
            sb.insert(idx,"_");
            sb.replace(idx+1,idx+2,String.valueOf(c).toLowerCase());
        }
        
        idx++;
    }
    
    return sb.toString();
}

}
