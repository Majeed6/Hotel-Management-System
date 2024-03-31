package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCheck extends JFrame implements ActionListener{

    Choice ccustomer;
    JTextField tfRoom, tfname, tfcheckin, tfpaid, tfpending;
    JButton check, update, back;
    JCheckBox updateR;

    UpdateCheck(){
        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
        text.setBounds(90,20,200,30);
        text.setForeground(Color.BLACK);
        add(text);

        JLabel id = new JLabel("Customer Id");
        id.setBounds(30,80,100,20);
        id.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(id);

        ccustomer = new Choice();
        ccustomer.setBounds(200,80,150,25);
        add(ccustomer);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from customerInfo");
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel room = new JLabel("Room Number");
        room.setBounds(30,120,100,20);
        room.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(room);

        tfRoom = new JTextField();
        tfRoom.setBounds(200,120,150,25);
        add(tfRoom);

        JLabel name = new JLabel("Name");
        name.setBounds(30,160,100,20);
        name.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);

        JLabel checkin = new JLabel("Checkin Time");
        checkin.setBounds(30,200,100,20);
        checkin.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(checkin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(200,200,150,25);
        add(tfcheckin);

        JLabel paid = new JLabel("Amount Paid");
        paid.setBounds(30,240,100,20);
        paid.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(paid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200,240,150,25);
        add(tfpaid);

        JLabel pending = new JLabel("Amount Pending");
        pending.setBounds(30,280,110,20);
        pending.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(pending);

        tfpending = new JTextField();
        tfpending.setBounds(200,280,150,25);
        add(tfpending);

        updateR = new JCheckBox("do you wont update the room ??");
        updateR.setBounds(30, 310, 230, 30);
//        isManager.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        add(updateR);

        check = new JButton("Check");
        check.setBounds(30,350,100,30);
        check.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        check.setForeground(goldColor);
        check.addActionListener(this);
        check.setFocusPainted(false);
        check.setOpaque(false);  // Make it non-opaque
        check.setBorder(roundedBorder);
        check.setUI(new CustomButtonUI());
        add(check);

        update = new JButton("Update");
        update.setBounds(150,350,100,30);
        update.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        update.setForeground(goldColor);
        update.addActionListener(this);
        update.setFocusPainted(false);
        update.setOpaque(false);  // Make it non-opaque
        update.setBorder(roundedBorder);
        update.setUI(new CustomButtonUI());
        add(update);

        back = new JButton("Back");
        back.setBounds(270,350,100,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/check.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(370,50,500,300);
        add(image);


        setBounds(300,200,900,500);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == check){
            String id = ccustomer.getSelectedItem();
            String query = "select * from customerInfo where number = '"+id+"' ";
            try {
                Connector c = new Connector();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    tfRoom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposit"));
                }

                ResultSet rs2 = c.s.executeQuery("select*from room where roomnumber = '"+tfRoom.getText()+"' ");
                while(rs2.next()){
                    String price = rs2.getString("price");
                    double amountpending = Double.parseDouble(price) - Double.parseDouble(tfpaid.getText());
                    tfpending.setText("" + amountpending);
                }

            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
        else if(e.getSource() == update){
            String number = ccustomer.getSelectedItem();
            String room = tfRoom.getText();
            String name = tfname.getText();
            String checkin = tfcheckin.getText();
            String deposit = tfpaid.getText();

            try {
                Connector conn = new Connector();
                if(updateR.isSelected()){
                    String customerQuery = "SELECT * FROM customerInfo WHERE room = '" + room + "'";
                    ResultSet resultSet = conn.s.executeQuery(customerQuery);
                    if (!resultSet.next()) {
                        conn.s.executeUpdate("update customerInfo set room = '" + room + "', name = '" + name + "', checkintime = '" + checkin + "', deposit = '" + deposit + "' where number = '" + number + "' ");
                        String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = '" + room + "'";
                        conn.s.executeUpdate(query2);
                        JOptionPane.showMessageDialog(null, "Data Upadated Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(null, "you can't go to the room number '" +room+ "'this room is Occupied");
                    }
                }else{
                    conn.s.executeUpdate("update customerInfo set name = '" + name + "', checkintime = '" + checkin + "', deposit = '" + deposit + "' where number = '" + number + "' ");
                    JOptionPane.showMessageDialog(null, "Data Upadated Successfully!");
                }

                setVisible(false);
                if(UserSession.isManager==true){
                    new Reception(UserSession.isManager);
                }else{
                    new Reception(UserSession.isManager);
                }
            } catch (Exception aae) {
                aae.printStackTrace();
            }
        }
        else if(e.getSource() == back) {
            setVisible(false);
            if(UserSession.isManager==true){

                new Reception(UserSession.isManager);
            }else{
                new Reception(UserSession.isManager);
            }
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }

}

