import java.util.*;
public class HammingCode{
    public boolean checkPowerOfTwo(int n){
        while (n != 1)
        {
            if (n%2 != 0){
                return false;
            }
            n = n/2;
        }
        return true;
    }
    public int parityValue(int data[],int redvalue[]){
        int retval=0;
        for(int i=0;i<redvalue.length;i++){
            if(redvalue[i]>data.length){
                break;
            }
            if(data[redvalue[i]-1]==1){
                retval++;
            }
        }
        if(retval%2==0){
            retval=0;
        }
        else{
            retval=1;
        }
        return retval;
    }
    public int recalcParity(int paritybit,int data[],int redvalue[]){
        int retval=0;
        System.out.println("Initial retval "+retval);
        for(int i=0;i<redvalue.length;i++){
            if(redvalue[i]>data.length){
                break;
            }
            System.out.println("redvalue [i] "+redvalue[i]);
            if(data[redvalue[i]-1]==1){
                retval++;
                System.out.println("Data check cal "+retval);
            }
        }
        if(paritybit==1){
            retval++;
            System.out.println("Parity check cal "+retval);
        }
        if(retval%2==0){
            retval=0;
        }
        else{
            retval=1;
        }
        return retval;
    }

    public int[] checkReceivedParity(int received_parity[],int received_pure_data[],int n){
        int result[]=new int[n];
        int r1[]={3,5,7,9,11,13,15};
        int r2[]={3,6,7,10,11,14,15};
        int r3[]={5,6,7,12,13,14,15};
        int r4[]={8,9,10,11,12,13,14,15};
        for(int i=received_parity.length-1;i>=0;i--){
            switch(i){
                case 0: 
                System.out.println("Case :"+i);
                result[i]=recalcParity(received_parity[i],received_pure_data,r1);break;
                case 1: 
                System.out.println("Case :"+i);
                result[i]=recalcParity(received_parity[i],received_pure_data,r2);break;
                case 2: 
                System.out.println("Case :"+i);
                result[i]=recalcParity(received_parity[i],received_pure_data,r3);break;
                case 3:
                System.out.println("Case :"+i); 
                result[i]=recalcParity(received_parity[i],received_pure_data,r4);break;
            }
            
        }
        System.out.println("Redundancy bits re-calculated");
        for(int i=received_parity.length-1;i>=0;i--){
            System.out.println(result[i]);
        }
        return result;

    }
    public  int[] calculateParityBits(int r,int data[]){
        int redundancybits[]=new int[r];
        int r1[]={3,5,7,9,11,13,15};
        int r2[]={3,6,7,10,11,14,15};
        int r3[]={5,6,7,12,13,14,15};
        int r4[]={8,9,10,11,12,13,14,15};
        
        for(int i=0;i<redundancybits.length;i++){
            switch(i){
                case 0: 
                redundancybits[i]=parityValue(data,r1);break;
                case 1: 
                redundancybits[i]=parityValue(data,r2);break;
                case 2: 
                redundancybits[i]=parityValue(data,r3);break;
                case 3: 
                redundancybits[i]=parityValue(data,r4);break;
            }
            
        }
        System.out.println("Redundancy bits calculated");
        for(int i=0;i<redundancybits.length;i++){
            System.out.println(redundancybits[i]);
        }
        return redundancybits;


    }
    public static void main(String args[]){
        HammingCode obj=new HammingCode();
        Scanner scan=new Scanner(System.in);
        int redundancybits[];
        int data_received[];
        int received_pure_data[];
        int received_parity[];
        int received_parity_check[];
        System.out.println("Enter no of bits of data");
        int n=scan.nextInt();
        int data_redundancy[];
        int data_sent[];
        int data[]=new int[n];

        System.out.println("Enter Data");
        for(int i=0;i<n;i++){
            data[i]=scan.nextInt();
        }
        int r=0;
        while(Math.pow(2,r) < (n+r+1)){
            System.out.println("Iteration "+r+" : "+"LHS : "+Math.pow(2,r)+"  RHS : "+(n+r+1));
            r++;
        }
        System.out.println("no of redundant bits are "+r);
        redundancybits=new int[r];

        for(int i=0;i<redundancybits.length;i++){
            redundancybits[i]=0;
        }

        data_redundancy=new int[data.length+redundancybits.length];
        //data_sent=new int[data_redundancy.length];
        System.out.println("red length"+data_redundancy.length);
        int k_redind=0; int dataind=0;
        for(int i=1;i<=data_redundancy.length;i++){
            if(i==1 || obj.checkPowerOfTwo(i)){
                data_redundancy[i-1]=redundancybits[k_redind];
                System.out.println("i is  "+i+" Data is "+ data_redundancy[i-1]);
                k_redind+=1;
            }
            else{
                data_redundancy[i-1]=data[dataind];
                System.out.println("i is  "+i+" Data is "+ data_redundancy[i-1]);
                dataind+=1;
            }
        }
        /*int datasentind=0;
        for(int i=data_redundancy.length-1;i>=0;i--){
            data_sent[datasentind]=data_redundancy[i];
            datasentind++;
        }
        System.out.println("Data to be sent");
        for(int i=0;i<data_sent.length;i++){
            System.out.print(data_sent[i]);
        }*/
        System.out.println();
        redundancybits=obj.calculateParityBits(r,data_redundancy);

        k_redind=0;dataind=0;
        for(int i=1;i<=data_redundancy.length;i++){
            if(i==1 || obj.checkPowerOfTwo(i)){
                data_redundancy[i-1]=redundancybits[k_redind];
                //System.out.println("i is  "+i+" Data is "+ data_redundancy[i-1]);
                k_redind+=1;
            }
            else{
                data_redundancy[i-1]=data[dataind];
                //System.out.println("i is  "+i+" Data is "+ data_redundancy[i-1]);
                dataind+=1;
            }
        }
        System.out.println("Data With hamming code");
        for(int i=0;i<data_redundancy.length;i++){
            System.out.print(data_redundancy[i]);
        }
        System.out.println();

        System.out.println("Enter data received at receiver end");
        data_received=new int[data.length+redundancybits.length];
        received_pure_data= new int [data.length];
        received_parity =new int [redundancybits.length];
        for(int i=0;i<data_received.length;i++){
            data_received[i]=scan.nextInt();
        }
        k_redind=0;dataind=0;
        for(int i=1;i<=data_redundancy.length;i++){
            if(i==1 || obj.checkPowerOfTwo(i)){
                received_parity[k_redind]=data_received[i-1];
                System.out.println("i is  "+i+" PA "+ received_parity[k_redind]);
                k_redind+=1;
            }
            else{
                received_pure_data[dataind]=data_received[i-1];
                System.out.println("i is  "+i+" PD "+   received_pure_data[dataind]);
                dataind+=1;
            }
        }
        received_parity_check=new int[redundancybits.length];
        received_parity_check=obj.checkReceivedParity(received_parity,data_received,received_parity.length);
       

        


    }
}