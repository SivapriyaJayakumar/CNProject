import java.util.*;
public class Checksum{
    public int binaryAddition(int op1,long op2,int n,int k){
        System.out.println("OP 1 "+op1);
        System.out.println("OP 2 "+op2);
        int i=0;
        int remainder=0;
        String sumval="";
        int sumValue=0;
        int sum[]=new int[n+1];
        while(op1!=0 || op2!=0){
            int intermideate_res=(int) (op1 % 10 + op2 % 10 + remainder);
            sum[i]=intermideate_res % 2;
            remainder=intermideate_res / 2;
            op1=op1%10;
            op2=op2/10;
            i++;
        }
        if(remainder!=0){
            sum[0]+=remainder;
        }
       i=i-1;
        System.out.println("Sum of two binary numbers: ");
            while (i >= 0) {
                System.out.println(sum[i]);
                sumval+=Integer.toString(sum[i]);
                System.out.println("sumval "+sumval);
                i--;
            }
        sumValue=Integer.parseInt(sumval);
        System.out.println("Int sum value "+sumValue);
        System.out.print("\n"); 
        return sumValue;

    }
    public void addDataBits(int k,int n,long data[]){
        String InitialSum="";
        for(int i=0;i<n;i++){
            InitialSum+="0";
        }
        int sum=Integer.parseInt(InitialSum);
        int start=0;
        for(int j=0;j<k;j++){
            sum=binaryAddition(sum,data[j],n,k);
            System.out.println("From For sum is "+sum);

           
        }
        
        
        
        
    }
    public static void main(String args[]){
        Checksum checksum_obj=new Checksum();
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter no of frames");
        int k=scan.nextInt();
        System.out.println("Enter no of bits of each frame");
        int n=scan.nextInt();
        long frames[]=new long[k];
        for(int j=0;j<k;j++){
            long databits=0;
            System.out.println("Enter Data for Frame "+(j+1));
            databits=scan.nextLong();
            frames[j]=databits;
        }

        for(int j=0;j<k;j++){
            System.out.println("Frame [ "+ j +" ]  :"+frames[j]);
        }

        checksum_obj.addDataBits(k,n,frames);

        
    }
}