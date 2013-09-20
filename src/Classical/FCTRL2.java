package Classical;
/*Accepted*/
import java.io.InputStreamReader;import java.io.BufferedReader;import java.math.BigInteger;
class FCTRL2{
public static void main(String[] a) throws Exception{FCTRL2 program=new FCTRL2();program.begin();}
public void begin() throws Exception{BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
int T=Integer.parseInt(b.readLine());String N;int M;BigInteger f;
for(int t=0;t<T;t++){f=new BigInteger("1");N=b.readLine();M=Integer.parseInt(N);
for(int n=1;n<=M;n++){f=f.multiply(new BigInteger(String.valueOf(n)));}
System.out.println(f);
}}}
