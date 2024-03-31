package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener{
    JTable table;
    JButton back, submit;
    JComboBox<String> bedtype;
    JCheckBox available;

    DashBoard db = new DashBoard();

    SearchRoom(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Century Schoolbook", Font.BOLD,20));
        text.setBounds(400,30,200,30);
        add(text);

        JLabel bed = new JLabel("Bed Type");
        bed.setBounds(50,100,100,20);
        bed.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(bed);

        bedtype = new JComboBox<>(new String[] {"Single", "Double"});
        bedtype.setBounds(150,100,150,25);
        bedtype.setBackground(Color.WHITE);
        bedtype.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(bedtype);

        available = new JCheckBox("Only display Available");
        available.setBounds(650,100,160,25);
        available.setBackground(Color.WHITE);
        available.setFont(new Font("Century Schoolbook", Font.BOLD, 11));
        add(available);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50,160,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Availibility");
        l2.setBounds(270,160,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Cleaning Status");
        l3.setBounds(450,160,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(670,160,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(870,160,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);

        table = new JTable();
        table.setBounds(0,200,1000,300);
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        submit = new JButton("Submit");
        submit.setBounds(300,520,120,30);
        submit.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        submit.setForeground(goldColor);
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setOpaque(false);  // Make it non-opaque
        submit.setBorder(roundedBorder);
        submit.setUI(new CustomButtonUI());
        add(submit);

        back = new JButton("Back");
        back.setBounds(500,520,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        setBounds(300,150,1000,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            try {
                String query1 = "select * from room where bed_type = '"+bedtype.getSelectedItem()+"' ";
                String query2 = "select * from room where availability = 'Available' AND bed_type = '"+bedtype.getSelectedItem()+"' ";

                Connector conn = new Connector();
                ResultSet rs;
                if(available.isSelected()){
                    rs = conn.s.executeQuery(query2);
                }
                else {
                    rs = conn.s.executeQuery(query1);
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
        else if(e.getSource() == back){
            setVisible(false);
            if(UserSession.isManager==true){
                new Reception(UserSession.isManager);
            }else{
                new Reception(UserSession.isManager);
            }
        }
        
    }
    public static void main(String[] args){
        new SearchRoom();
    }
}
