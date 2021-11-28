import java.util.*;
import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;

public class HammingGUI{
    JFrame frame;
    JLabel nprompt;
    JButton proceedwithnbt;
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

    public int addDedBit(int data[]){
        int cnt=0;
        for(int i=0;i<data.length;i++){
            if(data[i]==1){
                cnt++;
            }
        }
        if(cnt%2==0){
            cnt=0;
        }
        else{
            cnt=1;
        }
        return cnt;
        
    }           
    public int calculateNoOfRedundancyBits(int n){
        int r=0;
        while(Math.pow(2,r) < (n+r+1)){
            System.out.println("Iteration "+r+" : "+"LHS : "+Math.pow(2,r)+"  RHS : "+(n+r+1));
            r++;
        }
        System.out.println("Number of Redundancy Bits are "+r);
        return r;
    }
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
        int r1[]={3,5,7,9,11,13,15,17,19,21,23,25,27,29,31};
        int r2[]={3,6,7,10,11,14,15,18,19,22,23,26,27,30,31};
        int r3[]={5,6,7,12,13,14,15,20,21,22,23,28,29,30,31};
        int r4[]={9,10,11,12,13,14,15,20,21,22,23,28,29,30,31};
        int r5[]={17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
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
                case 4: 
                System.out.println("Case :"+i); 
                result[i]=recalcParity(received_parity[i],received_pure_data,r5);break;
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
        int r1[]={3,5,7,9,11,13,15,17,19,21,23,25,27,29,31};
        int r2[]={3,6,7,10,11,14,15,18,19,22,23,26,27,30,31};
        int r3[]={5,6,7,12,13,14,15,20,21,22,23,28,29,30,31};
        int r4[]={9,10,11,12,13,14,15,20,21,22,23,28,29,30,31};
        int r5[]={17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        
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
                case 4: 
                redundancybits[i]=parityValue(data,r5);break;
            }
            
        }
        System.out.println("Redundancy bits calculated");
        for(int i=0;i<redundancybits.length;i++){
            System.out.println(redundancybits[i]);
        }
        return redundancybits;
    }

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
                        nextbt_data.addActionListener(
                            new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent e){
                                    frame.getContentPane().remove(datalabel);
                                    frame.getContentPane().remove(databits);
                                    frame.getContentPane().remove(nextbt_data);
                                    JLabel displayRedundancyBits=new JLabel("Parity Bits Calculated : ");
                                    for(int i=0;i<redundancybits.length;i++){
                                        displayRedundancyBits.setText(displayRedundancyBits.getText()+redundancybits[i]);
                                    }
                                    JLabel enterrecdataprompt=new JLabel("Enter Data that has been received by the receiver");
                                    JTextField data_received=new JTextField(40);
                                    JButton data_rec_bt=new JButton("Next");
                                    data_rec_bt.addActionListener(
                                        new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent e){
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
                                                ded_received=addDedBit(data_receiver_arr);
                                                System.out.println("Recalculated DED bit at receiver side is "+ded_received);
                                                displaygnrlparityrec.setText(displaygnrlparityrec.getText()+ded_received);
                                                checkparity=new int[noofredbits];
                                                checkparity=checkReceivedParity(data_receiver_parity,data_receiver_arr,data_receiver_parity.length);
                                                decimal_error_bit=BinaryToDecimal(checkparity);
                                                System.out.println("Error bit is "+decimal_error_bit);
                                                displayrecdata.setFont(new Font("Verdana", Font.BOLD, 18));
                                                displaygnrlparityrec.setFont(new Font("Verdana", Font.BOLD, 18));
                                                result.setFont(new Font("Verdana", Font.BOLD, 18));
                                                frame.getContentPane().add(displayrecdata);
                                                frame.getContentPane().add(displaygnrlparityrec);
                                                result.setForeground(new Color(0,0,255));
                                                frame.getContentPane().add(result);
                                                frame.pack();

                                                if(ded_received==dedbit_sender && decimal_error_bit==0 ){
                                                    System.out.println("NO ERROR");
                                                    result.setText(result.getText()+"NO ERROR ");
                                                } 
                                                else if(ded_received==dedbit_sender && decimal_error_bit!=0 ){
                                                    System.out.println("DED --> Double Bit Error Detected and Cannot be Corrected");
                                                    result.setText(result.getText()+"DED --> Double Bit Error Detected and Cannot be Corrected ");
                                                }
                                        
                                                else if(ded_received!=dedbit_sender && decimal_error_bit!=0){
                                                    displayerrorbit.setText(displayerrorbit.getText()+decimal_error_bit);
                                                    result.setText(result.getText()+"SEDC --> Single Bit Error Detected and Corrected ");
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
                                                    displayerrorbit.setFont(new Font("Verdana", Font.BOLD, 18));
                                                    frame.getContentPane().add(displayerrorbit);
                                                    correctedData.setFont(new Font("Verdana", Font.BOLD, 18));
                                                    frame.getContentPane().add(correctedData);
                                                    frame.pack();
                                                }
                                               
                                            
                                            }
                                        }
                                    
                                    );
                                    displayRedundancyBits.setFont(new Font("Verdana", Font.BOLD, 18));
                                    enterrecdataprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                                    frame.getContentPane().add(displayRedundancyBits);
                                    frame.getContentPane().add(enterrecdataprompt);
                                    frame.getContentPane().add(data_received);
                                    frame.getContentPane().add(data_rec_bt);
                                    frame.pack();
                                }
                            }
                        
                        );
                        datalabel.setFont(new Font("Verdana", Font.BOLD, 18));
                        nextbt_data.setFont(new Font("Verdana", Font.PLAIN, 18));
                        frame.getContentPane().add(datalabel);
                        frame.getContentPane().add(databits);
                        frame.getContentPane().add(nextbt_data);
                        frame.pack();
                        nextbt_data.addActionListener(
                            new java.awt.event.ActionListener(){
                                public void actionPerformed(java.awt.event.ActionEvent e){
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
                                    redundancybits=calculateParityBits(noofredbits,data_red_added);

                                    k_redind=0;dataind=0;
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
                                    dedbit_sender=addDedBit(data_red_added);
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
                                    frame.getContentPane().add(displaydatahamming);
                                    frame.getContentPane().add(gnrlparity);

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
        info.setFont(new Font("Verdana", Font.BOLD, 18));
        proceedwithnbt.setFont(new Font("Verdana", Font.PLAIN, 18));
        frame.getContentPane().add(info);
        frame.getContentPane().add(nprompt);
        frame.getContentPane().add(noofdatabits);
        frame.getContentPane().add(proceedwithnbt);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(254,243,199));
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(800,500));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String args[]){
        HammingGUI gui = new HammingGUI();
        gui.BuildGUI();
    }

}
