import java.util.*;
public class CRC_Detection{
    int coeff[];

    public int[] BinaryDivison(int divisor[],int data[],int rem[]){
        int c=0;
        /*for(int i=0;i<divisor.length;i++){
               System.out.print(divisor[i]);
        }
        System.out.println("Remainder");
        for(int i=0;i<rem.length;i++){
               System.out.print(rem[i]);
        }*/
        while(true){
            for(int i=0;i<divisor.length;i++){
                    rem[c+i]=(rem[c+i]^divisor[i]);
                    //System.out.println("rem "+rem[i]);
            }
            while(rem[c]==0 && c!=rem.length-1){
                    c++;
                    //System.out.println("c"+c);
            }

            if((rem.length-c)<divisor.length){
                    break;
            }

        }
        return rem;

    }
    public int[] Decode_CRC_Generator(int n){
        Scanner scan_ob=new Scanner(System.in);
        //polynomial string - printing purpose
        String encoded="";

        //divisor bits
        coeff=new int[n+1];
        System.out.println("Enter N co-efficients ");
        for(int i=0;i<n+1;i++){
            coeff[i]=scan_ob.nextInt();
        }

        //printing polynomial
        System.out.println("The polynomial is : ");
        for(int i=0;i<coeff.length;i++){
            if(n==0){
            System.out.print(coeff[i]);
            encoded+=Integer.toString(coeff[i]);
            }
            else{
            System.out.print(coeff[i]+" x^"+n+" + ");
            encoded+=Integer.toString(coeff[i]);
            n=n-1;
            }
            
        } 
        System.out.println();
        return coeff;    
    }
    public static void main(String args[]){
            Scanner scan_obj=new Scanner(System.in);
            Scanner scan_ob=new Scanner(System.in);
            CRC_Detection po=new CRC_Detection();
            //power of polynomial eqn
            System.out.println("Enter N ");
            int n=scan_obj.nextInt();
            //divisor bits
            int divpolynomial[]=new int[n];
            divpolynomial=po.Decode_CRC_Generator(n);
            //data bits
            String data="";
            System.out.println("Enter the Data ");
            data=scan_ob.nextLine();
            //backup purpose - data
            String backupdata=data;
            //length of divisor
            int encoded_len=divpolynomial.length;
            System.out.println("Data  is : "+data);
            //appending zeros of length of divisor - 1 
            for(int i=0;i<encoded_len-1;i++){
                data+="0";
            }
            //data bits - arr
            int data_appended[]=new int[data.length()];
            for(int i=0;i<data_appended.length;i++){
                int no=Character.getNumericValue(data.charAt(i));
                data_appended[i]=no;
            }
           
            System.out.print("Generator Polynomial - Encoded : ");    
            for(int i=0;i<divpolynomial.length;i++){
                System.out.print(divpolynomial[i]);
            }
            System.out.println("\nData after  bits added is : ");
            for(int i=0;i<data_appended.length;i++){
                System.out.print(data_appended[i]);
            }
            System.out.println();
            int rem[]=new int[data_appended.length];
            for(int i=0;i<rem.length;i++){
                rem[i]=data_appended[i];
            }
            rem=po.BinaryDivison(divpolynomial,data_appended,rem);
            System.out.println("\nData - crc: ");
            for(int i=0;i<rem.length;i++){
                System.out.print(rem[i]);
            }

            System.out.println("\nData - after adding crc to be transmitted to receiver ");
            int data_crc[]=new int[data_appended.length];
            for(int i=0;i<rem.length;i++){
               data_crc[i]=data_appended[i]^rem[i];
            }
            for(int i=0;i<rem.length;i++){
               System.out.print(data_crc[i]);
            }

            System.out.println();
            System.out.println("Enter the Data that has been received in the receiver end ");
            int receiver_end_data[]=new int[data_appended.length];
            for(int i=0;i<receiver_end_data.length;i++){
               receiver_end_data[i]=scan_ob.nextInt();
            }

            int check_error_rem[]=new int[receiver_end_data.length];
            for(int i=0;i<check_error_rem.length;i++){
              check_error_rem[i]=receiver_end_data[i];
            }

            check_error_rem=po.BinaryDivison(divpolynomial,receiver_end_data,check_error_rem);
            System.out.println("Remainder : ");
            for(int i=0;i<check_error_rem.length;i++){
              System.out.print(check_error_rem[i]);
            }
            System.out.println();
            boolean detection_flag=true;
            for(int i=0;i<check_error_rem.length;i++){
                if(check_error_rem[i]==0){
                    continue;
                }
                if(check_error_rem[i]!=0){
                    detection_flag=false;
                    break;
                }
            }
            if(detection_flag==true){
                System.out.println("No Error is detected");
            }
            else{
                System.out.println("Error is detected");
            }
    }
}
