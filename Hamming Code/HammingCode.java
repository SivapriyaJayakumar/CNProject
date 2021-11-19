import java.util.*;
public class HammingCode{

    public static void main(String args[]){
        Scanner scan=new Scanner(System.in);
        int redundancybits[];
        System.out.println("Enter no of bits of data");
        int n=scan.nextInt();
        int data[]=new int[n];
        System.out.println("Enter Data");
        for(int i=0;i<n;i++){
            data[i]=scan.nextInt();
        }
        int r=0;
        int m=2;
        while(Math.pow(2,r) < (n+r+1)){
            System.out.println("Iteration "+r+" : "+"LHS : "+Math.pow(2,r)+"  RHS : "+(n+r+1));
            r++;
        }
        System.out.println("no of redundant bits are "+r);
        redundancybits=new int[r];
        

    }
}