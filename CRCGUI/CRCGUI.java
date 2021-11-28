import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;
public class CRCGUI {
    // global_n --> Power of Generating Polynomial
    int global_n=0;
    // coeff_inout --> Divisor Bits
    int coeff_input[];
    // input_data --> Data received from Sender
    String input_data="";
    // input_data --> Data received from Receiver
    String receiver_data="";
    // data_received --> Data received from Receiver [int array]
    int data_received[];
    // data_inp --> Data received from Sender [int array]
    int data_inp[];
    // data_manipulated --> Data with CRC bits added (Zero added)
    int data_manipulated[];
    // remainder --> To store CRC Bits (Remainder of Binary Division)
    int remainder[];
    // data_crc --> Data with CRC bits added (Remainder Added)
    int data_crc[];
    // check_err_rem --> To store Remainder bits that has to be checked for error (Remainder of Binary Division )
    int check_err_rem[];
    //frame
    JFrame frame;
    //Label for displaying prompt
    JLabel label_prompt;
    //Textbox for getting global_n
    JTextField enter_n;
    // next button
    JButton next_button;

    /*
    Binary Divison Method
    Returns - Remainder - int array
    Description : 
    
    */
    public int[] BinaryDivison(int divisor[],int data[],int rem[]){
        int c=0;
            while(true){
                for(int i=0;i<divisor.length;i++){
                    rem[c+i]=(rem[c+i]^divisor[i]);
                    
                }
                while(rem[c]==0 && c!=rem.length-1){
                    c++;
                    
                }

                if((rem.length-c)<divisor.length){
                   break;
                }

            }
        System.out.println("Remainder Calculated by Binary Division function  ");
        for(int i=0;i<rem.length;i++){
            System.out.print(rem[i]);
        }
        System.out.println();
        return rem;

    }

