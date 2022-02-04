import java.util.*;
import javax.swing.*;
import java.awt.*;    
import java.awt.Color;

public class twodparity{
    // frame 
    JFrame frame;
    // n --> no of bits
    int n;
    // k --> no of frame
    int k;
    // data_send_str --> to store data sent by sender
    String data_send_str[];
    // data_receive_str --> to store data received by receiver
    String data_receive_str[];
    // choice --> for odd/ even parity
    int choice;
    // sender  --> Extracting data sent by sender and storing it to an int arr
    int sender[][];
    // receiver  --> Extracting data received by receiver and storing it to an int arr
    int receiver[][];
    // rowparity  --> to store row wise parities at sender side
    int rowparity[];
    // colparity  --> to store column wise parities at sender side
    int colparity[];
    // rparity  --> to store column parity of row parities
    int rparity=-1;
    // rowparity_receiver  --> to store row wise parities at receiver side
    int rowparity_receiver[];
    // colparity_receiver  --> to store column wise parities at receiver side
    int colparity_receiver[];

    /*calcEvenParity method
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
         System.out.println(retval+"is retval");
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
        System.out.println(retval+"is retval");
        return retval;
    }

    /*
    calcRowEvenParity method
    Returns - parity bit for each row of data unit --> int[]
    Description : 
        calculate parity bit using even parity logic
        if number of ones in data unit is even --> parity bit is 0. Otherwise parity bit is 1.
    */
    public int[] calcRowEvenParity(int data[][],int n,int k){
        //retval --> value to be returned
        int res[]=new int[k];
        for(int i=0;i<k;i++){
            int cnt=0;
            for(int j=0;j<n;j++){
                if(data[i][j]==1){
                    cnt++;
                }
            }
            if(cnt%2==0){
                res[i]=0;
            }
            else{
                res[i]=1;
            }
        }
        System.out.println("ROW PARITY CALCULATED : ");
        for(int i=0;i<k;i++){
            System.out.print(res[i]);
        }
        System.out.println();

        return res;
    }
    /*
    calcColEvenParity method
    Returns - parity bit for each column of data unit --> int[]
    Description : 
        calculate parity bit using even parity logic
        if number of ones in data unit is even --> parity bit is 0. Otherwise parity bit is 1.
    */
    public int[] calcColEvenParity(int data[][],int n,int k){
        //retval --> value to be returned
        int res[]=new int[n];
        int colind=0;
        while(colind<n){
            int cnt=0;
            for(int i=0;i<k;i++){
                if(data[i][colind]==1){
                    cnt++;
                }
            }
            if(cnt%2==0){
                res[colind]=0;
            }
            else{
                res[colind]=1;
            }
            colind++;
        }
        System.out.println("COLUMN PARITY CALCULATED : ");
        for(int i=0;i<n;i++){
            System.out.print(res[i]);
        }
        System.out.println();

        return res;
    }
    /*
    calcRowOddParity method
    Returns - parity bit for each row of data unit --> int[]
    Description : 
        calculate parity bit using odd parity logic
        if number of ones in data unit is even --> parity bit is 1. Otherwise parity bit is 0.
    */
   public int[] calcRowOddParity(int data[][],int n,int k){
        //retval --> value to be returned
        int res[]=new int[k];
        for(int i=0;i<k;i++){
            int cnt=0;
            for(int j=0;j<n;j++){
                if(data[i][j]==1){
                    cnt++;
                }
            }
            if(cnt%2==0){
                res[i]=1;
            }
            else{
                res[i]=0;
            }
        }
        System.out.println("ROW PARITY CALCULATED : ");
        for(int i=0;i<k;i++){
            System.out.print(res[i]);
        }
        System.out.println();

        return res;
    }
    /*
    calcColOddParity method
    Returns - parity bit for each column of data unit --> int[]
    Description : 
        calculate parity bit using even parity logic
        if number of ones in data unit is even --> parity bit is 1. Otherwise parity bit is 0.
    */
    public int[] calcColOddParity(int data[][],int n,int k){
        //retval --> value to be returned
        int res[]=new int[n];
        int colind=0;
        while(colind<n){
            int cnt=0;
            for(int i=0;i<k;i++){
                if(data[i][colind]==1){
                    cnt++;
                }
            }
            if(cnt%2==0){
                res[colind]=1;
            }
            else{
                res[colind]=0;
            }
            colind++;
        }
        System.out.println("COLUMN PARITY CALCULATED : ");
        for(int i=0;i<n;i++){
            System.out.print(res[i]);
        }
        System.out.println();

        return res;
    }

