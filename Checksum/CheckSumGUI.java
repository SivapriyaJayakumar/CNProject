import java.util.*;
import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;
public class CheckSumGUI{
    // frame
    JFrame frame;
    // no of frames --> Textfield
    JTextField noofframes;
    // no of bits in each frame --> Textfield
    JTextField noofdatabits;
    // nextbt --> Button to proceed
    JButton nextbt;
    // Enter Frames Prompt --> Label
    JLabel framesprompt;
    // Enter n --> Label
    JLabel nprompt;
    // no of frames --> int 
    int no_frames=0;
    // data_collected --> String arr to store data from sender 
    String data_collected[];
    // data_received --> String arr to store data from receiver 
    String data_received[];
    // data_arr_sender --> long arr to store data from sender 
    long data_arr_sender[];
    // data_arr_reciever --> long arr to store data from receiver 
    long data_arr_reciever[];
    // checskum_receiver --> long arr to store complement of sum at receiver side
    long checksum_receiver[];
    // no_bits --> int 
    int no_bits=0;
    //checksum --> long arr to calculate checksum at sender side
    long checksum[];
    //csum --> to store intermediate checksum value at sender side
    long csum;
    //value --> to store intermediate checksum value at receiver side
    long value;
    //finalsum --> to store extracted sum in case of overflow
    long finalsum;
    //finalcarry --> to store extracted carry in case of overflow
    long finalcarry;
    //totalsum --> to store final sum sender
    long totalsum=0;

