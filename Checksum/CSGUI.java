import java.util.*;
import javax.swing.*;
import java.awt.*;   
import java.math.BigInteger;
import javax.swing.JScrollPane;  
import javax.swing.JOptionPane;
public class CSGUI{
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
    BigInteger data_arr_sender[];
    // data_arr_reciever --> long arr to store data from receiver 
    BigInteger data_arr_reciever[];
    // checskum_receiver --> long arr to store complement of sum at receiver side
    BigInteger checksum_receiver[];
    // no_bits --> int 
    int no_bits=0;
    //checksum --> long arr to calculate checksum at sender side
    BigInteger checksum[];
    //csum --> to store intermediate checksum value at sender side
    BigInteger csum;
    //value --> to store intermediate checksum value at receiver side
    BigInteger value;
    //finalsum --> to store extracted sum in case of overflow
    BigInteger finalsum;
    //finalcarry --> to store extracted carry in case of overflow
    BigInteger finalcarry;
    //totalsum --> to store final sum sender
    BigInteger totalsum=BigInteger.ZERO;

    /*
    complement Method
    Returns - Complement of given data - long array
    Description : 
        if data is 1 , invert it to 0 and vice versa thereby finding complement of data. 
        Example : Data is "1001" and Complement is "0110".
    */
    public BigInteger[] complement(BigInteger data[]){
        //result to be returned
        BigInteger res[]=new BigInteger[data.length];
        System.out.println("Complement is");
        for(int i=0;i<data.length;i++){
            if(data[i].equals(BigInteger.ONE)){
                res[i]=BigInteger.ZERO;
                System.out.print(res[i]+" ");
            }
            else if(data[i].equals(BigInteger.ZERO)){
                res[i]=BigInteger.ONE;
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
    public BigInteger[] findChecksum(String totalsum,int n){
        System.out.println("Checksum called");
        //sum array --> to store bits by extracting from string.
        BigInteger sum[]=new BigInteger[n];
        // complementofsum --> long arr to be returned.
        BigInteger complementofsum[]=new BigInteger[n];
        System.out.println("Sum is ");
        //diff --> difference between string's length and n 
        // For Example : If the sum is "001100", while conveting it into long it will truncate the zero in the front leaving sum as "1100".
        //That will be resolved using the logic. if (diff is < n ), then we can understand that zero's has been truncated and can be added.
        int diff=0;
        if(totalsum.length()<n){
            System.out.println("if Total sum length is less than i.e the zeros that has been truncated being added here");
            diff=n-totalsum.length();
            int i=0;
            for(i=0;i<diff;i++){
                complementofsum[i]=BigInteger.ONE;
                System.out.println(complementofsum[i]);
                System.out.println(i+" is i ");
            }
            //strind --> to keep track of index of string.
            int strind=0;
            for(int j=i;j<n;j++){
                sum[j]=BigInteger.valueOf(Character.getNumericValue(totalsum.charAt(strind)));
                System.out.print(sum[j]+" ");
                strind++;
            }
        }
        // if sum's length == n 
        else{
            int strind=0;
            for(int j=0;j<n;j++){
                sum[j]=BigInteger.valueOf(Character.getNumericValue(totalsum.charAt(strind)));
                System.out.print(sum[j]+" ");
                strind++;
            }
        }
        System.out.println();
        //Finding and Printing complement of sum from diff to n (from 0 to n incase of sum's length=n and from diff to n incase of sum's length<n)
        System.out.println("Complement of sum is ");
        for(int i=diff;i<n;i++){
            if(sum[i].equals(BigInteger.ONE)){
                complementofsum[i]=BigInteger.ZERO;
                System.out.print(complementofsum[i]+" ");
            }
            else if(sum[i].equals(BigInteger.ZERO)){
                complementofsum[i]=BigInteger.ONE;
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
    public BigInteger binaryAddition(BigInteger op1,BigInteger op2,int n,int k){
        BigInteger backop1=op1;BigInteger backop2=op2;
        System.out.println("OP 1 "+op1);
        System.out.println("OP 2 "+op2);
        int i=0;
        //remainder --> Initially 0.
        BigInteger remainder=BigInteger.ZERO;
        //sumval --> Initially "".
        String sumval="";
        //sumValue --> Initially 0.
        BigInteger sumValue=BigInteger.ZERO;
        //sum --> array to store sum of binary numbers
        BigInteger sum[]=new BigInteger[n+n];
   
        // Summation Logic --> Until both operands becomes zero, extracting the digits and adding it storing it to sum and dividing operand by 10.
        while(op1!=BigInteger.ZERO || op2!=BigInteger.ZERO){
            //Extracting ith digits and adding it
            BigInteger intermideate_res= (op1 .mod(BigInteger.valueOf(10)) .add(op2.mod(BigInteger.valueOf(10))) ).add(remainder);
            //dividing it by % 2
            sum[i]=intermideate_res .mod(BigInteger.valueOf(2));
            //calc remainder
            remainder=intermideate_res .divide(BigInteger.valueOf(2));
            //dividing operands by 10
            op1=op1.divide(BigInteger.valueOf(10));
            op2=op2.divide(BigInteger.valueOf(10));
            i++;
        }
        // if remainder is not equal to zero
        if(remainder!=BigInteger.ZERO){
            sum[i++]=remainder;
        }
        i=i-1;
       //Printing summation
        System.out.println("Sum of two binary numbers: ");
            while (i >= 0) {
                System.out.print(sum[i]);
                //appending summation bits to sumval string 
                sumval+=sum[i].toString();
                //System.out.println("sumval "+sumval);
                i--;
            }
        
        System.out.println();
        System.out.println("end of while in binary addition ");
        
        try{
        
            if(backop1==BigInteger.ZERO && backop2==BigInteger.ZERO){
                System.out.println("Both Operands are 0 ");
                sumValue=BigInteger.valueOf(0);
            }
            else if(sumval.length()>n){
            //carry ind --> To find the length of carry
            int carryind=sumval.length()-n;
            //carry --> String from 0th bit to carryind bit
            String carry=sumval.substring(0,carryind);
            //sum_extracted --> String from carryind bit to end of string
            String sum_extracted=sumval.substring(carryind);
            //finalsum --> String to Long conversion of sum
            finalsum=new BigInteger(sum_extracted);
            //finalcarry --> String to Long conversion of carry
            finalcarry=new BigInteger(carry);
            System.out.println("Carry is "+finalcarry);
            System.out.println("Sum is "+finalsum);
            //sum -->finding sum of sum and carry
            BigInteger inter_sum=binaryAddition(finalcarry,finalsum,sum_extracted.length(),2);
            System.out.println("Total Sum is "+inter_sum);
            sumValue=inter_sum;
        }
            else{
                // converting sumval string to long
                sumValue=new BigInteger(sumval);
                System.out.println("Long type sum value "+sumValue);
                System.out.print("\n"); 
            }
        }
        catch(Exception ex){
            System.out.println(ex + "in binaryAddition Method");
            JOptionPane.showMessageDialog(frame, " The Value can't be stored in long data type !  "+ex.getMessage());
            frame.pack();
            throw ex;
        }
        return sumValue;

    }
    /*
    addAll Method -- Receiver
    Returns - Sum of k data frames {Data frames + Checksum} - long 
    Description : Find Summation of all data frames and checksum
        
    */
    public BigInteger addAll(int k,int n,BigInteger data[]){
        BigInteger sum=BigInteger.ZERO;
        try{
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
            sum=new BigInteger(InitialSum);
            // Adding all data frames 
            for(int j=0;j<k;j++){
                System.out.println("for entered : "+j);
                sum=binaryAddition(sum,data[j],n,k);
                System.out.println("From addAll For loop the sum is "+sum);
                System.out.println("for iteration  ended : "+j);
            }
            System.out.println("end of for");
            //sum_total --> Converting final sum of data frames into string.
            String sum_total=sum.toString();
            // If sum string is > n then overflow has occured.
            //Can be resolved by logic : Sum+carry=NewSum
            //Example --> 10+11=101 (1 is carry) --> 01+1=10 So, Sum is 10.
            /*if(sum_total.length()>n){
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
            
            }*/
            
        }
        catch (Exception e){
            sum=BigInteger.valueOf(-1);
            System.out.println(e + "in addAll Method");
            JOptionPane.showMessageDialog(frame, " The Value can't be stored in long data type !  "+e.getMessage());
            frame.pack();
        }
        return sum;
           
    } 
    /*
    addDataBits Method -- Sender
    Returns - Sum of k data frames - long 
    Description : Find Summation of all data frames
        
    */
    public BigInteger addDataBits(int k,int n,BigInteger data[]){
    BigInteger csum=BigInteger.ZERO;
    BigInteger sum=BigInteger.ZERO;
    try{
        //Checksum_str --> To store checksum as string
        String Checksum_str="";
        String totalsum_str="";
        //csum --> To store checksum as long
     
        //InitialSum --> To store Initial sum  into sum
        String InitialSum="";
        for(int i=0;i<n;i++){
            InitialSum+="0";
        }
        //sum --> To store sum
        sum=new BigInteger(InitialSum);
        // Adding all data frames 
        for(int j=0;j<k;j++){
            System.out.println("for entered : "+j);
            sum=binaryAddition(sum,data[j],n,k);
            System.out.println("From addDataBits For loop the sum is "+sum);
            System.out.println("for iteration ended: "+j);
        }   
        System.out.println("for ended");    
        //sum_total --> Converting final sum of data frames into string. 
        String sum_total=sum.toString();
        // If sum string is > n then overflow has occured.
        //Can be resolved by logic : Sum+carry=NewSum
        //Example --> 10+11=101 (1 is carry) --> 01+1=10 So, Sum is 10.
        /*if(sum_total.length()>n){
            System.out.println("Total Sum 's length is greater than N");
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
        }*/
        //else{
            System.out.println("Total Sum 's length is not greater than N");
            totalsum=sum;
            totalsum_str=sum_total;
            System.out.println(" Sum is "+totalsum);
            System.out.println("Total Sum str inside else is "+totalsum_str);
        //}
        System.out.println("Checksum callig from addDataBits");
        //checksum --> array to store checksum
        checksum=new BigInteger[n];            
        // calling findChecksum Method
        checksum=findChecksum(totalsum_str,n);
        // Converting Checksum to string
        for(int i=0;i<checksum.length;i++){
            Checksum_str+=checksum[i].toString();
        }
        System.out.println("String checksum is"+Checksum_str);
        // converting string to long
        csum=new BigInteger(Checksum_str);
        System.out.println("Int returned checksum is"+csum);
    }
    catch (Exception e){
        csum=BigInteger.valueOf(-1);
        System.out.println(e + "in addDataBits Method");
        JOptionPane.showMessageDialog(frame, " The Value can't be stored in long data type !  "+e.getMessage());
        frame.pack();

    }
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
                    data_arr_sender=new BigInteger[no_frames];
                    // textfield references for each frame
                    JTextField dataref[]=new JTextField[no_frames];
                    // label references for each frame
                    JLabel datareflab[]=new JLabel[no_frames];
                    // nextbt_data --> to proceed
                    JButton nextbt_data=new JButton("Next");
                    //coordinates
                    int xc=100;
                    int yc=100;
                   
                    JPanel senderpanel=new JPanel();
                    JScrollPane scrollableareasend = new JScrollPane(senderpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollableareasend.setLayout(new ScrollPaneLayout());
                    senderpanel.setPreferredSize(new Dimension(10000,4000));

                    // generating k textfield each of n bits
                    for(int i=0;i<no_frames;i++){
                        String promptdata="Frame "+(i+1);
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
                        data.setBounds(xc+100,yc,160,40);
                        //adding components
                        //frame.getContentPane().add(datalabel);
                        //frame.getContentPane().add(data);
                        //frame.pack();
                        senderpanel.add(datalabel);
                        senderpanel.add(data);
                        frame.pack();
                        yc+=100;
                        if(yc>3600){
                            yc=100;
                            xc+=300;
                        }
                    }
                    senderpanel.setBounds(100,100,1200,600);
                    scrollableareasend.setBounds(100,100,900,600);
                    senderpanel.setLayout(null);
                    scrollableareasend.setVisible(true);
                    frame.getContentPane().add(scrollableareasend);
                    frame.pack();
                    //setting bounds and adding component
                    nextbt_data.setFont(new Font("Verdana", Font.BOLD, 18));
                    nextbt_data.setBounds(xc,yc,200,40);
                    senderpanel.add(nextbt_data);
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
                                 
                                frame.getContentPane().remove(scrollableareasend);
                                frame.pack();
                                // Data collected from sender being stored into string arr
                                System.out.println("Data collected from sender");
                                for(int i=0;i<dataref.length;i++){
                                    data_collected[i]=dataref[i].getText();
                                    System.out.println(" data_collected [ "+ i +"] : " +data_collected[i]);
                                }
                                // Data collected from sender being extracted from string arr into long arr
                                System.out.println("Extracting data into array");
                                int datacorrect=0;
                                for(int i=0;i<no_frames;i++){
                                    try{
                                        data_arr_sender[i]=new BigInteger(data_collected[i]);
                                    }
                                    catch (Exception exc){
                                        datacorrect=-1;
                                        System.out.println(exc + " Data Sent by sender is too long");
                                        JOptionPane.showMessageDialog(frame, " The Value can't be stored in long data type !  "+exc.getMessage());
                                        frame.pack();
                                        break;
                                    }

                                }
                                if(datacorrect!=-1){
                                    // Printing Data collected from sender 
                                    System.out.println("Printing Data Array");
                                    for(int i=0;i<no_frames;i++){
                                        System.out.println("Frame : "+(i+1));
                                        System.out.println(data_arr_sender[i]);

                                    }
                                    //Calculating checksum by taking the complement of summation of data frames (By calling addDataBits method) 
                                    csum=addDataBits(no_frames,no_bits,data_arr_sender);
                                    System.out.println("csum is "+ csum);
                                    if(!csum.equals(BigInteger.valueOf(-1))){
                                        //setting bounds and adding component
                                        JLabel labelrecprompt=new JLabel("Enter Data Received at Receiver Side");
                                        labelrecprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                                        labelrecprompt.setBounds(100,100,400,40);
                                        frame.getContentPane().add(labelrecprompt);
                                        int xc=200,yc=200;
                                        JTextField datareceive[]=new JTextField[no_frames];
                                        JPanel receiverpanel=new JPanel();
                                        JScrollPane scrollableareareceive= new JScrollPane(receiverpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                        scrollableareareceive.setLayout(new ScrollPaneLayout());
                                        receiverpanel.setPreferredSize(new Dimension(10000,4000));
                                        // generating k textfield each of n bits --> To get data received
                                        for(int i=0;i<no_frames;i++){
                                            String promptdata="Frame "+(i+1);
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
                                            datalabel.setBounds(xc,yc,200,40);
                                            datalabel.setFont(new Font("Verdana", Font.BOLD, 18));
                                            data.setBounds(xc+100,yc,160,40);
                                            //frame.getContentPane().add(datalabel);
                                            //frame.getContentPane().add(data);
                                            //frame.pack();
                                            receiverpanel.add(datalabel);
                                            receiverpanel.add(data);
                                            frame.pack();
                                            yc+=100;
                                            if(yc>3600){
                                                yc=100;
                                                xc+=300;
                                            }
                                        }
                                        receiverpanel.setBounds(100,100,1200,600);
                                        scrollableareareceive.setBounds(100,100,900,600);
                                        receiverpanel.setLayout(null);
                                        scrollableareareceive.setVisible(true);
                                        frame.getContentPane().add(scrollableareareceive);
                                        frame.pack();
                                        //setting bounds and adding component
                                        JButton nextres=new JButton("Check Error");
                                        nextres.setBounds(xc,yc,200,40);
                                        receiverpanel.add(nextres);
                                        frame.pack();
                                        // Initialising array 
                                        data_arr_reciever=new BigInteger[no_frames+1];
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
                                                    int datareccorrect=0;
                                                    System.out.println("Extracting receiver data into array");
                                                    for(int i=0;i<no_frames;i++){
                                                        try{
                                                        data_arr_reciever[i]=new BigInteger(data_received[i]);
                                                        }
                                                        catch (Exception ex){
                                                            datareccorrect=-1;
                                                            System.out.println(ex + " Data Received by Receiver is too long");
                                                            JOptionPane.showMessageDialog(frame, " The Value can't be stored in long data type !  "+ex.getMessage());
                                                            frame.pack();
                                                            break;
                                                        }
                                                    }

                                                    if(datareccorrect!=-1){
                                                        // adding checksum produced as last element of data_arr_reciever
                                                        System.out.println("csum is "+ csum);
                                                        data_arr_reciever[no_frames]=csum;
                                                        // Printing data collected from receiver
                                                        System.out.println("Printing Data receiver Array");
                                                        for(int i=0;i<no_frames+1;i++){
                                                            System.out.println("Frame : "+(i+1));
                                                            System.out.println(data_arr_reciever[i]);
                                                        }
                                                        //Calculating sum of dataframes and checksum  (By calling addAll method) 
                                                        value=addAll(no_frames+1,no_bits,data_arr_reciever);
                                                        if(!value.equals(BigInteger.valueOf(-1))){
                                                            System.out.println("return value"+value); 
                                                            String strvalue=value.toString();
                                                            System.out.println("return value string"+strvalue); 
                                                            String final_value=strvalue;
                                                            System.out.println("final value  string"+final_value); 
                                                            //checksum_receiver --> long arr to store sum of data units and checksum calc at receiver end
                                                            checksum_receiver=new BigInteger[no_bits];
                                                            // if sum of data units and checksum  is less than no_bits then zeros in front will be truncated.
                                                            //resolving that
                                                            if(final_value.length()<no_bits){
                                                                int diff=no_bits-final_value.length();
                                                                int strind=0;
                                                                for(int i=0;i<diff;i++){
                                                                    checksum_receiver[i]=BigInteger.ZERO;
                                                                    System.out.print(checksum_receiver[i]);
                                                                }
                                                                for(int j=diff;j<no_bits;j++){
                                                                    checksum_receiver[j]=BigInteger.valueOf(Character.getNumericValue(final_value.charAt(strind)));
                                                                    strind++;
                                                                    System.out.print(checksum_receiver[j]);
                                                                }
                                                                System.out.println();
                                                            }
                                                            // if  sum of data units and checksum  is == no_bits
                                                            else{
                                                                for(int i=0;i<no_bits;i++){
                                                                    checksum_receiver[i]=BigInteger.valueOf(Character.getNumericValue(final_value.charAt(i)));
                                                                }
                                                            }
                                                            JLabel result = new JLabel(" THE RESULT IS : ");
                                                            boolean flag_checksum=false;
                                                            //complementing the sum
                                                            checksum_receiver=complement(checksum_receiver);
                                                            // if all zeros --> no error else error
                                                            for(int i=0;i<checksum_receiver.length;i++){
                                                                
                                                                if(checksum_receiver[i]==BigInteger.ZERO){
                                                                    continue;
                                                                }
                                                                else if(checksum_receiver[i]!=BigInteger.ZERO){
                                                                    flag_checksum=true;
                                                                    break;
                                                                }
                                                            }
                                                            if(flag_checksum==true){
                                                                System.out.println("RESULT IS ERROR");
                                                                result.setText(result.getText()+"  ERROR");
                                                                result.setForeground(new Color(255,0,0));
                                                                
                                                            }
                                                            else if(flag_checksum==false){
                                                                System.out.println(" RESULT IS NO ERROR");
                                                                result.setForeground(new Color(22, 163, 74));
                                                                result.setText(result.getText()+"  NO ERROR");
                                                            }
                                                            result.setBounds(10,10,800,80);
                                                            result.setFont(new Font("Verdana", Font.BOLD, 18));
                                                            frame.getContentPane().add(result);
                                                            frame.pack();
                                                            

                                                        }
                                                    }
                                                }
                                            }
                                        );
                                    }
                                }
                                
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
        CSGUI checksumgui=new CSGUI();
        checksumgui.BuildGUI();
    }

}