import java.util.*;
public class Checksum{
    int finalsum;
    int finalcarry;
    int totalsum=0;
    int checksum[];
    int checksum_receiver[];
    public int[] findChecksum(String totalsum,int n){
        int sum[]=new int[n];
        int compind=0;
        int complementofsum[]=new int[n];
        System.out.println("Sum is ");
        int diff=0;
        if(totalsum.length()<n){
            diff=n-totalsum.length();
            int i=0;
            for(i=0;i<diff;i++){
                complementofsum[i]=1;
                System.out.println(complementofsum[i]+"for ");
                System.out.println(i+"is i ");
            }
            int strind=0;
            for(int j=i;j<n;j++){
                sum[j]=Character.getNumericValue(totalsum.charAt(strind));
                System.out.print(sum[j]+" ");
                strind++;
            }
        }
        else{
            int strind=0;
            for(int j=0;j<n;j++){
                sum[j]=Character.getNumericValue(totalsum.charAt(strind));
                System.out.print(sum[j]+" ");
                strind++;
            }
        }
        /*
        for(int i=0;i<n;i++){
        try{ 
            sum[i]=Character.getNumericValue(totalsum.charAt(i));
            System.out.print(sum[i]+" ");
        }
        catch (StringIndexOutOfBoundsException e) {
            complementofsum[compind]=1;
            compind++;
            System.out.println(complementofsum[compind]+"for ");
            System.out.print(compind+"is compind ");
            System.out.println();
        }
        }*/
        System.out.println();
        System.out.println("Complement of sum is ");
        for(int i=diff;i<n;i++){
            if(sum[i]==1){
                complementofsum[i]=0;
                System.out.print(complementofsum[i]+" ");
            }
            else if(sum[i]==0){
                complementofsum[i]=1;
                System.out.print(complementofsum[i]+" ");
            }
        }
        System.out.println();
        return complementofsum;

    }
    public int binaryAddition(int op1,long op2,int n,int k){
        System.out.println("OP 1 "+op1);
        System.out.println("OP 2 "+op2);
        int i=0;
        int remainder=0;
        String sumval="";
        int sumValue=0;
        int sum[]=new int[n+5];
        while(op1!=0 || op2!=0){
            int intermideate_res=(int) (op1 % 10 + op2 % 10 + remainder);
            sum[i]=intermideate_res % 2;
            remainder=intermideate_res / 2;
            op1=op1/10;
            op2=op2/10;
            i++;
        }
        if(remainder!=0){
            sum[i++]=remainder;
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

    public int addAll(int k,int n,long data[]){
        String Checksum_str="";
        int csum=0;
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
        return sum;
           
    } 
    public int addDataBits(int k,int n,long data[]){
        String Checksum_str="";
        int csum=0;
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
        String sum_total=Integer.toString(sum);
        if(sum_total.length()>n){
            int carryind=sum_total.length()-n;
            String carry=sum_total.substring(0,carryind);
            String sum_extracted=sum_total.substring(carryind);
            finalsum=Integer.parseInt(sum_extracted);
            finalcarry=Integer.parseInt(carry);
            System.out.println("Carry is "+finalcarry);
            System.out.println("Sum is "+finalsum);
            totalsum=binaryAddition(finalcarry,finalsum,sum_extracted.length(),2);
            System.out.println("Total Sum is "+totalsum);
            String totalsum_str=Integer.toString(totalsum);
            checksum=new int[n];
            checksum=findChecksum(totalsum_str,n);
            for(int i=0;i<checksum.length;i++){
                Checksum_str+=Integer.toString(checksum[i]);
            }
            System.out.println("String checksum is"+Checksum_str);
            csum=Integer.parseInt(Checksum_str);
            System.out.println("Int returned checksum is"+csum);
            
            
        }
        return csum;
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

        int checksum=checksum_obj.addDataBits(k,n,frames);

        System.out.println("Receiver Side");
        long recframes[]=new long[k+1];
        for(int j=0;j<k;j++){
            long databits=0;
            System.out.println("Enter Data for Frame "+(j+1));
            databits=scan.nextLong();
            recframes[j]=databits;
        }
        recframes[k]=checksum;
        System.out.println("Data Received");
        for(int j=0;j<k+1;j++){
            System.out.println("Frame [ "+ j +" ]  :"+recframes[j]);
        }
        
        int value=checksum_obj.addAll(k+1,n,recframes);
        System.out.println("return value"+value); 
        String strvalue=Integer.toString(value);
        System.out.println("return value string"+strvalue); 
        String final_value=strvalue.substring(0,n);
        System.out.println("final value trimmed string"+final_value); 
        checksum_obj.checksum_receiver=new int[n];
        for(int i=0;i<n;i++){
            checksum_obj.checksum_receiver[i]=Character.getNumericValue(final_value.charAt(i));
        }
        boolean flag_checksum=false;
        for(int i=0;i<checksum_obj.checksum_receiver.length;i++){
            
            if(checksum_obj.checksum_receiver[i]==1){
                continue;
            }
            else if(checksum_obj.checksum_receiver[i]!=1){
                flag_checksum=true;
                break;
            }
        }
        if(flag_checksum==true){
            System.out.println("ERROR");
        }
        else if(flag_checksum==false){
            System.out.println("NO ERROR");
        }

    }
}