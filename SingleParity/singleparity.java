import java.util.*;
import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;
public class singleparity{
    JFrame frame;
    int n;
    int paritybit_sender;
    String data_send_str="";
    int choice;
    int data_sender_arr[];
     String data_receive_str="";
    int data_receiver_arr[];
    public int calcEvenParity(int data[]){
        int retval=0;
        for(int i=0;i<data.length;i++){
            if(data[i]==1){
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
    public int calcOddParity(int data[]){
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
    public void BuildGUI(){
        frame=new JFrame("Error Detector - Single Parity");
        JLabel nprompt=new JLabel("Enter no of Data Bits");
        JTextField ntextbox=new JTextField(40);
        JButton next=new JButton("Next");
        next.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e){
                    frame.getContentPane().remove(nprompt);
                    frame.getContentPane().remove(ntextbox);
                    frame.getContentPane().remove(next);
                    frame.pack();
                    n=Integer.parseInt(ntextbox.getText());
                    System.out.println("N :  "+n);
                    JLabel enterdataprompt=new JLabel("Enter Data");
                    JTextField data_send=new JTextField(40);
                    JButton nextbt_data=new JButton("Next");
                    data_send.getDocument().addDocumentListener(
                        new javax.swing.event.DocumentListener(){
                            public void changedUpdate(javax.swing.event.DocumentEvent e){}
                            public void insertUpdate(javax.swing.event.DocumentEvent e){
                                if(data_send.getText().length()==n){
                                    System.out.println("Disabled");
                                    data_send.setEditable(false);
                                }                                
                            }
                            public void removeUpdate(javax.swing.event.DocumentEvent e){}
                        }                        
                    ); 
                    nextbt_data.addActionListener(
                        new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e){
                                frame.getContentPane().remove(enterdataprompt);
                                frame.getContentPane().remove(data_send);
                                frame.getContentPane().remove(nextbt_data);
                                JLabel displaydata=new JLabel("Data from sender : ");
                                data_send_str=data_send.getText();
                                data_sender_arr=new int[n+1];
                                System.out.println("Data from Sender ");
                                for(int i=0;i<n;i++){
                                    data_sender_arr[i]=Character.getNumericValue(data_send_str.charAt(i));
                                    System.out.print(data_sender_arr[i]+" ");
                                    displaydata.setText(displaydata.getText()+data_sender_arr[i]+" ");
                                }
                                System.out.println();
                                data_sender_arr[n]=0;
                                JRadioButton r1=new JRadioButton("EVEN PARITY");    
                                JRadioButton r2=new JRadioButton("ODD PARITY");    
                                ButtonGroup bg=new ButtonGroup();   
                                JButton calcparity=new JButton("Calculate Parity");
                                calcparity.addActionListener(
                                    new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent e){
                                            JLabel displaychosenparity=new JLabel("Chosen parity is ");
                                            frame.getContentPane().remove(r1);
                                            frame.getContentPane().remove(r2);
                                            frame.getContentPane().remove(calcparity);
                                            frame.pack();
                                            if(r1.isSelected()){
                                                choice=1;
                                                System.out.println("Chosen EVEN PARITY ");
                                                paritybit_sender=calcEvenParity(data_sender_arr);
                                                System.out.println("Parity Bit Calculated at Sender side "+paritybit_sender);
                                                data_sender_arr[n]=paritybit_sender;
                                                displaychosenparity.setText(displaychosenparity.getText()+"  EVEN PARITY");
                                            }
                                            else if(r2.isSelected()){
                                                choice=2;
                                                System.out.println("Chosen ODD PARITY ");
                                                paritybit_sender=calcOddParity(data_sender_arr);
                                                System.out.println("Parity Bit Calculated at Sender side "+paritybit_sender);
                                                data_sender_arr[n]=paritybit_sender;
                                                displaychosenparity.setText(displaychosenparity.getText()+"  ODD PARITY");
                                                
                                            }
                                            JLabel datatobesent=new JLabel("Data to be transmitted to receiver ");
                                            System.out.println("Data to be transmitted to receiver ");
                                            for(int i=0;i<n+1;i++){
                                                System.out.print(data_sender_arr[i]+" ");
                                                datatobesent.setText(datatobesent.getText()+data_sender_arr[i]+" ");
                                            }
                                            System.out.println();
                                            JLabel data_rec_label=new JLabel("Enter data received");
                                            JTextField data_receive=new JTextField(40);
                                            JButton checkerr=new JButton("Check Error");
                                            frame.getContentPane().add(displaychosenparity);
                                            frame.getContentPane().add(datatobesent);
                                            frame.getContentPane().add(data_rec_label);
                                            frame.getContentPane().add(data_receive);
                                            frame.getContentPane().add(checkerr);
                                            frame.pack();
                                            checkerr.addActionListener(
                                                new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent e){
                                                        JLabel datafromrec=new JLabel("Data from Receiver ");
                                                        JLabel result =new JLabel("Result is");
                                                        data_receive_str=data_receive.getText();
                                                        data_receiver_arr=new int[n+1];
                                                        System.out.println("Data from Receiver ");
                                                        for(int i=0;i<n+1;i++){
                                                            data_receiver_arr[i]=Character.getNumericValue(data_receive_str.charAt(i));
                                                            System.out.print(data_receiver_arr[i]+" ");
                                                            datafromrec.setText(datafromrec.getText()+data_receiver_arr[i]+" ");
                                                        }
                                                        System.out.println();
                                                        switch(choice){
                                                            case 1:
                                                            int parityeven=calcEvenParity(data_receiver_arr);
                                                            if(parityeven==0){
                                                                System.out.println("NO ERROR");
                                                                result.setText(result.getText()+"NO ERROR");
                                                            }
                                                            else if(parityeven==1){
                                                                System.out.println("ERROR");
                                                                result.setText(result.getText()+" ERROR");
                                                            }
                                                            break;

                                                            case 2:
                                                            int parityodd=calcOddParity(data_receiver_arr);
                                                            if(parityodd==0){
                                                                System.out.println("NO ERROR");
                                                                result.setText(result.getText()+"NO ERROR");
                                                            }
                                                            else if(parityodd==1){
                                                                System.out.println("ERROR");
                                                                result.setText(result.getText()+" ERROR");
                                                            }
                                                            break;
                                                        }
                                                        frame.getContentPane().add(datafromrec);
                                                        frame.getContentPane().add(result);
                                                        frame.pack();

                                                    }
                                                }
                                            );
                                        }
                                    }
                                );
                                bg.add(r1);bg.add(r2); 
                                frame.add(displaydata);   
                                frame.add(r1);frame.add(r2);frame.add(calcparity);frame.pack();
                            }
                        }

                    );
                    
                    frame.getContentPane().add(enterdataprompt);
                    frame.getContentPane().add(data_send);
                    frame.getContentPane().add(nextbt_data);
                    frame.pack();
                }
            }
        );
        frame.getContentPane().add(nprompt);
        frame.getContentPane().add(ntextbox);
        frame.getContentPane().add(next);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(254,243,199));
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(600,600));
        frame.setLocationRelativeTo(null);
        GridLayout gridLayout=new GridLayout(12,0,10,10);
        frame.setLayout(gridLayout);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String args[]){
    singleparity obj=new singleparity();
    obj.BuildGUI();
    

    }

}
