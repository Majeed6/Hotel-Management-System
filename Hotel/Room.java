package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Room extends JFrame implements ActionListener{
    JTable table;
    JButton back,submit;
    DashBoard db = new DashBoard();
    JComboBox<String> selectquery;
    JTextField  box = new JTextField(20), Result = new JTextField(20);

    Room(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bed.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,0,600,600);
        add(image);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(10,50,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Availibility");
        l2.setBounds(120,50,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Status");
        l3.setBounds(230,50,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(330,50,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(410,50,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);
        selectquery = new JComboBox<>(new String[] {"Total number of rooms", "number of available or occupied rooms",
                "How many Male Customers booked in a certain period", "How many Female Customers booked in a certain period"});
        selectquery.setBounds(20,0,250,25);
        selectquery.setBackground(Color.WHITE);
        selectquery.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(selectquery);

        JLabel l10 = new JLabel("box:");
        l10.setBounds(280,10,100,20);
        l10.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l10);

        box.setBounds(310,10,70,20);
        add(box);
        Result.setBounds(400,10,100,20);
        add(Result);

        table = new JTable();
        table.setBounds(10,80,500,400);
//        table.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(200,500,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);
        submit = new JButton("submit");
        submit.setBounds(330,500,120,30);
        submit.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        submit.setForeground(goldColor);
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setOpaque(false);
        submit.setBorder(roundedBorder);
        submit.setUI(new CustomButtonUI());
        add(submit);

        setBounds(270,100,1050,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String boxo = box.getText();
        if(e.getSource() == back){
            setVisible(false);
            new Reception(UserSession.isManager);
        }else if(e.getSource() == submit){
            try {
                if (selectquery.getSelectedItem() == "Total number of rooms"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(roomnumber) AS TotalROOMS FROM room";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        double totalroom = rs.getDouble("TotalROOMS");
                        Result.setText(String.valueOf(totalroom));
                    } else {
                        Result.setText("No data found.");
                    }
                }if (selectquery.getSelectedItem() == "number of available or occupied rooms"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(roomnumber) AS TotalROOMS FROM room WHERE availability = ? ";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, boxo);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        double totalroom = rs.getDouble("TotalROOMS");
                        Result.setText(String.valueOf(totalroom));
                    } else {
                        Result.setText("No data found.");
                    }
                }




            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }





        }



    }
    public static void main(String[] args){
        new Room();
    }
}
