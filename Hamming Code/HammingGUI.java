import java.util.*;
import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;

public class HammingGUI{
    JFrame frame;
    JLabel nprompt;
    JButton proceedwithnbt;
    int parchoice=-1;
    int noofbits=0;
    int noofredbits=0;
    int data_sender_arr[];
    int data_receiver_arr[];
    int data_receiver_parity[];
    int data_receiver_data[];
    int checkparity[];
    int dedbit_sender=-1;
    int ded_received=-1;
    int redundancybits[];
    int data_red_added[];
    int decimal_error_bit=-1;
    String data_sender="";
    String data_receiver="";
    JTextField noofdatabits;
     /*
    calcOddParity method
    Returns - parity bit --> int
    Description : 
        calculate parity bit using odd parity logic
        if number of ones in data unit is even --> parity bit is 1. Otherwise parity bit is 0.
    */
    public int calcOddParity(int data[]){
        //retval --> value to be returned
        int retval=0;
        for(int i=0;i<data.length;i++){
            if(data[i]==1){
                retval++;
            }
        }
        if(retval%2==0){
            retval=1;
        }
        else{
            retval=0;
        }
        return retval;
    }
     /*
    BinaryToDecimal method
    Returns - decimal equivalent of binary number --> int
    Description : converts binary to decimal
    */
    public int BinaryToDecimal(int []binary_data){  
        int decimal = 0;  
        int n = 0;  
        while(n<binary_data.length){  
            System.out.println("Bit "+binary_data[n]);
            decimal += binary_data[n]*Math.pow(2, n);  
            System.out.println("Decimal at iteration "+n +" "+decimal);
            n++;  
                  
        }  
    return decimal;  
    }   
     /*
    addDedBit method
    Returns - ded i.e GP bit --> int
    Description : 
        calculate ded bit using  parity logic
    */
    public int addDedBit(int data[],int choice){
        int cnt=0;
        for(int i=0;i<data.length;i++){
            if(data[i]==1){
                cnt++;
            }
        }
        if(choice==1){
            if(cnt%2==0){
                cnt=0;
            }
            else{
                cnt=1;
            }
        }
        else if(choice==0){
             if(cnt%2==0){
                cnt=1;
            }
            else{
                cnt=0;
            }
        }
        return cnt;
        
    }           
    /*
    calculateNoOfRedundancyBits method
    Returns -no of red bits--> int
    Description : 
        calculate no of red.bits using 2^r<=n+r+1 logic
    */
    public int calculateNoOfRedundancyBits(int n){
        int r=0;
        while(Math.pow(2,r) < (n+r+1)){
            System.out.println("Iteration "+r+" : "+"LHS : "+Math.pow(2,r)+"  RHS : "+(n+r+1));
            r++;
        }
        System.out.println("Number of Redundancy Bits are "+r);
        return r;
    }
    /*
    checkPowerOfTwo method
    Returns -yes if it is power of 2--> int
    Description : 
        checks whether no is power of 2
    */
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
   /*
    parityValue method
    Returns -parity--> int
    Description : 
        calculates parity bit at sender side
    */
    public int parityValue(int data[],int redvalue[],int choice){
        int retval=0;
        for(int i=0;i<redvalue.length;i++){
            if(redvalue[i]>data.length){
                break;
            }
            if(data[redvalue[i]-1]==1){
                retval++;
            }
        }
        if(choice==1){
            if(retval%2==0){
                retval=0;
            }
            else{
                retval=1;
            }
        }
        else if(choice==0){
             if(retval%2==0){
                retval=1;
            }
            else{
                retval=0;
            }
        }
        return retval;
    }
     /*
    recalcParity method
    Returns -parity--> int
    Description : 
        calculates parity bit at receiver side
    */
    public int recalcParity(int paritybit,int data[],int redvalue[],int choice){
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
        if(choice==1){
            if(retval%2==0){
                retval=0;
            }
            else{
                retval=1;
            }
        }
        else if(choice==0){
            if(retval%2==0){
                retval=1;
            }
            else{
                retval=0;
            }
        }
        return retval;
    }
    /*
    checkReceivedParity method
    Returns -parity arr--> int
    Description : 
        calculates all parity bits at receiver side
    */
    public int[] checkReceivedParity(int received_parity[],int received_pure_data[],int n,int choice){
        int result[]=new int[n];
        int r1[]={3,5,7,9,11,13,15,17,19,21,23,25,27,29,31};
        int r2[]={3,6,7,10,11,14,15,18,19,22,23,26,27,30,31};
        int r3[]={5,6,7,12,13,14,15,20,21,22,23,28,29,30,31};
        int r4[]={9,10,11,12,13,14,15,24,25,26,27,28,29,30,31};
        int r5[]={17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        for(int i=received_parity.length-1;i>=0;i--){
            switch(i){
                case 0: 
                System.out.println("Case :"+i);
                result[i]=recalcParity(received_parity[i],received_pure_data,r1,choice);break;
                case 1: 
                System.out.println("Case :"+i);
                result[i]=recalcParity(received_parity[i],received_pure_data,r2,choice);break;
                case 2: 
                System.out.println("Case :"+i);
                result[i]=recalcParity(received_parity[i],received_pure_data,r3,choice);break;
                case 3:
                System.out.println("Case :"+i); 
                result[i]=recalcParity(received_parity[i],received_pure_data,r4,choice);break;
                case 4: 
                System.out.println("Case :"+i); 
                result[i]=recalcParity(received_parity[i],received_pure_data,r5,choice);break;
            }
            
        }
        System.out.println("Redundancy bits re-calculated");
        for(int i=received_parity.length-1;i>=0;i--){
            System.out.println(result[i]);
        }
        return result;

    }
    /*
    calculateParityBits method
    Returns -parity arr--> int
    Description : 
        calculates all parity bits at sender side
    */
    public  int[] calculateParityBits(int r,int data[],int choice){
        int redundancybits[]=new int[r];
        int r1[]={3,5,7,9,11,13,15,17,19,21,23,25,27,29,31};
        int r2[]={3,6,7,10,11,14,15,18,19,22,23,26,27,30,31};
        int r3[]={5,6,7,12,13,14,15,20,21,22,23,28,29,30,31};
        int r4[]={9,10,11,12,13,14,15,24,25,26,27,28,29,30,31};
        int r5[]={17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        
        for(int i=0;i<redundancybits.length;i++){
            switch(i){
                case 0: 
                redundancybits[i]=parityValue(data,r1,choice);break;
                case 1: 
                redundancybits[i]=parityValue(data,r2,choice);break;
                case 2: 
                redundancybits[i]=parityValue(data,r3,choice);break;
                case 3: 
                redundancybits[i]=parityValue(data,r4,choice);break;
                case 4: 
                redundancybits[i]=parityValue(data,r5,choice);break;
            }
            
        }
        System.out.println("Redundancy bits calculated");
        for(int i=0;i<redundancybits.length;i++){
            System.out.println(redundancybits[i]);
        }
        return redundancybits;
    }
    /*
    BuildGUI method
    Returns - nothing
    Description : 
       BUILDS GUI COMPONENTS
    */
    public void BuildGUI(){
        frame=new JFrame("Error Correction - Hamming Code(26,31)");
        JLabel info=new JLabel("HAMMING CODE ( 26,31) ");
        nprompt=new JLabel("Enter No of Data Bits");
        noofdatabits=new JTextField(40);
        proceedwithnbt=new JButton("Next");
        proceedwithnbt.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e){
                    noofbits=Integer.parseInt(noofdatabits.getText());
                    if(noofbits >=27){
                        JOptionPane.showMessageDialog(frame, "Enter any no between 1-26 ! This is HammingCode(31,26)");
                    }
                    else{
                        frame.getContentPane().remove(nprompt);
                        frame.getContentPane().remove(noofdatabits);
                        frame.getContentPane().remove(proceedwithnbt);
                        System.out.println("No of databits : "+noofbits);
                        JLabel datalabel=new JLabel("Enter data");
                        JTextField databits=new JTextField(40);
                        JButton nextbt_data=new JButton("Next");
                        datalabel.setFont(new Font("Verdana", Font.BOLD, 18));
                        nextbt_data.setFont(new Font("Verdana", Font.PLAIN, 18));
                        datalabel.setBounds(100,100,1000,80);
                        databits.setBounds(100,180,200,40);
                        nextbt_data.setBounds(100,260,160,40);
                        frame.getContentPane().add(datalabel);
                        frame.getContentPane().add(databits);
                        frame.getContentPane().add(nextbt_data);
                        frame.pack();
                        nextbt_data.addActionListener(
                            new java.awt.event.ActionListener(){
                                public void actionPerformed(java.awt.event.ActionEvent e){
                                    frame.getContentPane().remove(datalabel);
                                    frame.getContentPane().remove(databits);
                                    frame.getContentPane().remove(nextbt_data);
                                    JLabel displaydatahamming=new JLabel(" Data that has to be sent to receiver is :");
                                    JLabel gnrlparity=new JLabel("General Parity Bit is ");
                                    data_sender=databits.getText();
                                    data_sender_arr=new int[data_sender.length()];
                                    noofredbits=calculateNoOfRedundancyBits(data_sender.length());
                                    redundancybits=new int[noofredbits];
                                    for(int i=0;i<noofredbits;i++){
                                        System.out.println("Initialised Redundancy Bits to Zero");
                                        redundancybits[i]=0;
                                    }
                                    System.out.println("Data entered by sender : ");
                                    for(int i=0;i<data_sender.length();i++){
                                        data_sender_arr[i]=Character.getNumericValue(data_sender.charAt(i));
                                        System.out.print(data_sender_arr[i]);
                                    }
                                    System.out.println();
                                    data_red_added=new int[data_sender_arr.length+redundancybits.length];
                                    int k_redind=0;int dataind=0;
                                    for(int i=1;i<=data_red_added.length;i++){
                                        if(i==1 || checkPowerOfTwo(i)){
                                            data_red_added[i-1]=redundancybits[k_redind];
                                            System.out.println("i is  "+i+" Data is "+ data_red_added[i-1]);
                                            k_redind+=1;
                                        }
                                        else{
                                            data_red_added[i-1]=data_sender_arr[dataind];
                                            System.out.println("i is  "+i+" Data is "+ data_red_added[i-1]);
                                            dataind+=1;
                                        }
                                    }
                                    JLabel enterparity= new JLabel("CHOOSE PARITY");
                                    // r1,r2 --> radiobutton to choose parity
                                    JRadioButton r1=new JRadioButton("EVEN PARITY");    
                                    JRadioButton r2=new JRadioButton("ODD PARITY");  
                                    //buttongroup  
                                    ButtonGroup bg=new ButtonGroup();
                                    JButton calculateparity=new  JButton("Calculate Parity");
                                    enterparity.setBounds(100,100,1000,80);
                                    r1.setBounds(100,180,200,80);
                                    r2.setBounds(100,260,200,80);
                                    calculateparity.setBounds(100,340,160,40);
                                    frame.getContentPane().add(enterparity); 
                                    frame.getContentPane().add(r1); 
                                    frame.getContentPane().add(r2); 
                                    frame.getContentPane().add(calculateparity);
                                    frame.pack(); 
                                    
                                    calculateparity.addActionListener(
                                        new java.awt.event.ActionListener(){
                                            public void actionPerformed(java.awt.event.ActionEvent e){
                                                frame.getContentPane().remove(enterparity); 
                                                frame.getContentPane().remove(r1); 
                                                frame.getContentPane().remove(r2); 
                                                frame.getContentPane().remove(calculateparity);
                                                if(r1.isSelected()){
                                                    parchoice=1;
                                                    System.out.println("Choosen EVEN PARITY");
                                                    redundancybits=calculateParityBits(noofredbits,data_red_added,parchoice);
                                                }
                                                else if(r2.isSelected()){
                                                    parchoice=0;
                                                    System.out.println("Choosen EVEN PARITY");
                                                    redundancybits=calculateParityBits(noofredbits,data_red_added,parchoice);
                                                }
                                                int k_redind=0;int dataind=0;
                                                for(int i=1;i<=data_red_added.length;i++){
                                                    if(i==1 || checkPowerOfTwo(i)){
                                                        data_red_added[i-1]=redundancybits[k_redind];
                                                        //System.out.println("i is  "+i+" Data is "+ data_redundancy[i-1]);
                                                        k_redind+=1;
                                                    }
                                                    else{
                                                        data_red_added[i-1]=data_sender_arr[dataind];
                                                        //System.out.println("i is  "+i+" Data is "+ data_redundancy[i-1]);
                                                        dataind+=1;
                                                    }
                                                }
                                               
                                                System.out.println("Parity choice is "+parchoice);
                                                dedbit_sender=addDedBit(data_red_added,parchoice);
                                                
                                                System.out.println("DED BIT FROM SENDER "+dedbit_sender);
                                                gnrlparity.setText(gnrlparity.getText()+dedbit_sender);
                                                System.out.println("Data With hamming code");
                                                for(int i=0;i<data_red_added.length;i++){
                                                    System.out.print(data_red_added[i]);
                                                    displaydatahamming.setText(displaydatahamming.getText()+data_red_added[i]+" ");

                                                }
                                                System.out.println();
                                                gnrlparity.setFont(new Font("Verdana", Font.BOLD, 18));
                                                displaydatahamming.setFont(new Font("Verdana", Font.BOLD, 18));
                                                displaydatahamming.setBounds(100,100,10000,80);
                                                gnrlparity.setBounds(100,180,10000,80);
                                                frame.getContentPane().add(displaydatahamming);
                                                frame.getContentPane().add(gnrlparity);
                                                
                                                JLabel displayRedundancyBits=new JLabel("Parity Bits Calculated : ");
                                                for(int i=0;i<redundancybits.length;i++){
                                                    displayRedundancyBits.setText(displayRedundancyBits.getText()+redundancybits[i]);
                                                }
                                                JLabel enterrecdataprompt=new JLabel("Enter Data that has been received by the receiver");
                                                JTextField data_received=new JTextField(40);
                                                JButton data_rec_bt=new JButton("Next");
                                    
                                                displayRedundancyBits.setFont(new Font("Verdana", Font.BOLD, 18));
                                                enterrecdataprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                                                displayRedundancyBits.setBounds(100,260,10000,80);
                                                enterrecdataprompt.setBounds(100,360,10000,80);
                                                data_received.setBounds(100,440,200,40);
                                                data_rec_bt.setBounds(100,520,160,40);
                                                frame.getContentPane().add(displayRedundancyBits);
                                                frame.getContentPane().add(enterrecdataprompt);
                                                frame.getContentPane().add(data_received);
                                                frame.getContentPane().add(data_rec_bt);
                                                frame.pack();

                                                data_rec_bt.addActionListener(
                                                    new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent e){
                                                            
                                                            frame.getContentPane().remove(enterrecdataprompt);
                                                            frame.getContentPane().remove(data_received);
                                                            frame.getContentPane().remove(data_rec_bt);
                                                            JLabel displayerrorbit=new JLabel("Error at the bit position ");
                                                            JLabel displaygnrlparityrec=new JLabel("General Parity Recalculated at Receiver Side");
                                                            JLabel correctedData=new JLabel("Data after correction by using Invertion Technique ");
                                                            JLabel displayrecdata=new JLabel("Data Received  ");
                                                            JLabel result=new JLabel(" Result is ");
                                                            data_receiver=data_received.getText();
                                                        
                                                            System.out.println("Data Entered By Receiver");
                                                            data_receiver_arr=new int[data_receiver.length()];
                                                            data_receiver_parity=new int[noofredbits];
                                                            data_receiver_data=new int[noofbits];
                                                            for(int i=0;i<data_receiver_arr.length;i++){
                                                                data_receiver_arr[i]=Character.getNumericValue(data_receiver.charAt(i));
                                                                System.out.print(data_receiver_arr[i]);
                                                                displayrecdata.setText(displayrecdata.getText()+data_receiver_arr[i]+" ");
                                                            }
                                                            int k_redind=0;int dataind=0;
                                                            for(int i=1;i<=data_receiver_arr.length;i++){
                                                                if(i==1 || checkPowerOfTwo(i)){
                                                                    data_receiver_parity[k_redind]=data_receiver_arr[i-1];
                                                                    System.out.println("i is  "+i+" PA "+ data_receiver_parity[k_redind]);
                                                                    k_redind+=1;
                                                                }
                                                                else{
                                                                    data_receiver_data[dataind]=data_receiver_arr[i-1];
                                                                    System.out.println("i is  "+i+" PD "+   data_receiver_data[dataind]);
                                                                    dataind+=1;
                                                                }
                                                            }
                                                            ded_received=addDedBit(data_receiver_arr,parchoice);
                                                            System.out.println("Recalculated DED bit at receiver side is "+ded_received);
                                                            displaygnrlparityrec.setText(displaygnrlparityrec.getText()+ded_received);
                                                            checkparity=new int[noofredbits];
                                                            checkparity=checkReceivedParity(data_receiver_parity,data_receiver_arr,data_receiver_parity.length,parchoice);
                                                            decimal_error_bit=BinaryToDecimal(checkparity);
                                                            System.out.println("Error bit is "+decimal_error_bit);
                                                            displayrecdata.setFont(new Font("Verdana", Font.BOLD, 18));
                                                            displaygnrlparityrec.setFont(new Font("Verdana", Font.BOLD, 18));
                                                            result.setFont(new Font("Verdana", Font.BOLD, 18));
                                                            displayrecdata.setBounds(100,300,10000,80);
                                                            displaygnrlparityrec.setBounds(100,380,10000,80);
                                                            
                                                            result.setBounds(100,440,10000,40);
                                                            
                                                            frame.getContentPane().add(displayrecdata);
                                                            frame.getContentPane().add(displaygnrlparityrec);
                                                            result.setForeground(new Color(0,0,255));
                                                            frame.getContentPane().add(result);
                                                            frame.pack();

                                                            if(ded_received==dedbit_sender && decimal_error_bit==0 ){
                                                                System.out.println("NO ERROR");
                                                                result.setText(result.getText()+"NO ERROR ");
                                                                result.setForeground(new Color(22, 163, 74));
                                                            } 
                                                            else if(ded_received==dedbit_sender && decimal_error_bit!=0 ){
                                                                System.out.println("DED --> Double Bit Error Detected and Cannot be Corrected");
                                                                result.setText(result.getText()+"DED --> Double Bit Error Detected and Cannot be Corrected ");
                                                                result.setForeground(new Color(255, 0, 0));
                                                            }
                                                    
                                                            else if(ded_received!=dedbit_sender && decimal_error_bit!=0){
                                                                displayerrorbit.setText(displayerrorbit.getText()+decimal_error_bit);
                                                                result.setText(result.getText()+"SEDC --> Single Bit Error Detected and Corrected ");
                                                                result.setForeground(new Color(255, 0, 0));
                                                                if(data_receiver_arr[decimal_error_bit-1]==0){
                                                                    data_receiver_arr[decimal_error_bit-1]=1;
                                                                }
                                                                else{
                                                                    data_receiver_arr[decimal_error_bit-1]=0;
                                                                }
                                                                System.out.println("Corrected Error bit is by invertion");
                                                                for(int i=0;i<data_receiver_arr.length;i++){
                                                                    System.out.print(data_receiver_arr[i]);
                                                                    correctedData.setText(correctedData.getText()+data_receiver_arr[i]+" ");
                                                                }  
                                                                System.out.println();
                                                                displayerrorbit.setBounds(100,520,10000,80);
                                                                correctedData.setBounds(100,580,10000,80);
                                                                displayerrorbit.setFont(new Font("Verdana", Font.BOLD, 18));
                                                                correctedData.setForeground(new Color(22, 163, 74));
                                                                frame.getContentPane().add(displayerrorbit);
                                                                correctedData.setFont(new Font("Verdana", Font.BOLD, 18));
                                                                frame.getContentPane().add(correctedData);
                                                                frame.pack();
                                                            }
                                                             else{
                                                                System.out.println("DED CALCULATED AT BOTH ENDS DOES NOT MATCH AND POSITION IS 0");
                                                                result.setText(result.getText()+"DED CALCULATED AT BOTH ENDS DOES NOT MATCH AND POSITION IS 0");
                                                            }
                                                        
                                                               
                                                                
                                                            
                                                        }
                                                    }
                                                );
                                            }
                                        }
                                    );
                                }
                            }
                        );
                        databits.getDocument().addDocumentListener(
                            new javax.swing.event.DocumentListener(){
                                public void changedUpdate(javax.swing.event.DocumentEvent e){}
                                public void insertUpdate(javax.swing.event.DocumentEvent e){
                                    if(databits.getText().length()==noofbits){
                                        System.out.println("Disabled");
                                        databits.setEditable(false);
                                    }                                
                                }
                                public void removeUpdate(javax.swing.event.DocumentEvent e){}
                            }                        
                        ); 
                    }
                }
            }
        );
        
        nprompt.setFont(new Font("Verdana", Font.BOLD, 18));
        nprompt.setBounds(100,180,1000,80);
        info.setFont(new Font("Verdana", Font.BOLD, 18));
        info.setBounds(450,10,1000,80);
        noofdatabits.setBounds(100,260,200,40); 
        proceedwithnbt.setFont(new Font("Verdana", Font.PLAIN, 18));
        proceedwithnbt.setBounds(100,320,200,40);
        JLabel dummy=new JLabel();
        frame.getContentPane().add(info);
        frame.getContentPane().add(nprompt);
        frame.getContentPane().add(noofdatabits);
        frame.getContentPane().add(proceedwithnbt);
        frame.getContentPane().add(dummy);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(254,243,199));
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(1200,1000));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String args[]){
        HammingGUI gui = new HammingGUI();
        gui.BuildGUI();
    }

}
