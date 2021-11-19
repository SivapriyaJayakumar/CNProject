public class Binaryaddn {

    public int Addition(int operands[],int n){
        int op=0;
        for(int i=0;i<operands.length;i++){
            System.out.println("operand is"+operands[i]);
            op+=operands[i];
            String binaryop=Integer.toBinaryString(op);
            System.out.println("op  is "+binaryop +" i is "+i);
            //String output=Integer.toString(op);
            System.out.println("output str is"+binaryop);
            if(binaryop.length()!=n){
                System.out.println("  carry case");
                String carry=binaryop.substring(0,1);
                System.out.println("carry is"+carry);
                String sum=binaryop.substring(1);
                System.out.println("sum is"+sum);
                int sum_int=Integer.parseInt(sum);
                int carry_rem=Integer.parseInt(carry);
                System.out.println("After  carry sum is"+ sum_int);
                System.out.println("After  carry carry is"+ carry_rem);
                op=sum_int+carry_rem;
                System.out.println("After  carry total sum is"+ op);
                
            }
        }
        return op;
    }

	public static void main(String[] args) {
        Binaryaddn obj=new Binaryaddn();
		// Two binary numbers in string format
        int n=8;
		String binaryNumber1 = "11001100", binaryNumber2 = "10101010";
		String binaryNumber3 = "11110000", binaryNumber4="11000011";

		// converting strings into binary format numbers
		int integer1 = Integer.parseInt(binaryNumber1, 2);
		int integer2 = Integer.parseInt(binaryNumber2, 2);
		int integer3 = Integer.parseInt(binaryNumber3, 2);
		int integer4 = Integer.parseInt(binaryNumber4, 2);

        int operands[]={integer1,integer2,integer3,integer4};
		// adding two integers
		//Integer output = integer1 + integer2+integer3+integer4;
        int output=obj.Addition(operands,n);
		// converting final output back to Binary Integer
		System.out.println("Output: " + Integer.toBinaryString(output));

	}
}
