package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class CustumerForm extends JFrame implements ActionListener{
    DashBoard db = new DashBoard();
    JComboBox<String> comboId;
    JTextField tfnumber, tfname, tfcountry, tfDeposit;
    JRadioButton rmale, rfemale;
    Choice croom;
    JLabel checkinTime;
    JButton add, back;

    CustumerForm(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Century Schoolbook", Font.BOLD, 17));
        add(text);

        JLabel id = new JLabel("Identity");
        id.setBounds(35, 80, 100, 20);
        id.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(id);

        String options[] = {"ID Card", "Passport", "Driving License", "Voter-id Card", "Ration Card"};
        comboId = new JComboBox<>(options);
        comboId.setBounds(200, 80, 150, 25);
        comboId.setBackground(Color.WHITE);
        comboId.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
        add(comboId);

        JLabel number = new JLabel("Number");
        number.setBounds(35, 120, 100, 20);
        number.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(number);

        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        tfnumber.setBorder(roundedBorder);
        add(tfnumber);

        JLabel name = new JLabel("Name");
        name.setBounds(35, 160, 100, 20);
        name.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        tfname.setBorder(roundedBorder);
        add(tfname);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(35, 200, 100, 20);
        gender.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(gender);

        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200,200,60,25);
        rmale.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270,200,100,25);
        rfemale.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(rfemale);

        JLabel country = new JLabel("Country");
        country.setBounds(35, 240, 100, 20);
        country.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(country);

        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        tfcountry.setBorder(roundedBorder);
        add(tfcountry);

        JLabel room = new JLabel("Room Number");
        room.setBounds(35, 280, 150, 20);
        room.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(room);

        croom = new Choice();

        try {
            Connector conn = new Connector();
            String query = "Select * from room where availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        croom.setBounds(200,280,150,25);
        add(croom);

        JLabel time = new JLabel("Checkin Time");
        time.setBounds(35, 320, 150, 20);
        time.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(time);

        Date date = new Date();

        checkinTime = new JLabel("" + date);
        checkinTime.setBounds(200, 320, 170, 25);
        checkinTime.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
        add(checkinTime);

        JLabel deposit = new JLabel("Deposit");
        deposit.setBounds(35, 360, 100, 20);
        deposit.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
        add(deposit);

        tfDeposit = new JTextField();
        tfDeposit.setBounds(200,360,150,25);
        tfDeposit.setBorder(roundedBorder);
        add(tfDeposit);

        add = new JButton("ADD");
        add.setBounds(50, 410, 120, 30);
        add.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        add.setForeground(goldColor);
        add.addActionListener(this);
        add.setFocusPainted(false);
        add.setOpaque(false);
        add.setBorder(roundedBorder);
        add.setUI(new CustomButtonUI());
        add(add);

        back = new JButton("BACK");
        back.setBounds(200, 410, 120, 30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/yoga.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);


        setBounds(350, 200, 800, 500);
        setVisible(true);

        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String id = (String) comboId.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;

            if (rmale.isSelected()) {
                gender = "Male";
            } else if (rfemale.isSelected()) {
                gender = "Female";
            }

            String country = tfcountry.getText();
            String room = (String) croom.getSelectedItem();
            String time = checkinTime.getText();
            String deposit = tfDeposit.getText();

            // Perform validation checks
            if (number.isEmpty() || name.isEmpty() || gender == null || country.isEmpty() || room.isEmpty() || deposit.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the required fields");
            } else {
                try {
                    String query = "insert into customerInfo values('" + id + "','" + number + "','" + name + "','" + gender + "','" + country + "','" + room + "','" + time + "','" + deposit + "')";
                    String query1 = "insert into records(document, number, name, gender, country, room, checkintime, deposit) values('" + id + "','" + number + "','" + name + "','" + gender + "','" + country + "','" + room + "','" + time + "','" + deposit + "')";
                    String query2 = "update room set availability = 'Occupied' where roomnumber = '" + room + "' ";

                    Connector conn = new Connector();
                    conn.s.executeUpdate(query);
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "New Customer Added Successfully");

                    setVisible(false);
                    if (UserSession.isManager) {
                        new Reception(UserSession.isManager);
                    } else {
                        new Reception(UserSession.isManager);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            if (UserSession.isManager) {
                new Reception(UserSession.isManager);
            } else {
                new Reception(UserSession.isManager);
            }
        }
    }


    public static void main(String[] args){
        new CustumerForm();
    }
}
