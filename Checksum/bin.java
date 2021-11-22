import java.util.*;
public class bin{
    public int binaryadd(int op1,int op2){
        int res=-1;
        if(op1==0 && op2==0){
            res=0;
        }
        if(op1==0 && op2==1){
            res=1;
        }
        if(op1==1 && op2==0){
            res=1;
        }
        if(op1==1 && op2==1){
            res=10;
        }
        return res;
 
    }
    public void add(int data[][],int k,int n){
        int result[][]=new int[1][n];
        for(int i=0;i<n;i++){
            result[0][i]=0;
        }
        for(int i=0;i<k;i++){
            int rem=0; int lastind=n-1;
            for(int j=n-1;j>=0;j--){
                result[0][j]=binaryadd(result[0][j],data[i][j]);
                char carry; char sum;
                System.out.println("sum[i][j] "+result[0][j]);
                String res=Integer.toString(result[0][j]);
                if(res.length()>1){
                    carry=res.charAt(0);
                    sum=res.charAt(1);
                    int s=Character.getNumericValue(sum);
                    result[0][j]=s;
                    System.out.println("result [0][j] "+result[0][j]);
                    int c=Character.getNumericValue(carry);
                    rem=c;
                    System.out.println("Carry is"+carry+" Int carry is "+c);
                    System.out.println(result[0][n-1]+" + "+rem+" = ");
                    result[0][j-1]=binaryadd(result[0][j-1],rem);
                    System.out.println(result[0][j-1]);
                }
            }
            System.out.println("At the end of "+i +"th iteration sum is");
            for(int m=0;m<n;m++){
                System.out.print(result[0][m]);
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        bin checksum_obj=new bin();
        Scanner scan=new Scanner(System.in);
        System. out.println("Enter no of frames");
        int k=scan.nextInt();
        System.out.println("Enter no of bits of each frame");
        int n=scan.nextInt();
        int frames[][]=new int[k][n];
        for(int j=0;j<k;j++){
            System.out.println("Enter Data for Frame "+(j+1));
            for(int i=0;i<n;i++){
                int databits=scan.nextInt();
                frames[j][i]=databits;
            }
        }

        for(int j=0;j<k;j++){
            System.out.println("Frame [ "+ j +" ]  :");
            for(int i=0;i<n;i++){
                System.out.print(frames[j][i]);
            }
            System.out.println();
        }

        checksum_obj.add(frames,k,n);

       

        
    }
}