    /*
    BuildGUI Method
    Returns - Nothing
    Description :
    */
    public void BuildGUI(){
        frame = new JFrame("Error Detector -  CRC ");
        label_prompt=new JLabel("Enter the power of the generator polynomial equation");
        enter_n=new JTextField(10);
        next_button = new JButton("Next");
        label_prompt.setBounds(100,100,1500,80);
        enter_n.setBounds(100,180,200,40);
        next_button.setBounds(100,250,80,40);
        frame.getContentPane().add(label_prompt);
        frame.getContentPane().add(enter_n);
        frame.getContentPane().add(next_button); 
        next_button.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String input_n=enter_n.getText();
                    global_n=Integer.parseInt(input_n);
                    coeff_input=new int[global_n+1];
                    System.out.println("N : "+global_n);
                    frame.remove(label_prompt);
                    frame.remove(enter_n);                        
                    frame.remove(next_button);
                    enter_n_act_prfmd(evt,input_n,frame);
                }
            }
        ); 
        frame.getContentPane().setBackground(new Color(254,243,199));
        label_prompt.setFont(new Font("Verdana", Font.BOLD, 18));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(1300,800));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
    }

    /*
    DisplayPolynomialAndGetData Method
    Returns - Nothing
    Description :
    */     
    public void DisplayPolynomialAndGetData(){
        int retflag=0;
        int n=global_n;
        JTextField data=new JTextField(100);
        JLabel label_enter_data=new JLabel("Enter the Data");            
        JButton next_bt=new JButton("Next");
        JLabel disp_info=new JLabel("The Generating Polynomial given by the sender / Divisor is ");
            for(int i=0;i<coeff_input.length;i++){
                if(i==coeff_input.length-1){
                    String s=Integer.toString(coeff_input[i]);
                    disp_info.setText(disp_info.getText()+ s);
                }
                else{
                    String s=Integer.toString(coeff_input[i]);
                    disp_info.setText(disp_info.getText()+s + "x ^ "+ n+" + ");
                    n--;
                }
            }
           
        next_bt.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    input_data=data.getText();
                    System.out.println("Data Entered by sender is "+ input_data);
                    frame.remove(disp_info);
                    JLabel data_info=new JLabel("");
                    JLabel crc_info=new JLabel("Data that has to be transmitted to receiver ");
                    data_info.setText("Data is "+input_data);
                    data_inp=new int [input_data.length()];
                    data_manipulated=new int[data_inp.length+coeff_input.length-1];
                    remainder=new int[data_manipulated.length];
                    System.out.println("Data - after adding zero bits to data entered by sender");
                        for(int i=0;i<data_inp.length;i++){
                            int bit=Character.getNumericValue(input_data.charAt(i));
                            data_inp[i]=bit;
                            data_manipulated[i]=bit;
                            remainder[i]=data_manipulated[i];
                            //System.out.println(data_inp[i]+"data inp [ "+i+" ]");
                            //System.out.println(data_manipulated[i]+"data maninpulated [ "+i+" ]");
                            System.out.println("rem [ "+i+" ] "+remainder[i]);
                 
                        }

                        for(int i=data_inp.length;i<data_inp.length+coeff_input.length-1;i++){
                            data_manipulated[i]=0;
                            remainder[i]=data_manipulated[i];
                            //System.out.println(data_manipulated[i]+"data maninpulated [ "+i+" ]");
                            System.out.println("rem [ "+i+" ] "+remainder[i]);

                        }
                    remainder=BinaryDivison(coeff_input,data_inp,remainder);
                    data_crc=new int[data_manipulated.length];

                        for(int i=0;i<data_crc.length;i++){
                            data_crc[i]=data_manipulated[i]^remainder[i];
                        }
                        System.out.println("Data - after adding crc bits to data entered by sender");
                        for(int i=0;i<data_crc.length;i++){
                            System.out.println("data_crc ["+i+"] "+data_crc[i]);
                            String s=Integer.toString(data_crc[i]);
                            crc_info.setText(crc_info.getText()+s);
                           
                        }
                    data_info.setBounds(100,120,2000,100);
                    crc_info.setBounds(100,160,2000,100);
                    frame.getContentPane().remove(label_enter_data);            
                    frame.getContentPane().remove(data);
                    frame.getContentPane().remove(next_bt);
                    data_info.setFont(new Font("Verdana", Font.BOLD, 18));
                    crc_info.setFont(new Font("Verdana", Font.BOLD, 18));
                    frame.getContentPane().add(data_info);
                    frame.getContentPane().add(crc_info);
                    frame.pack();

                    JLabel receiver_data_get=new JLabel("Enter the data that has been received by the receiver at the receiver end");
                    JTextField receiver_data_inp=new JTextField(100);
                    JButton checkerror_bt=new JButton("Check Error");
                    receiver_data_get.setBounds(100,200,1200,100);
                    receiver_data_inp.setBounds(100,280,200,40);
                    checkerror_bt.setBounds(100,350,150,40);
                    receiver_data_get.setFont(new Font("Verdana", Font.BOLD, 18));
                    frame.getContentPane().add(receiver_data_get);
                    frame.getContentPane().add(receiver_data_inp);
                    frame.getContentPane().add(checkerror_bt);
                    checkerror_bt.addActionListener( 
                        new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt){
                                receiver_data=receiver_data_inp.getText();
                                System.out.println("Receiver data textbox "+receiver_data);
                                System.out.println("Receiver data len "+receiver_data.length());
                                System.out.println("datamanip data len "+data_manipulated.length);
                               
                                    if(receiver_data.length()!=data_manipulated.length){
                                        String warnstr="";
                                        int bal=data_manipulated.length-receiver_data.length();
                                        if(bal<0){
                                            bal=Math.abs(bal);
                                            warnstr="more";
                                        }
                                        else{
                                            warnstr="missing";
                                        }
                                        JOptionPane.showMessageDialog(frame, "Enter Receiver Bits Properly !  "+bal +" bits are "+warnstr,"Error", JOptionPane.ERROR_MESSAGE);

                                        
                                    }
                                    else{
                                        data_received=new int[receiver_data.length()];
                                        System.out.println("Data entered by the receiver ");
                                        for(int i=0;i<data_received.length;i++){
                                            int bit=Character.getNumericValue(receiver_data.charAt(i));
                                            data_received[i]=bit;
                                            System.out.println("data_received [ "+i+" ] "+data_received[i]);
                                            
                                        }
                                        check_err_rem=new int[data_received.length];
                                        for(int i=0;i<check_err_rem.length;i++){
                                            check_err_rem[i]=data_received[i];
                                        }
                                        System.out.println("Checking Error ");
                                        check_err_rem=BinaryDivison(coeff_input,data_received,check_err_rem);
                                        JLabel check_result=new JLabel("Result is ");
                                        boolean detection_flag=true;
                                            for(int i=0;i<check_err_rem.length;i++){
                                                if(check_err_rem[i]==0){
                                                    continue;
                                                }
                                                if(check_err_rem[i]!=0){
                                                    detection_flag=false;
                                                    break;
                                                }
                                            }
                                            if(detection_flag==true){
                                                check_result.setText(check_result.getText()+" NO ERROR");
                                                System.out.println("No Error is detected");
                                            }
                                            else{
                                                check_result.setText(check_result.getText()+" ERROR DETECTED");
                                                System.out.println("Error is detected");
                                            }
                                        
                                        
                                        JLabel received_info=new JLabel("Data received entered by Receiver is "+receiver_data);
                                        check_result.setBounds(100,260,1500,80);
                                        received_info.setBounds(100,220,2000,80);
                                        received_info.setFont(new Font("Verdana", Font.BOLD,18));
                                        check_result.setFont(new Font("Verdana", Font.BOLD, 18));
                                        check_result.setForeground(new Color(0,0,255));
                                        frame.getContentPane().remove(receiver_data_get);
                                        frame.getContentPane().remove(receiver_data_inp);
                                        frame.getContentPane().remove(checkerror_bt);
                                        frame.getContentPane().add(received_info);
                                        frame.getContentPane().add(check_result);
                                        frame.pack();
                                    }

                            }
                        }
                    );

                }
                    
            }
        );
        disp_info.setBounds(100,200,1000,80);
        label_enter_data.setBounds(100,260,1000,80);
        data.setBounds(100,320,200,40);
        next_bt.setBounds(100,400,80,40);
        label_enter_data.setFont(new Font("Verdana", Font.BOLD, 18));
        disp_info.setFont(new Font("Verdana", Font.BOLD, 18));            
        frame.getContentPane().add(disp_info);
        frame.getContentPane().add(label_enter_data);
        frame.getContentPane().add(data);
        frame.getContentPane().add(next_bt);            
        frame.pack();
        System.out.println("Update : "+disp_info.getText());
          

    }

    /*
    enter_n_act_prfmd Method
    Returns Nothing
    Description :
    */   
    public void enter_n_act_prfmd(java.awt.event.ActionEvent evt,String input_n,JFrame f){
        int num_inp=Integer.parseInt(input_n);
        int no=num_inp;
        int number_backup=no;            
        String form="X";
        boolean flag=true;
        JTextField textfieldarr[]=new JTextField[no+1];
        JLabel labelarr[]=new JLabel[no+1];
        int xc=100; int yc=60;
            for(int i=0;i<=number_backup;i++){
                String x=form+" ^ "+no +" : ";
                JLabel l=new JLabel(x);
                JTextField coeff_tf=new JTextField(10);
                textfieldarr[i]=coeff_tf;
                labelarr[i]=l;
                l.setBounds(xc,yc,200,40);
                coeff_tf.setBounds(xc+100,yc,100,40);
                l.setFont(new Font("Verdana", Font.BOLD, 18));
                f.getContentPane().add(l);
                f.getContentPane().add(coeff_tf);
                f.pack();
                no--;
                yc+=100;
                if(yc>700){
                    yc=60;
                    xc+=250;
                }
            }
        JButton bt=new JButton("Next");
        bt.setBounds(xc,yc,80,40);
        f.getContentPane().add(bt);    
        f.pack();
        bt.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    for(int i=0;i<textfieldarr.length;i++){
                        String input_n=textfieldarr[i].getText();
                        System.out.println("Divisor bit "+i +" : "+input_n);
                        coeff_input[i]=Integer.parseInt(input_n);  
                    }
                    DisplayPolynomialAndGetData();
                    for(int i=0;i<textfieldarr.length;i++){
                        f.remove(textfieldarr[i]);
                        f.remove(labelarr[i]);
                    }
                    f.remove(bt);       
                }
            }
        ); 
               
    }
            
                   
            
    /*
    Main Function
    Returns Nothing
    */
    public static void main(String args[]){
      CRCGUI gui=new CRCGUI();
      gui.BuildGUI();
    }

}