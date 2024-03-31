package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class CustumerInfo extends JFrame implements ActionListener{
    JTable table;
    JButton back;

    DashBoard db = new DashBoard();
    CustumerInfo(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel l1 = new JLabel("Document Type");
        l1.setBounds(10,10,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Number");
        l2.setBounds(160,10,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Name");
        l3.setBounds(290,10,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Gender");
        l4.setBounds(410,10,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Country");
        l5.setBounds(510,10,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);

        JLabel l6 = new JLabel("Room Number");
        l6.setBounds(640,10,100,20);
        l6.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l6);

        JLabel l7 = new JLabel("Checkin Time");
        l7.setBounds(760,10,100,20);
        l7.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l7);

        JLabel l8 = new JLabel("Deposit");
        l8.setBounds(900,10,100,20);
        l8.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l8);

        table = new JTable();
        table.setBounds(0,40,1000,400);
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from customerInfo");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(420,500,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        setBounds(300,200,1000,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        if(UserSession.isManager==true){
            new Reception(UserSession.isManager);
        }else{
            new Reception(UserSession.isManager);
        }
        
    }
    public static void main(String[] args){
        new CustumerInfo();
    }
}
