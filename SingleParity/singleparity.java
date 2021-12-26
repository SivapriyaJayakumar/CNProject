import java.util.*;
import javax.swing.*;
import java.awt.*;    

public class singleparity{
    // frame 
    JFrame frame;
    // n --> no of bits
    int n;
    // paritybit_sender --> Parity bit calculated at sender side
    int paritybit_sender;
    // data_send_str --> to store data sent by sender
    String data_send_str="";
    // choice --> for odd/ even parity
    int choice;
    //data_sender_arr --> To store data sent by sender --> int arr
    int data_sender_arr[];
    // data_receive_str --> to store data sent by receiver
    String data_receive_str="";
    //data_receiver_arr --> To store data sent by receiver --> int arr
    int data_receiver_arr[];

    /*
    calcEvenParity method
    Returns - parity bit --> int
    Description : 
        calculate parity bit using even parity logic
        if number of ones in data unit is even --> parity bit is 0. Otherwise parity bit is 1.
    */
    public int calcEvenParity(int data[]){
        //retval --> value to be returned
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
    BuildGUI method
    Renders - GUI
    Description : Renders Elements of GUI
    */
    public void BuildGUI(){
        //frame --> Setting title
        frame=new JFrame("Error Detector - Single Parity");
        //nprompt --> Setting label
        JLabel nprompt=new JLabel(" Enter no of Data Bits");
        //ntextbox --> Setting textfield
        JTextField ntextbox=new JTextField(40);
        //next --> Setting button
        JButton next=new JButton("Next");
        //after entering n
        next.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e){
                    //removing previously added elements
                    frame.getContentPane().remove(nprompt);
                    frame.getContentPane().remove(ntextbox);
                    frame.getContentPane().remove(next);
                    frame.pack();
                    // storing n entered by user 
                    n=Integer.parseInt(ntextbox.getText());
                    System.out.println("N :  "+n);
                    // enterdataprompt --> setting label
                    JLabel enterdataprompt=new JLabel("Enter Data");
                    // data_send --> setting textfield
                    JTextField data_send=new JTextField(40);
                    // nextbt_data --> setting button
                    JButton nextbt_data=new JButton("Next");
                    //to disable textfield once sender has typed n bits
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
                    // after entering data
                    nextbt_data.addActionListener(
                        new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e){
                                //removing previously added elements
                                frame.getContentPane().remove(enterdataprompt);
                                frame.getContentPane().remove(data_send);
                                frame.getContentPane().remove(nextbt_data);
                                frame.pack();
                                // displaydata --> setting label to display data
                                JLabel displaydata=new JLabel("Data from sender : ");
                                // data_send_str --> getting the data
                                data_send_str=data_send.getText();
                                //data_sender_arr --> extracting the data into arr
                                data_sender_arr=new int[n+1];
                                //Printing the data entered by sender
                                System.out.println("Data from Sender ");
                                for(int i=0;i<n;i++){
                                    data_sender_arr[i]=Character.getNumericValue(data_send_str.charAt(i));
                                    System.out.print(data_sender_arr[i]+" ");
                                    displaydata.setText(displaydata.getText()+data_sender_arr[i]+" ");
                                }
                                System.out.println();
                                //parity bit initially 0.
                                data_sender_arr[n]=0;
                                // r1,r2 --> radiobutton to choose parity
                                JRadioButton r1=new JRadioButton("EVEN PARITY");    
                                JRadioButton r2=new JRadioButton("ODD PARITY");  
                                //buttongroup  
                                ButtonGroup bg=new ButtonGroup();   
                                //calcparity --> after entering data to calculate parity
                                JButton calcparity=new JButton("Calculate Parity");
                                //after entering data
                                calcparity.addActionListener(
                                    new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent e){
                                            //displaychosenparity --> setting label to display chosen parity
                                            JLabel displaychosenparity=new JLabel("Chosen parity is ");
                                            // removing previously added elements
                                            frame.getContentPane().remove(r1);
                                            frame.getContentPane().remove(r2);
                                            frame.getContentPane().remove(calcparity);
                                            frame.pack();
                                            // if r1 is selected --> even parity is selected
                                            if(r1.isSelected()){
                                                choice=1;
                                                System.out.println("Chosen EVEN PARITY ");
                                                //calling calcEvenParity method to calculate paritybit
                                                paritybit_sender=calcEvenParity(data_sender_arr);
                                                System.out.println("Parity Bit Calculated at Sender side "+paritybit_sender);
                                                //storing calculated parity bit to data arr
                                                data_sender_arr[n]=paritybit_sender;
                                                displaychosenparity.setText(displaychosenparity.getText()+"  EVEN PARITY");
                                            }
                                            // if r2 is selected --> odd parity is selected
                                            else if(r2.isSelected()){
                                                choice=2;
                                                System.out.println("Chosen ODD PARITY ");
                                                //calling calcOddParity method to calculate paritybit
                                                paritybit_sender=calcOddParity(data_sender_arr);
                                                System.out.println("Parity Bit Calculated at Sender side "+paritybit_sender);
                                                //storing calculated parity bit to data arr
                                                data_sender_arr[n]=paritybit_sender;
                                                displaychosenparity.setText(displaychosenparity.getText()+"  ODD PARITY");
                                                
                                            }
                                            //datatobesent --> setting label
                                            JLabel datatobesent=new JLabel("Data to be transmitted to receiver ");
                                            System.out.println("Data to be transmitted to receiver ");
                                            // printing data to be transmitted to receiver
                                            for(int i=0;i<n+1;i++){
                                                System.out.print(data_sender_arr[i]+" ");
                                                datatobesent.setText(datatobesent.getText()+data_sender_arr[i]+" ");
                                            }
                                            System.out.println();
                                            // data_rec_label --> enter data received
                                            JLabel data_rec_label=new JLabel("Enter data received");
                                            // data_receive --> setting textfield
                                            JTextField data_receive=new JTextField(40);
                                            // checkerr --> button to proceed after data entered by receiver
                                            JButton checkerr=new JButton("Check Error");
                                            // adding elements to frame
                                            displaychosenparity.setMaximumSize(new Dimension(2000,40));
                                            displaychosenparity.setFont(new Font("Verdana", Font.BOLD, 18));
                                            datatobesent.setMaximumSize(new Dimension(2000,40));
                                            datatobesent.setFont(new Font("Verdana", Font.BOLD, 18));
                                            data_rec_label.setMaximumSize(new Dimension(2000,40));
                                            data_rec_label.setFont(new Font("Verdana", Font.BOLD, 18));
                                            data_receive.setMaximumSize(new Dimension(800,40));
                                            checkerr.setMaximumSize(new Dimension(150,40));
                                            checkerr.setFont(new Font("Verdana", Font.BOLD, 18));
                                            frame.getContentPane().add(displaychosenparity);
                                            frame.getContentPane().add(datatobesent);
                                            frame.getContentPane().add(data_rec_label);
                                            frame.getContentPane().add(data_receive);
                                            frame.getContentPane().add(checkerr);
                                            frame.pack();
                                            //after entering data received by receiver
                                            checkerr.addActionListener(
                                                new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent e){
                                                        //datafromrec --> setting label
                                                        JLabel datafromrec=new JLabel("Data from Receiver ");
                                                        //result --> setting label
                                                        JLabel result =new JLabel("Result is");
                                                        //data_receive_str --> getting data from receiver 
                                                        data_receive_str=data_receive.getText();
                                                        //data_receiver_arr --> extracting data from receiverstr to array
                                                        data_receiver_arr=new int[n+1];
                                                        //Printing data from receiver
                                                        System.out.println("Data from Receiver ");
                                                        for(int i=0;i<n+1;i++){
                                                            data_receiver_arr[i]=Character.getNumericValue(data_receive_str.charAt(i));
                                                            System.out.print(data_receiver_arr[i]+" ");
                                                            datafromrec.setText(datafromrec.getText()+data_receiver_arr[i]+" ");
                                                        }
                                                        System.out.println();
                                                        // getting parity choice 
                                                        switch(choice){
                                                            // if even parity is chosen , 1st case will be executed.
                                                            case 1:
                                                            int parityeven=calcEvenParity(data_receiver_arr);
                                                            System.out.println("Parity calc at receiver "+parityeven);
                                                            if(parityeven==0){
                                                                System.out.println("NO ERROR");
                                                                result.setText(result.getText()+"NO ERROR");
                                                                result.setForeground(new Color(22, 163, 74));
                                                            }
                                                            else if(parityeven==1){
                                                                System.out.println("ERROR");
                                                                result.setText(result.getText()+" ERROR"); 
                                                                result.setForeground(new Color(255,0,0));
                                                            }
                                                            
                                                            break;
                                                            // if odd parity is chosen , 2nd case will be executed.
                                                            case 2:
                                                            int parityodd=calcOddParity(data_receiver_arr);
                                                             System.out.println("Parity calc at receiver "+parityodd);
                                                            if(parityodd==0){
                                                                System.out.println("NO ERROR");
                                                                result.setText(result.getText()+" NO ERROR");
                                                                result.setForeground(new Color(22, 163, 74));
                                                            }
                                                            else if(parityodd==1){
                                                                System.out.println("ERROR");
                                                                result.setText(result.getText()+" ERROR");
                                                                result.setForeground(new Color(255,0,0));
                                                            }
                                                            break;
                                                        }
                                                        //adding GUI elements
                                                        datafromrec.setMaximumSize(new Dimension(2000,40));
                                                        datafromrec.setFont(new Font("Verdana", Font.BOLD, 18));
                                                        result.setMaximumSize(new Dimension(2000,40));
                                                        result.setFont(new Font("Verdana", Font.BOLD, 18));
                                                        frame.getContentPane().add(datafromrec);
                                                        frame.getContentPane().add(result);
                                                        frame.pack();

                                                    }
                                                }
                                            );
                                        }
                                    }
                                );
                                //adding GUI elements
                                JLabel displayparprompt=new JLabel("CHOOSE PARITY");
                                bg.add(r1);bg.add(r2); 
                                displaydata.setMaximumSize(new Dimension(2000,40));
                                displayparprompt.setMaximumSize(new Dimension(2000,40));
                                displayparprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                                displaydata.setFont(new Font("Verdana", Font.BOLD, 18));
                                r1.setFont(new Font("Verdana", Font.BOLD, 18));
                                r2.setFont(new Font("Verdana", Font.BOLD, 18));
                                calcparity.setFont(new Font("Verdana", Font.BOLD, 18));
                                calcparity.setMaximumSize(new Dimension(250,40));
                                frame.getContentPane().add(displaydata);   
                                frame.getContentPane().add(displayparprompt);
                                frame.getContentPane().add(r1);frame.getContentPane().add(r2);
                                frame.getContentPane().add(calcparity);frame.pack();
                            }
                        }

                    );
                    //adding GUI elements
                    enterdataprompt.setMaximumSize(new Dimension(2000,40));
                    enterdataprompt.setFont(new Font("Verdana", Font.BOLD, 18));
                    data_send.setMaximumSize(new Dimension(800,40));
                    nextbt_data.setMaximumSize(new Dimension(150,40));
                    nextbt_data.setFont(new Font("Verdana", Font.BOLD, 18));
                    frame.getContentPane().add(enterdataprompt);
                    frame.getContentPane().add(data_send);
                    frame.getContentPane().add(nextbt_data);
                    frame.pack();
                }
            }
        );
        //adding GUI elements
        nprompt.setMaximumSize(new Dimension(2000,40));
        nprompt.setFont(new Font("Verdana", Font.BOLD, 18));
        ntextbox.setMaximumSize(new Dimension(800,40));
        next.setMaximumSize(new Dimension(150,40));
        next.setFont(new Font("Verdana", Font.BOLD, 18));
        frame.getContentPane().add(nprompt);
        frame.getContentPane().add(ntextbox);
        frame.getContentPane().add(next);
        frame.pack();
        //setting frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(254,243,199));
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(600,600));
        frame.setLocationRelativeTo(null);
        BoxLayout layout=new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(layout);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String args[]){
    //creating object for class and calling BuildGUI
    singleparity obj=new singleparity();
    obj.BuildGUI();
    

    }

}
