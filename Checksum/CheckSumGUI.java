import java.util.*;
import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;
public class CheckSumGUI{
    JFrame frame;
    JTextField noofframes;
    JTextField noofdatabits;
    JButton nextbt;
    JLabel framesprompt;
    JLabel nprompt;
    int no_frames=0;
    String data_collected[];
    String data_received[];
    int data_arr_sender[];
    int data_arr_reciever[];
    int checksum_receiver[];
    int no_bits=0;
    int checksum[];
    int csum;
    int value;
    int finalsum;
    int finalcarry;
    int totalsum=0;

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
    public int binaryAddition(int op1,int op2,int n,int k){
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

    public int addAll(int k,int n,int data[]){
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
            sum=binaryAddition(finalcarry,finalsum,sum_extracted.length(),2);
            System.out.println("Total Sum is "+totalsum);
            String totalsum_str=Integer.toString(totalsum);
        }
        return sum;
           
    } 
    public int addDataBits(int k,int n,int data[]){
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

    public void BuildGUI(){
        frame=new JFrame("Error Detector - CHECKSUM");
        framesprompt=new JLabel("Enter the No of Frames");
        noofframes=new JTextField(30);
        nprompt=new JLabel("Enter the No of bits in each frame");
        noofdatabits=new JTextField(30);
        nextbt=new JButton("Next");
        framesprompt.setBounds(100,100,1000,120);
        framesprompt.setFont(new Font("Verdana", Font.BOLD, 18));
        noofframes.setBounds(100,180,200,40);
        nprompt.setBounds(100,220,1000,120);
        nprompt.setFont(new Font("Verdana", Font.BOLD, 18));
        noofdatabits.setBounds(100,300,200,40);
        nextbt.setBounds(100,400,100,40);
        frame.getContentPane().add(framesprompt);
        frame.getContentPane().add(noofframes);
        frame.getContentPane().add(nprompt);
        frame.getContentPane().add(noofdatabits);
        frame.getContentPane().add(nextbt);
        nextbt.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e){
                    frame.getContentPane().remove(framesprompt);
                    frame.getContentPane().remove(noofframes);
                    frame.getContentPane().remove(nprompt);
                    frame.getContentPane().remove(noofdatabits);
                    frame.getContentPane().remove(nextbt);
                    no_frames=Integer.parseInt(noofframes.getText());
                    no_bits=Integer.parseInt(noofdatabits.getText());
                    System.out.println("No of Frames : "+no_frames);
                    System.out.println("No of Bits in each frame : "+no_bits);
                    data_collected=new String[no_frames];
                    data_arr_sender=new int[no_frames];
                    JTextField dataref[]=new JTextField[no_frames];
                    JLabel datareflab[]=new JLabel[no_frames];
                    JButton nextbt_data=new JButton("Next");
                    int xc=100;
                    int yc=100;
                    for(int i=0;i<no_frames;i++){
                        String promptdata=" Enter Data for Frame "+(i+1);
                        JLabel datalabel=new JLabel(promptdata);
                        JTextField data=new JTextField(no_bits);
                        dataref[i]=data;
                        datareflab[i]=datalabel;
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
                    nextbt_data.setFont(new Font("Verdana", Font.BOLD, 18));
                    nextbt_data.setBounds(xc,yc,200,40);
                    frame.getContentPane().add(nextbt_data);
                    nextbt_data.addActionListener(
                        new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e){
                                for(int i=0;i<dataref.length;i++){
                                frame.getContentPane().remove(dataref[i]);
                                frame.getContentPane().remove(datareflab[i]);
                                  frame.pack();
                                }
                                 frame.getContentPane().remove(nextbt_data);
                                 frame.pack();
                                System.out.println("Data collected from sender");
                                for(int i=0;i<dataref.length;i++){
                                    data_collected[i]=dataref[i].getText();
                                    System.out.println(" data_collected [ "+ i +"] : " +data_collected[i]);
                                }
                                System.out.println("Extracting data into array");
                                for(int i=0;i<no_frames;i++){
                                    data_arr_sender[i]=Integer.parseInt(data_collected[i]);
                                }
                                System.out.println("Printing Data Array");
                                for(int i=0;i<no_frames;i++){
                                    System.out.println("Frame : "+(i+1));
                                    for(int j=0;j<no_bits;j++){
                                        System.out.println(data_arr_sender[i]);
                                    }
                                }
                                csum=addDataBits(no_frames,no_bits,data_arr_sender);

                                JLabel labelrecprompt=new JLabel("Enter Data Received at Receiver Side");
                                labelrecprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                                labelrecprompt.setBounds(100,100,400,40);
                                frame.getContentPane().add(labelrecprompt);
                               int xc=200,yc=200;
                                JTextField datareceive[]=new JTextField[no_frames];
                             
                                for(int i=0;i<no_frames;i++){
                                    String promptdata=" Enter Data for Frame "+(i+1);
                                    JLabel datalabel=new JLabel(promptdata);
                                    JTextField data=new JTextField(no_bits);
                                    datareceive[i]=data;
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
                                JButton nextres=new JButton("Check Error");
                                nextres.setBounds(xc,yc,200,40);
                               frame.getContentPane().add(nextres);
                               frame.pack();
                                data_arr_reciever=new int[no_frames+1];
                                data_received=new String[no_frames+1];
                               nextres.addActionListener(
                                    new java.awt.event.ActionListener() {
                                        
                                        public void actionPerformed(java.awt.event.ActionEvent e){
                                            System.out.println("Data collected from Receiver");
                                            for(int i=0;i<datareceive.length;i++){
                                                data_received[i]=datareceive[i].getText();
                                                System.out.println(" data_received [ "+ i +"] : " +data_received[i]);
                                            }
                                            System.out.println("Extracting receiver data into array");
                                            for(int i=0;i<no_frames;i++){
                                                data_arr_reciever[i]=Integer.parseInt(data_received[i]);
                                            }
                                            data_arr_reciever[no_frames]=csum;
                                            System.out.println("Printing Data receiver Array");
                                            for(int i=0;i<no_frames+1;i++){
                                                System.out.println("Frame : "+(i+1));
                                                System.out.println(data_arr_reciever[i]);
                                                
                                            }
                                            value=addAll(no_frames+1,no_bits,data_arr_reciever);

                                            System.out.println("return value"+value); 
                                            String strvalue=Integer.toString(value);
                                            System.out.println("return value string"+strvalue); 
                                            String final_value=strvalue.substring(0,no_bits);
                                            System.out.println("final value trimmed string"+final_value); 
                                            checksum_receiver=new int[no_bits];
                                            for(int i=0;i<no_bits;i++){
                                                checksum_receiver[i]=Character.getNumericValue(final_value.charAt(i));
                                            }
                                            boolean flag_checksum=false;
                                            for(int i=0;i<checksum_receiver.length;i++){
                                                
                                                if(checksum_receiver[i]==1){
                                                    continue;
                                                }
                                                else if(checksum_receiver[i]!=1){
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
        CheckSumGUI checksumgui=new CheckSumGUI();
        checksumgui.BuildGUI();
    }

}