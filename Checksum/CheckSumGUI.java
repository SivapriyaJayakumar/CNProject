import java.util.*;
import javax.swing.*;
import java.awt.*;    
import javax.swing.JOptionPane;
public class ChecksumGUI{
    JFrame frame;
    JTextField noofframes;
    JTextField noofdatabits;
    JButton nextbt;
    JLabel framesprompt;
    JLabel nprompt;
    int no_frames=0;
    String data_collected[];
    int data_arr_sender[][];
    int no_bits=0;
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
                    data_arr_sender=new int[no_frames][no_bits];
                    JTextField dataref[]=new JTextField[no_frames];
                    JButton nextbt_data=new JButton("Next");
                    int xc=100;
                    int yc=100;
                    for(int i=0;i<no_frames;i++){
                        String promptdata=" Enter Data for Frame "+(i+1);
                        JLabel datalabel=new JLabel(promptdata);
                        JTextField data=new JTextField(no_bits);
                        dataref[i]=data;
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
                                System.out.println("Data collected from sender");
                                for(int i=0;i<dataref.length;i++){
                                    data_collected[i]=dataref[i].getText();
                                    System.out.println(" data_collected [ "+ i +"] : " +data_collected[i]);
                                }
                                System.out.println("Extracting data into array");
                                for(int i=0;i<no_frames;i++){
                                    for(int j=0;j<no_bits;j++){
                                        data_arr_sender[i][j]=Character.getNumericValue(data_collected[i].charAt(j));
                                    }
                                }
                                System.out.println("Printing Data Array");
                                for(int i=0;i<no_frames;i++){
                                    System.out.println("Frame : "+(i+1));
                                    for(int j=0;j<no_bits;j++){
                                        System.out.print(data_arr_sender[i][j]+" ");
                                    }
                                    System.out.println();
                                }
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
        ChecksumGUI checksumgui=new ChecksumGUI();
        checksumgui.BuildGUI();
    }

}