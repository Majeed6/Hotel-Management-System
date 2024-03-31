package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener{

    Choice ccustomer;
    JTextField tfRoom, tfavailable, tfcleaning, tfpaid, tfpending;
    JButton check, update, back;
    JComboBox<String> comboBox, comboBox2;

    UpdateRoom(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Century Schoolbook", Font.BOLD, 25));
        text.setBounds(30,20,300,30);
        text.setForeground(Color.BLACK);
        add(text);

        JLabel room = new JLabel("Room Number");
        room.setBounds(30,130,100,20);
        room.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(room);

        ccustomer = new Choice();
        ccustomer.setBounds(200,130,150,25);
        add(ccustomer);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from room");
            while(rs.next()){
                ccustomer.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JLabel availability = new JLabel("Availability");
        availability.setBounds(30,180,100,20);
        availability.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(availability);

        String[] options = {"Available", "Occupied"};
        comboBox = new JComboBox<>(options);
        comboBox.setBounds(200, 180, 150, 25);
        add(comboBox);

        JLabel cleaning = new JLabel("Cleaning Status");
        cleaning.setBounds(30,230,100,20);
        cleaning.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(cleaning);

        String[] options2 = {"Clean", "Dirty"};
        comboBox2 = new JComboBox<>(options2);
        comboBox2.setBounds(200,230,150,25);
        add(comboBox2);



        check = new JButton("Check");
        check.setBounds(30,300,100,30);
        check.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        check.setForeground(goldColor);
        check.addActionListener(this);
        check.setFocusPainted(false);
        check.setOpaque(false);  // Make it non-opaque
        check.setBorder(roundedBorder);
        check.setUI(new CustomButtonUI());
        add(check);

        update = new JButton("Update");
        update.setBounds(150,300,100,30);
        update.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        update.setForeground(goldColor);
        update.addActionListener(this);
        update.setFocusPainted(false);
        update.setOpaque(false);  // Make it non-opaque
        update.setBorder(roundedBorder);
        update.setUI(new CustomButtonUI());
        add(update);

        back = new JButton("Back");
        back.setBounds(270,300,100,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,500,300);
        add(image);


        setBounds(300,200,980,450);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == check){
            String selectedroom = ccustomer.getSelectedItem();
            String query = "select * from room where roomnumber = '"+selectedroom+"' ";
            try {
                Connector c = new Connector();
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    String availability = rs.getString("availability");
                    String cleaningStatus = rs.getString("cleaning_status");

                    // Update the combo box selection based on availability
                    if (availability.equals("Available")) {
                        comboBox.setSelectedItem("Available");
                    } else if (availability.equals("Occupied")) {
                        comboBox.setSelectedItem("Occupied");
                    }

                    if (cleaningStatus.equals("Clean")) {
                        comboBox2.setSelectedItem("Clean");
                    } else if (cleaningStatus.equals("Dirty")) {
                        comboBox2.setSelectedItem("Dirty");
                    }
                } else {
                    System.out.println("No room found with roomnumber = " + selectedroom);
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }


        else if(e.getSource() == update){
            // String number = ccustomer.getSelectedItem();
            String room = ccustomer.getSelectedItem();
            String isAvailable = (String)comboBox.getSelectedItem();
            String cleanStatus = (String)comboBox2.getSelectedItem();

            try {
                String customerQuery = "SELECT * FROM customerInfo WHERE room = '" + room + "'";
                if (customerQuery.isEmpty()){
                    Connector conn = new Connector();
                    conn.s.executeUpdate("update room set availability = '"+isAvailable+"', cleaning_status = '"+cleanStatus+"'  where roomnumber = '"+room+"' ");
                    JOptionPane.showMessageDialog(null, "Data Upadated Successfully");
                }else{
                    JOptionPane.showMessageDialog(null, "this room is not availabilable");
                }
                setVisible(false);
                if(UserSession.isManager==true){
                    boolean isManager = true;
                    new Reception(UserSession.isManager);
                }else{
                    boolean isManager = false;
                    new Reception(UserSession.isManager);
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
        else if(e.getSource() == back) {
            setVisible(false);
            if(UserSession.isManager==true){
                boolean isManager = true;
                new Reception(UserSession.isManager);
            }else{
                boolean isManager = false;
                new Reception(UserSession.isManager);
            }
        }
    }

    public static void main(String[] args){
        new UpdateRoom();
    }

}

