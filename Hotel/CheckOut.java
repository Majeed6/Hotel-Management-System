package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class CheckOut extends JFrame implements ActionListener {
    Choice cCustomerId;
    JLabel lblroomnumber, lblcheckinTime, lblcheckoutTime;
    JButton  checkout, back;

    DashBoard db = new DashBoard();

    boolean isManager;

    CheckOut() {

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("CheckOut");
        heading.setBounds(100,20,150,30);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
        add(heading);


        JLabel lblId = new JLabel("Customer Id");
        lblId.setBounds(30, 80, 100, 30);
        lblId.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblId);

        cCustomerId = new Choice();
        cCustomerId.setBounds(150, 80, 150, 25);
        cCustomerId.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(cCustomerId);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(310, 80, 20, 20);
        add(image);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        lblroom.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 100, 30);
        lblroomnumber.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblroomnumber);


        JLabel lblcheckin = new JLabel("CheckIn Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        lblcheckin.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblcheckin);

        lblcheckinTime = new JLabel();
        lblcheckinTime.setBounds(150, 180, 100, 30);
        lblcheckinTime.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblcheckinTime);

        JLabel lblcheckout = new JLabel("CheckOut Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        lblcheckout.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblcheckout);

        lblcheckoutTime = new JLabel();
        lblcheckoutTime.setBounds(150, 230, 100, 30);
        lblcheckoutTime.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(lblcheckoutTime);

        Date date = new Date();
        lblcheckoutTime = new JLabel(""+ date); // double quotes makes date into string ; 
        lblcheckoutTime.setBounds(150, 230, 180, 30);
        lblcheckoutTime.setFont(new Font("Century Schoolbook", Font.BOLD, 11));
        add(lblcheckoutTime);

        checkout = new JButton("CheckOut");
        checkout.setBounds(30, 280, 120, 30);
        checkout.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        checkout.setForeground(goldColor);
        checkout.addActionListener(this);
        checkout.setFocusPainted(false);
        checkout.setOpaque(false);  // Make it non-opaque
        checkout.setBorder(roundedBorder);
        checkout.setUI(new CustomButtonUI());
        add(checkout);

        back = new JButton("Back");
        back.setBounds(170, 280, 120, 30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(350, 50, 400, 250);
        add(image1);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from customerInfo");
            while(rs.next()) {
                cCustomerId.add(rs.getString("number"));
                lblroomnumber.setText(rs.getString("room"));
                lblcheckinTime.setText(rs.getString("checkintime"));
            }
            cCustomerId.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    // Get the selected customer ID
                    String selectedCustomerId = cCustomerId.getSelectedItem();

                    try {
                        Connector conn = new Connector();
                        ResultSet rs = conn.s.executeQuery("select * from customerInfo where number = '" + selectedCustomerId + "'");

                        if (rs.next()) {
                            lblroomnumber.setText(rs.getString("room"));
                            lblcheckinTime.setText(rs.getString("checkintime"));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        setBounds(300, 200, 800, 400);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkout) {
            String time = lblcheckoutTime.getText();
            String room = lblroomnumber.getText();
            String checkIn = lblcheckinTime.getText();
            String customerId = cCustomerId.getSelectedItem();

            try {
                Connector conn = new Connector();
                // Retrieve customer information from customerInfo table
                String customerQuery = "SELECT * FROM customerInfo WHERE number = '" + customerId + "'";
                ResultSet rs = conn.s.executeQuery(customerQuery);

                if (rs.next()) {
                    String document = rs.getString("document");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    String country = rs.getString("country");
                    String deposit = rs.getString("deposit");


                    // Insert the customer information into the records table
                    String query = "INSERT INTO records (document, number, name, gender, country, room, checkintime, checkouttime, deposit) VALUES ('" +
                            document + "', '" + customerId + "', '" + name + "', '" + gender + "', '" + country + "', '" + room + "', '" +
                            checkIn + "', '" + time + "', '" + deposit + "')";

                    // Delete the customer information from customerInfo table
                    String query1 = "DELETE FROM customerInfo WHERE number = '" + customerId + "'";

                    // Update the room availability in the room table
                    String query2 = "UPDATE room SET availability = 'Available' WHERE roomnumber = '" + room + "'";

                    // Execute the queries
                    conn.s.executeUpdate(query);
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Checkout Done");
                    setVisible(false);
                    if(db.getisManager()==true){
                        boolean isManager = true;
                        new Reception(UserSession.isManager);
                    }else{
                        boolean isManager = false;
                        new Reception(UserSession.isManager);
                    }
                }

            } catch (Exception ae) {
                ae.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            if(UserSession.isManager==true){
                new Reception(UserSession.isManager);
            }else{
                new Reception(UserSession.isManager);
            }
        }
    }


    public static void main(String[] args) {
        new CheckOut();
    }
}