    /*
    complement Method
    Returns - Complement of given data - long array
    Description : 
        if data is 1 , invert it to 0 and vice versa thereby finding complement of data. 
        Example : Data is "1001" and Complement is "0110".
    */
    public long[] complement(long data[]){
        //result to be returned
        long res[]=new long[data.length];
        System.out.println("Complement is");
        for(int i=0;i<data.length;i++){
            if(data[i]==1){
                res[i]=0;
                System.out.print(res[i]+" ");
            }
            else if(data[i]==0){
                res[i]=1;
                System.out.print(res[i]+" ");
            }
        }
        return res;
    }
    /*
    findChecksum Method
    Returns - Checksum of given data for sender side - long array
    Description : Find Checksum by Complementing the sum of data units
        
    */
    public long[] findChecksum(String totalsum,int n){
        System.out.println("Checksum called");
        //sum array --> to store bits by extracting from string.
        long sum[]=new long[n];
        // complementofsum --> long arr to be returned.
        long complementofsum[]=new long[n];
        System.out.println("Sum is ");
        //diff --> difference between string's length and n 
        // For Example : If the sum is "001100", while conveting it into long it will truncate the zero in the front leaving sum as "1100".
        //That will be resolved using the logic. if (diff is < n ), then we can understand that zero's has been truncated and can be added.
        int diff=0;
        if(totalsum.length()<n){
            diff=n-totalsum.length();
            int i=0;
            for(i=0;i<diff;i++){
                complementofsum[i]=1;
                System.out.println(complementofsum[i]);
                System.out.println(i+"is i ");
            }
            //strind --> to keep track of index of string.
            int strind=0;
            for(int j=i;j<n;j++){
                sum[j]=Character.getNumericValue(totalsum.charAt(strind));
                System.out.print(sum[j]+" ");
                strind++;
            }
        }
        // if sum's length == n 
        else{
            int strind=0;
            for(int j=0;j<n;j++){
                sum[j]=Character.getNumericValue(totalsum.charAt(strind));
                System.out.print(sum[j]+" ");
                strind++;
            }
        }
        System.out.println();
        //Finding and Printing complement of sum from diff to n (from 0 to n incase of sum's length=n and from diff to n incase of sum's length<n)
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
    /*
    binaryAddition Method
    Returns - Sum of given operands - long array
    Description : Find Summation of 2 binary numbers
        
    */
    public long binaryAddition(long op1,long op2,int n,int k){
        System.out.println("OP 1 "+op1);
        System.out.println("OP 2 "+op2);
        int i=0;
        //remainder --> Initially 0.
        long remainder=0;
        //sumval --> Initially "".
        String sumval="";
        //sumValue --> Initially 0.
        long sumValue=0;
        //sum --> array to store sum of binary numbers
        long sum[]=new long[n+n];
        // Summation Logic --> Until both operands becomes zero, extracting the digits and adding it storing it to sum and dividing operand by 10.
        while(op1!=0 || op2!=0){
            //Extracting ith digits and adding it
            long intermideate_res= (op1 % 10 + op2 % 10 + remainder);
            //dividing it by % 2
            sum[i]=intermideate_res % 2;
            //calc remainder
            remainder=intermideate_res / 2;
            //dividing operands by 10
            op1=op1/10;
            op2=op2/10;
            i++;
        }
        // if remainder is not equal to zero
        if(remainder!=0){
            sum[i++]=remainder;
        }
       i=i-1;
       //Printing summation
        System.out.println("Sum of two binary numbers: ");
            while (i >= 0) {
                System.out.println(sum[i]);
                //appending summation bits to sumval string 
                sumval+=Long.toString(sum[i]);
                System.out.println("sumval "+sumval);
                i--;
            }
        // converting sumval string to long
        sumValue=Long.parseLong(sumval);
        System.out.println("Long sum value "+sumValue);
        System.out.print("\n"); 
        return sumValue;

    }
    /*
    addAll Method -- Receiver
    Returns - Sum of k data frames {Data frames + Checksum} - long 
    Description : Find Summation of all data frames and checksum
        
    */
    public long addAll(int k,int n,long data[]){
        //Checksum_str --> To store checksum as string
        String Checksum_str="";
        //csum --> To store checksum as long
        long csum=0;
        //InitialSum --> To store Initial sum  into sum
        String InitialSum="";
        for(int i=0;i<n;i++){
            InitialSum+="0";
        }
        //sum --> To store sum
        long sum=Long.parseLong(InitialSum);
        // Adding all data frames 
        for(int j=0;j<k;j++){
            sum=binaryAddition(sum,data[j],n,k);
            System.out.println("From For sum is "+sum);
        }
        //sum_total --> Converting final sum of data frames into string.
        String sum_total=Long.toString(sum);
        // If sum string is > n then overflow has occured.
        //Can be resolved by logic : Sum+carry=NewSum
        //Example --> 10+11=101 (1 is carry) --> 01+1=10 So, Sum is 10.
        if(sum_total.length()>n){
            //carry ind --> To find the length of carry
            int carryind=sum_total.length()-n;
            //carry --> String from 0th bit to carryind bit
            String carry=sum_total.substring(0,carryind);
            //sum_extracted --> String from carryind bit to end of string
            String sum_extracted=sum_total.substring(carryind);
            //finalsum --> String to Long conversion of sum
            finalsum=Long.parseLong(sum_extracted);
            //finalcarry --> String to Long conversion of carry
            finalcarry=Long.parseLong(carry);
            System.out.println("Carry is "+finalcarry);
            System.out.println("Sum is "+finalsum);
            //sum -->finding sum of sum and carry
            sum=binaryAddition(finalcarry,finalsum,sum_extracted.length(),2);
            System.out.println("Total Sum is "+sum);
        
        }
        return sum;
           
    } 
    /*
    addDataBits Method -- Sender
    Returns - Sum of k data frames - long 
    Description : Find Summation of all data frames
        
    */
    public long addDataBits(int k,int n,long data[]){
        //Checksum_str --> To store checksum as string
        String Checksum_str="";
        String totalsum_str="";
        //csum --> To store checksum as long
        long csum=0;
        //InitialSum --> To store Initial sum  into sum
        String InitialSum="";
        for(int i=0;i<n;i++){
            InitialSum+="0";
        }
        //sum --> To store sum
        long sum=Long.parseLong(InitialSum);
        // Adding all data frames 
        for(int j=0;j<k;j++){
            sum=binaryAddition(sum,data[j],n,k);
            System.out.println("From For sum is "+sum);
        }       
        //sum_total --> Converting final sum of data frames into string. 
        String sum_total=Long.toString(sum);
        // If sum string is > n then overflow has occured.
        //Can be resolved by logic : Sum+carry=NewSum
        //Example --> 10+11=101 (1 is carry) --> 01+1=10 So, Sum is 10.
            if(sum_total.length()>n){
                //carry ind --> To find the length of carry
                int carryind=sum_total.length()-n;
                //carry --> String from 0th bit to carryind bit
                String carry=sum_total.substring(0,carryind);
                //sum_extracted --> String from carryind bit to end of string
                String sum_extracted=sum_total.substring(carryind);
                //finalsum --> String to Long conversion of sum
                finalsum=Long.parseLong(sum_extracted);
                //finalcarry --> String to Long conversion of carry
                finalcarry=Long.parseLong(carry);
                System.out.println("Carry is "+finalcarry);
                System.out.println("Sum is "+finalsum);
                //totalsum -->finding sum of sum and carry
                totalsum=binaryAddition(finalcarry,finalsum,sum_extracted.length(),2);
                System.out.println("Total Sum is "+totalsum);
                //totalsum_str -->fconversion of long to string
                totalsum_str=Long.toString(totalsum);
                System.out.println("Total Sum str inside if is "+totalsum_str);
            }
            else{
                totalsum_str=Long.toString(totalsum);
                System.out.println("Total Sum str inside else is "+totalsum_str);
            }
            System.out.println("Checksum callig from addDataBits");
            //checksum --> array to store checksum
            checksum=new long[n];
            // calling findChecksum Method
            checksum=findChecksum(totalsum_str,n);
            // Converting Checksum to string
            for(int i=0;i<checksum.length;i++){
                Checksum_str+=Long.toString(checksum[i]);
            }
            System.out.println("String checksum is"+Checksum_str);
            // converting string to long
            csum=Long.parseLong(Checksum_str);
            System.out.println("Int returned checksum is"+csum);
            return csum;
            
        
       
    }
    /*
    BuildGUI Method
    Renders - GUI
    Description : Renders Elements of GUI
        
    */
    public void BuildGUI(){
        //frame --> settitle
        frame=new JFrame("Error Detector - CHECKSUM");
        //frameprompt --> setlabel
        framesprompt=new JLabel("Enter the No of Frames");
        //noofframes --> settextfield
        noofframes=new JTextField(30);
        //nprompt --> setlabel
        nprompt=new JLabel("Enter the No of bits in each frame");
        //noofdatabits --> settextfield
        noofdatabits=new JTextField(30);
        //nextbt --> setbutton
        nextbt=new JButton("Next");
        //setting bounds
        framesprompt.setBounds(100,100,1000,120);
        framesprompt.setFont(new Font("Verdana", Font.BOLD, 18));
        noofframes.setBounds(100,180,200,40);
        nprompt.setBounds(100,220,1000,120);
        nprompt.setFont(new Font("Verdana", Font.BOLD, 18));
        noofdatabits.setBounds(100,300,200,40);
        nextbt.setBounds(100,400,100,40);
        //adding elements
        frame.getContentPane().add(framesprompt);
        frame.getContentPane().add(noofframes);
        frame.getContentPane().add(nprompt);
        frame.getContentPane().add(noofdatabits);
        frame.getContentPane().add(nextbt);
        //after entering no of frames and bits of each frame
        nextbt.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e){
                    //removing components previously added
                    frame.getContentPane().remove(framesprompt);
                    frame.getContentPane().remove(noofframes);
                    frame.getContentPane().remove(nprompt);
                    frame.getContentPane().remove(noofdatabits);
                    frame.getContentPane().remove(nextbt);
                    // getting no of frames and no of data bits
                    no_frames=Integer.parseInt(noofframes.getText());
                    no_bits=Integer.parseInt(noofdatabits.getText());
                    System.out.println("No of Frames : "+no_frames);
                    System.out.println("No of Bits in each frame : "+no_bits);
                    // Initialising array 
                    data_collected=new String[no_frames];
                    data_arr_sender=new long[no_frames];
                    // textfield references for each frame
                    JTextField dataref[]=new JTextField[no_frames];
                    // label references for each frame
                    JLabel datareflab[]=new JLabel[no_frames];
                    // nextbt_data --> to proceed
                    JButton nextbt_data=new JButton("Next");
                    //coordinates
                    int xc=100;
                    int yc=100;
                    // generating k textfield each of n bits
                    for(int i=0;i<no_frames;i++){
                        String promptdata=" Enter Data for Frame "+(i+1);
                        JLabel datalabel=new JLabel(promptdata);
                        JTextField data=new JTextField(no_bits);
                        dataref[i]=data;
                        datareflab[i]=datalabel;
                        // to disable textfield once sender has typed n bits
                        data.getDocument().addDocumentListener(
                            new javax.swing.event.DocumentListener(){
                                public void changedUpdate(javax.swing.event.DocumentEvent e){}
                                public void insertUpdate(javax.swing.event.DocumentEvent e){
                                    if(data.getText().length()==no_bits){
                                        System.out.println("Disabled");
                                        data.setEditable(false);
                                    }
                                }
                                public void removeUpdate(javax.swing.event.DocumentEvent e){}
                            }
                        ); 
                        //setting bounds
                        datalabel.setBounds(xc,yc,400,40);
                        datalabel.setFont(new Font("Verdana", Font.BOLD, 18));
                        data.setBounds(xc+300,yc,200,40);
                        //adding components
                        frame.getContentPane().add(datalabel);
                        frame.getContentPane().add(data);
                        frame.pack();
                        yc+=100;
                        if(yc==700){
                            yc+=100;
                            xc+=100;
                        }
                    }
                    //setting bounds and adding component
                    nextbt_data.setFont(new Font("Verdana", Font.BOLD, 18));
                    nextbt_data.setBounds(xc,yc,200,40);
                    frame.getContentPane().add(nextbt_data);
                    // after ending k data frames
                    nextbt_data.addActionListener(
                        new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e){
                                // removing components previously added
                                for(int i=0;i<dataref.length;i++){
                                frame.getContentPane().remove(dataref[i]);
                                frame.getContentPane().remove(datareflab[i]);
                                  frame.pack();
                                }
                                 frame.getContentPane().remove(nextbt_data);
                                 frame.pack();
                                // Data collected from sender being stored into string arr
                                System.out.println("Data collected from sender");
                                for(int i=0;i<dataref.length;i++){
                                    data_collected[i]=dataref[i].getText();
                                    System.out.println(" data_collected [ "+ i +"] : " +data_collected[i]);
                                }
                                // Data collected from sender being extracted from string arr into long arr
                                System.out.println("Extracting data into array");
                                for(int i=0;i<no_frames;i++){
                                    data_arr_sender[i]=Long.parseLong(data_collected[i]);
                                }
                                // Printing Data collected from sender 
                                System.out.println("Printing Data Array");
                                for(int i=0;i<no_frames;i++){
                                    System.out.println("Frame : "+(i+1));
                                    System.out.println(data_arr_sender[i]);

                                }
                                //Calculating checksum by taking the complement of summation of data frames (By calling addDataBits method) 
                                csum=addDataBits(no_frames,no_bits,data_arr_sender);
                                //setting bounds and adding component
                                JLabel labelrecprompt=new JLabel("Enter Data Received at Receiver Side");
                                labelrecprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                                labelrecprompt.setBounds(100,100,400,40);
                                frame.getContentPane().add(labelrecprompt);
                                int xc=200,yc=200;
                                JTextField datareceive[]=new JTextField[no_frames];
                                // generating k textfield each of n bits --> To get data received
                                for(int i=0;i<no_frames;i++){
                                    String promptdata=" Enter Data for Frame "+(i+1);
                                    JLabel datalabel=new JLabel(promptdata);
                                    JTextField data=new JTextField(no_bits);
                                    datareceive[i]=data;
                                    // to disable textfield once sender has typed n bits
                                    data.getDocument().addDocumentListener(
                                        new javax.swing.event.DocumentListener(){
                                            public void changedUpdate(javax.swing.event.DocumentEvent e){}
                                            public void insertUpdate(javax.swing.event.DocumentEvent e){
                                                if(data.getText().length()==no_bits){
                                                    System.out.println("Disabled");
                                                    data.setEditable(false);
                                                }
                                            }
                                            public void removeUpdate(javax.swing.event.DocumentEvent e){}
                                        }
                                    ); 
                                    //setting bounds and adding component
                                    datalabel.setBounds(xc,yc,400,40);
                                    datalabel.setFont(new Font("Verdana", Font.BOLD, 18));
                                    data.setBounds(xc+300,yc,200,40);
                                    frame.getContentPane().add(datalabel);
                                    frame.getContentPane().add(data);
                                    frame.pack();
                                    yc+=100;
                                    if(yc==700){
                                        yc+=100;
                                        xc+=100;
                                    }
                                }
                                //setting bounds and adding component
                                JButton nextres=new JButton("Check Error");
                                nextres.setBounds(xc,yc,200,40);
                                frame.getContentPane().add(nextres);
                                frame.pack();
                                // Initialising array 
                                data_arr_reciever=new long[no_frames+1];
                                data_received=new String[no_frames+1];
                                // after getting data that has been received
                                nextres.addActionListener(
                                    new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent e){
                                            // Data collected from receiver being stored into string arr
                                            System.out.println("Data collected from Receiver");
                                            for(int i=0;i<datareceive.length;i++){
                                                data_received[i]=datareceive[i].getText();
                                                System.out.println(" data_received [ "+ i +"] : " +data_received[i]);
                                            }
                                            // Data collected from receiver being extracted from string arr into long arr
                                            System.out.println("Extracting receiver data into array");
                                            for(int i=0;i<no_frames;i++){
                                                data_arr_reciever[i]=Long.parseLong(data_received[i]);
                                            }
                                            // adding checksum produced as last element of data_arr_reciever
                                            data_arr_reciever[no_frames]=csum;
                                            // Printing data collected from receiver
                                            System.out.println("Printing Data receiver Array");
                                            for(int i=0;i<no_frames+1;i++){
                                                System.out.println("Frame : "+(i+1));
                                                System.out.println(data_arr_reciever[i]);
                                                
                                            }
                                            //Calculating sum of dataframes and checksum  (By calling addAll method) 
                                            value=addAll(no_frames+1,no_bits,data_arr_reciever);

                                            System.out.println("return value"+value); 
                                            String strvalue=Long.toString(value);
                                            System.out.println("return value string"+strvalue); 
                                            String final_value=strvalue;
                                            System.out.println("final value  string"+final_value); 
                                            //checksum_receiver --> long arr to store sum of data units and checksum calc at receiver end
                                            checksum_receiver=new long[no_bits];
                                            // if sum of data units and checksum  is less than no_bits then zeros in front will be truncated.
                                            //resolving that
                                            if(final_value.length()<no_bits){
                                                int diff=no_bits-final_value.length();
                                                int strind=0;
                                                for(int i=0;i<diff;i++){
                                                    checksum_receiver[i]=0;
                                                    System.out.print(checksum_receiver[i]);
                                                }
                                                for(int j=diff;j<no_bits;j++){
                                                    checksum_receiver[j]=Character.getNumericValue(final_value.charAt(strind));
                                                    strind++;
                                                    System.out.print(checksum_receiver[j]);
                                                }
                                                 System.out.println();
                                            }
                                            // if  sum of data units and checksum  is == no_bits
                                            else{
                                                 for(int i=0;i<no_bits;i++){
                                                    checksum_receiver[i]=Character.getNumericValue(final_value.charAt(i));
                                                }
                                            }
                                            boolean flag_checksum=false;
                                            //complementing the sum
                                            checksum_receiver=complement(checksum_receiver);
                                            // if all zeros --> no error else error
                                            for(int i=0;i<checksum_receiver.length;i++){
                                                
                                                if(checksum_receiver[i]==0){
                                                    continue;
                                                }
                                                else if(checksum_receiver[i]!=0){
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
                                );
                                
                            }
                        
                        }
                    );
                    frame.pack();
                }
            
            
            
            }
        );
        //setting frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(254,243,199));
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(1200,800));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String args[]){
        //creating object for class and calling BuildGUI
        CheckSumGUI checksumgui=new CheckSumGUI();
        checksumgui.BuildGUI();
    }

}