    /*
    BuildGUI method
    Renders - GUI
    Description : Renders Elements of GUI
    */
    
    public void BuildGUI(){
        JLabel divider=new JLabel(" | ");
        
        //frame --> Setting title
        frame=new JFrame("Error Detector - Two Dimensional Parity");
        //kprompt --> Setting label
        JLabel kprompt=new JLabel("Enter no of frames ( K ) ");
        //nprompt --> Setting label
        JLabel nprompt=new JLabel("Enter no of Data Bits");
        //ntextbox --> Setting textfield
        JTextField ntextbox=new JTextField(40);
        //ktextbox --> Setting textfield
        JTextField ktextbox=new JTextField(40);
        //next --> Setting button
        JButton next=new JButton("Next");
        //after entering n and k
        next.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e){
                    //removing previously added elements
                    frame.getContentPane().remove(nprompt);
                    frame.getContentPane().remove(kprompt);
                    frame.getContentPane().remove(ntextbox);
                    frame.getContentPane().remove(ktextbox);
                    frame.getContentPane().remove(next);
                    frame.pack();
                    // storing n entered by user 
                    n=Integer.parseInt(ntextbox.getText());
                    k=Integer.parseInt(ktextbox.getText());
                    System.out.println("N :  "+n);
                    System.out.println("K :  "+k);
                    // enterdataprompt --> setting label
                    JLabel enterdataprompt=new JLabel("Enter Data");
                    JTextField dataframerefsend[]=new JTextField[k];
                    for( int i=0;i<k;i++){
                        // data_send --> setting textfield
                        JTextField data_send=new JTextField(40);
                        dataframerefsend[i]=data_send;
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
                    }
                    // nextbt_data --> setting button
                    JButton nextbt_data=new JButton("Next");
                    JPanel datasendpanel=new JPanel();
                    JPanel op=new JPanel();
                    JScrollPane scrollablearea=new JScrollPane(datasendpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    //to disable textfield once sender has typed n bits
                    
                    // after entering data
                    nextbt_data.addActionListener(
                        new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e){
                                //removing previously added elements
                                frame.getContentPane().remove(enterdataprompt);
                                frame.getContentPane().remove(op);
                                frame.pack();
                                
                                frame.getContentPane().remove(nextbt_data);
                                frame.pack();
                                // displaydata --> setting label to display data
                                JLabel displaydata=new JLabel("Data from sender : ");
                                data_send_str=new String[k];
                                sender=new int[k+1][n+1];
                                // data_send_str --> getting the data
                                for(int i=0;i<k;i++){
                                data_send_str[i]=dataframerefsend[i].getText();
                                }
                                //sender --> extracting the data into arr
                                System.out.println("Data from Sender ");
                                
                                for(int i=0;i<k;i++){
                                    System.out.println(" Extracting Data for frame"+(i+1));
                                    for(int j=0;j<n;j++){
                                        sender[i][j]=Character.getNumericValue(data_send_str[i].charAt(j));
                                        System.out.print(sender[i][j]+" ");
                                        displaydata.setText(displaydata.getText()+sender[i][j]+" ");
                                    }
                                    System.out.println();
                                    displaydata.setText(displaydata.getText()+divider.getText());
                                }
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
                                            rowparity=new int[k];
                                            colparity=new int[n];
                                            // if r1 is selected --> even parity is selected
                                            // calculating row parities, colparities, rparity
                                            if(r1.isSelected()){
                                                choice=1;
                                                System.out.println("Chosen EVEN PARITY ");
                                                //calling calcEvenParity method to calculate paritybit
                                                rowparity=calcRowEvenParity(sender,n,k);
                                                colparity=calcColEvenParity(sender,n,k);
                                                rparity=calcEvenParity(rowparity);
                                                displaychosenparity.setText(displaychosenparity.getText()+"  EVEN PARITY");
                                            }
                                            // if r2 is selected --> odd parity is selected
                                            // calculating row parities, colparities, rparity
                                            else if(r2.isSelected()){
                                                choice=2;
                                                System.out.println("Chosen ODD PARITY ");
                                                rowparity=calcRowOddParity(sender,n,k);
                                                colparity=calcColOddParity(sender,n,k);
                                                rparity=calcOddParity(rowparity);
                                                displaychosenparity.setText(displaychosenparity.getText()+"  ODD PARITY");
                                                
                                            }
                                            int ind;
                                            for(ind=0;ind<k;ind++){
                                                sender[ind][n]=rowparity[ind];
                                            }
                                            
                                            ind=0;
                                            for(ind=0;ind<n;ind++){
                                                sender[k][ind]=colparity[ind];
                                            }
                                            sender[k][ind]=rparity;
                                            //datatobesent --> setting label
                                            JLabel datatobesent=new JLabel("Data to be transmitted to receiver ");
                                            System.out.println("Data from Sender to be transmitted");
                                            
                                            for(int i=0;i<k+1;i++){
                                                System.out.println((i+1)+"Data of frame ");
                                                for(int j=0;j<n+1;j++){
                                                    System.out.print(sender[i][j]+" ");
                                                    datatobesent.setText(datatobesent.getText()+sender[i][j]+" ");
                                                }
                                                System.out.println();
                                                datatobesent.setText(datatobesent.getText()+divider.getText());
                                            }
                                            
                                            // data_rec_label --> enter data received
                                            JLabel data_rec_label=new JLabel("Enter data received");
                                            JTextField dataframerefreceive[]=new JTextField[k+1];
                                            for( int i=0;i<k+1;i++){
                                                // data_send --> setting textfield
                                                JTextField data_receive=new JTextField(40);
                                                dataframerefreceive[i]=data_receive;
                                                data_receive.getDocument().addDocumentListener(
                                                new javax.swing.event.DocumentListener(){
                                                    public void changedUpdate(javax.swing.event.DocumentEvent e){}
                                                    public void insertUpdate(javax.swing.event.DocumentEvent e){
                                                        if(data_receive.getText().length()==n+1){
                                                            System.out.println("Disabled");
                                                            data_receive.setEditable(false);
                                                        }                                
                                                    }
                                                    public void removeUpdate(javax.swing.event.DocumentEvent e){}
                                                }                        
                                                ); 
                                            }
                                            // checkerr --> button to proceed after data entered by receiver
                                            JButton checkerr=new JButton("Check Error");
                                            // adding elements to frame
                                            displaychosenparity.setMaximumSize(new Dimension(2000,40));
                                            displaychosenparity.setFont(new Font("Verdana", Font.BOLD, 14));
                                            datatobesent.setMaximumSize(new Dimension(2000,40));
                                            datatobesent.setFont(new Font("Verdana", Font.BOLD, 14));
                                            data_rec_label.setFont(new Font("Verdana", Font.BOLD, 14));
                                            data_rec_label.setMaximumSize(new Dimension(2000,40));
                                            frame.getContentPane().add(displaychosenparity);
                                            frame.getContentPane().add(datatobesent);
                                            frame.getContentPane().add(data_rec_label);
                                            frame.pack();
                                            JPanel op1=new JPanel();
                                            JPanel bp2=new JPanel();
                                            bp2.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
                                            bp2.setLayout(new BoxLayout(bp2,BoxLayout.Y_AXIS));
                                            JPanel datarecpanel=new JPanel();
                                            JScrollPane scrollablearea1=new JScrollPane(datarecpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                                            for(int i=0;i<k+1;i++){
                                                dataframerefreceive[i].setMaximumSize(new Dimension(800,40));
                                                datarecpanel.add(dataframerefreceive[i]);
                                                frame.pack();
                                            }
                                            datarecpanel.setPreferredSize(new Dimension(400,10000));
                                            op1.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
                                            scrollablearea1.setPreferredSize(new Dimension(800,400));
                                            op1.setPreferredSize(new Dimension(900,400));
                                            scrollablearea1.setLayout(new ScrollPaneLayout());
                                            bp2.setPreferredSize(new Dimension(200,200));
                                            datarecpanel.setLayout(new BoxLayout(datarecpanel,BoxLayout.Y_AXIS));
                                            op1.setLayout(new BoxLayout(op1,BoxLayout.Y_AXIS));
                                            scrollablearea1.setVisible(true);
                                            op1.setBackground(new Color(254,243,199));
                                            op1.add(scrollablearea1);
                                            op1.add(bp2);
                                            bp2.setBackground(new Color(254,243,199));
                                            frame.getContentPane().add(op1);
                                            checkerr.setFont(new Font("Verdana", Font.BOLD, 14));
                                            checkerr.setMaximumSize(new Dimension(250,40));
                                            bp2.add(checkerr);
                                            frame.pack();
                                            //after entering data received by receiver
                                            checkerr.addActionListener(
                                                new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent e){
                                                        //datafromrec --> setting label
                                                        JLabel datafromrec=new JLabel("Data from Receiver ");
                                                        //result --> setting label
                                                        JLabel result =new JLabel("Result is");
                                                        rowparity_receiver=new int[k];
                                                        colparity_receiver=new int[n];
                                                        data_receive_str=new String[k+1];
                                                        receiver=new int[k+1][n+1];
                                                        // data_send_str --> getting the data
                                                        for(int i=0;i<k+1;i++){
                                                        data_receive_str[i]=dataframerefreceive[i].getText();
                                                        }
                                                        //sender --> extracting the data into arr
                                                        System.out.println("Data from Receiver ");
                                                        for(int i=0;i<k+1;i++){
                                                            System.out.println(" Extracting Data for frame"+(i+1));
                                                            for(int j=0;j<n+1;j++){
                                                                receiver[i][j]=Character.getNumericValue(data_receive_str[i].charAt(j));
                                                                System.out.print(receiver[i][j]+" ");
                                                                displaydata.setText(displaydata.getText()+receiver[i][j]+" ");
                                                            }
                                                            System.out.println();
                                                            displaydata.setText(displaydata.getText()+divider.getText());
                                                        }
                                                        // getting parity choice 
                                                        switch(choice){
                                                            // if even parity is chosen , 1st case will be executed.
                                                            // calculating row parities, colparities, rparity
                                                            case 1:
                                                            colparity_receiver=calcColEvenParity(receiver,n,k);
                                                            rowparity_receiver=calcRowEvenParity(receiver,n,k);
                                                            rparity=calcEvenParity(rowparity_receiver);
                                                            System.out.println("rparity"+ rparity);
                                                            break;
                                                            
                                                            // if odd parity is chosen , 2nd case will be executed.
                                                            // calculating row parities, colparities, rparity
                                                            case 2:
                                                            colparity_receiver=calcColOddParity(receiver,n,k);
                                                            rowparity_receiver=calcRowOddParity(receiver,n,k);
                                                            rparity=calcOddParity(rowparity_receiver);
                                                            System.out.println("rparity"+ rparity);
                                                            break;
                                                        }
                                                     
                                                        // if all column parities are 0 --> colparity_flag is true
                                                        // rparity is column parity of row parities
                                                        boolean colparity_flag=true;
                                                        boolean rowparity_flag=true;
                                                        for(int i=0;i<k;i++){
                                                            System.out.println(rowparity_receiver[i]+"    ==   "+receiver[i][n]);
                                                            if(rowparity_receiver[i]==receiver[i][n]){
                                                                continue;
                                                            }
                                                            else{
                                                                rowparity_flag=false;break;
                                                            }
                                                        }
                                                        if(rparity!=receiver[k][n]){
                                                            colparity_flag=false;
                                                        }
                                                        else{
                                                            for(int i=0;i<n;i++){
                                                                if(colparity_receiver[i]==receiver[k][i]){
                                                                    continue;
                                                                }
                                                                else{
                                                                    colparity_flag=false;break;
                                                                }
                                                            }
                                                        }
                                                        System.out.println(rowparity_flag);
                                                        System.out.println(colparity_flag);
                                                        if(rowparity_flag && colparity_flag){
                                                            System.out.println("NO ERROR");
                                                            result.setText(result.getText()+" NO ERROR ");
                                                            result.setForeground(new Color(22, 163, 74));
                                                        }
                                                        else if(rowparity_flag!=true || colparity_flag!=true){
                                                            System.out.println("ERROR");
                                                            result.setText(result.getText()+"  ERROR ");
                                                            result.setForeground(new Color(255, 0, 0));
                                                        }
                                                        //adding GUI elements
                                                        datafromrec.setMaximumSize(new Dimension(2000,40));
                                                        datafromrec.setFont(new Font("Verdana", Font.BOLD, 14));
                                                        result.setMaximumSize(new Dimension(2000,40));
                                                        datarecpanel.add(datafromrec);
                                                        result.setFont(new Font("Verdana", Font.BOLD, 14));
                                                        datarecpanel.add(result);
                                                        frame.pack();

                                                    }
                                                }
                                            );
                                        }
                                    }
                                );
                                //adding GUI elements
                                bg.add(r1);bg.add(r2); 
                                displaydata.setMaximumSize(new Dimension(2000,40));
                                displaydata.setFont(new Font("Verdana", Font.BOLD, 14));
                                r1.setFont(new Font("Verdana", Font.BOLD, 14));
                                r2.setFont(new Font("Verdana", Font.BOLD, 14));
                                frame.add(displaydata);   
                                frame.add(r1);frame.add(r2);
                                calcparity.setFont(new Font("Verdana", Font.BOLD, 14));
                                calcparity.setMaximumSize(new Dimension(250,40));
                                frame.add(calcparity);frame.pack();
                            }
                        }

                    );
                    //adding GUI elements
                    for(int i=0;i<k;i++){
                        dataframerefsend[i].setMaximumSize(new Dimension(800,40));
                        datasendpanel.add(dataframerefsend[i]);
                        frame.pack();
                    }
                    JPanel bp1=new JPanel();
                    enterdataprompt.setMaximumSize(new Dimension(2000,40));
                    enterdataprompt.setFont(new Font("Verdana", Font.BOLD, 14));
                    frame.getContentPane().add(enterdataprompt);
                    datasendpanel.setPreferredSize(new Dimension(400,10000));
                    op.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
                    bp1.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
                    op.setLayout(new BoxLayout(op,BoxLayout.Y_AXIS));
                    bp1.setLayout(new BoxLayout(bp1,BoxLayout.Y_AXIS));
                    scrollablearea.setPreferredSize(new Dimension(800,400));
                    op.setPreferredSize(new Dimension(900,400));
                    bp1.setPreferredSize(new Dimension(200,200));
                    scrollablearea.setLayout(new ScrollPaneLayout());
                    datasendpanel.setLayout(new BoxLayout(datasendpanel,BoxLayout.Y_AXIS));
                    scrollablearea.setVisible(true);
                    op.add(scrollablearea);
                    op.add(bp1);
                    op.setBackground(new Color(254,243,199));
                    bp1.setBackground(new Color(254,243,199));
                    frame.getContentPane().add(op);
                    nextbt_data.setMaximumSize(new Dimension(190,40));
                    nextbt_data.setFont(new Font("Verdana",Font.BOLD, 14));
                    bp1.add(nextbt_data);
                    frame.pack();
                }
            }
        );
        //adding GUI elements
        kprompt.setMaximumSize(new Dimension(2000,40));
        kprompt.setFont(new Font("Verdana", Font.BOLD, 14));
        frame.getContentPane().add(kprompt);
        ktextbox.setMaximumSize(new Dimension(800,40));
        frame.getContentPane().add(ktextbox);
        nprompt.setMaximumSize(new Dimension(2000,40));
        nprompt.setFont(new Font("Verdana", Font.BOLD, 14));
        frame.getContentPane().add(nprompt);
        ntextbox.setMaximumSize(new Dimension(800,40));
        frame.getContentPane().add(ntextbox);
        next.setMaximumSize(new Dimension(90,40));
        next.setFont(new Font("Verdana", Font.BOLD, 14));
        frame.getContentPane().add(next);
        frame.pack();
        //setting frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(254,243,199));
        frame.setResizable(true);            
        frame.getContentPane().setPreferredSize(new Dimension(1200,800));
        frame.setLocationRelativeTo(null);
        BoxLayout layout=new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS);
        frame.setLayout(layout);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]){
    //creating object for class and calling BuildGUI
    twodparity obj=new twodparity();
    obj.BuildGUI();
    }
    /*
    Scanner scan=new Scanner(System.in);
    System.out.println("Enter K");
    int k=scan.nextInt();
    System.out.println("Enter N");
    int n=scan.nextInt();
    int sender[][]=new int[k+1][n+1];
    int receiver[][]=new int[k+1][n+1];
    int rowparity[]=new int[k];
    int colparity[]=new int[n];
    int rowparity_receiver[]=new int[k];
    int colparity_receiver[]=new int[n];
    System.out.println("N is "+n);
    for(int i=0;i<k;i++){
        System.out.println("Enter data for frame"+(i+1));
        for(int j=0;j<n;j++){
            sender[i][j]=scan.nextInt();
        }
    }
    System.out.println("Data from Sender");
    
    for(int i=0;i<k;i++){
        System.out.println("Data of frame "+(i+1));
    for(int j=0;j<n;j++){
       System.out.print(sender[i][j]+" ");
    }
        System.out.println();
    }
    System.out.println("Enter Choice 1- even parity and 2- odd parity");
    int choice=scan.nextInt();
    int rparity=-1;int cparity=-1;
    switch(choice){
        case 1:
        rowparity=obj.calcRowEvenParity(sender,n,k);
        colparity=obj.calcColEvenParity(sender,n,k);
        rparity=obj.calcEvenParity(rowparity);
        cparity=obj.calcEvenParity(colparity);break;
        case 2:
        rowparity=obj.calcRowOddParity(sender,n,k);
        colparity=obj.calcColOddParity(sender,n,k);
        rparity=obj.calcOddParity(rowparity);
        cparity=obj.calcOddParity(colparity);
        break;
    }

    
    int ind;
    for(ind=0;ind<k;ind++){
        sender[ind][n]=rowparity[ind];
    }
    sender[ind][n]=rparity;
    ind=0;
    for(ind=0;ind<n;ind++){
        sender[k][ind]=colparity[ind];
    }
    sender[k][ind]=cparity;
    System.out.println("Data from Sender to be transmitted");
    
    for(int i=0;i<k+1;i++){
        System.out.println((i+1)+"Data of frame ");
    for(int j=0;j<n+1;j++){
       System.out.print(sender[i][j]+" ");
    }
        System.out.println();
    }
    System.out.println("Enter data Received");
    for(int i=0;i<k+1;i++){
        System.out.println("Enter data for frame"+(i+1));
        for(int j=0;j<n+1;j++){
            receiver[i][j]=scan.nextInt();
        }
    }

    switch(choice){
        case 1:
        rowparity_receiver=obj.calcRowEvenParity(receiver,n+1,k+1);
        colparity_receiver=obj.calcColEvenParity(receiver,n+1,k+1);
        rparity=obj.calcEvenParity(rowparity_receiver);
        cparity=obj.calcEvenParity(colparity_receiver);break;
        case 2:
        rowparity_receiver=obj.calcRowOddParity(receiver,n+1,k+1);
        colparity_receiver=obj.calcColOddParity(receiver,n+1,k+1);
        rparity=obj.calcOddParity(rowparity_receiver);
        cparity=obj.calcOddParity(colparity_receiver);
        break;
    }
    boolean rowparity_flag=true;
    for(int i=0;i<rowparity_receiver.length;i++){
        if(cparity==0){
            if(rowparity_receiver[i]==0){
            continue;
            }
        }
        else if(rowparity_receiver[i]==1){
            rowparity_flag=false;break;
        }
    }

    boolean colparity_flag=true;
    for(int i=0;i<colparity_receiver.length;i++){
        if(rparity==0){
            if(colparity_receiver[i]==0){
            continue;
            }
        }
        else if(colparity_receiver[i]==1){
            colparity_flag=false;break;
        }
    }

    if(colparity_flag && rowparity_flag){
        System.out.println("NO ERROR");
    }
    else if(colparity_flag!=true && rowparity_flag!=true){
        System.out.println("ERROR");
    }
    

    }*/

}
