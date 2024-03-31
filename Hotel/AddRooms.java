package HotalManagementSystem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class AddRooms extends JFrame implements ActionListener {
    JTextField tfroom, tfprice;
    JButton addButton, cancel;
    JComboBox<String> cbAvail, cbClean, cbbedType;
    AddRooms() {

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("ADD ROOMS");
        heading.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);

        setLayout(null);
        setBounds(330, 200, 940, 470 );

        JLabel roomNo = new JLabel("Room Number");
        roomNo.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
        roomNo.setBounds(60, 80, 120, 30);
        add(roomNo);
        

        tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        add(tfroom);

        JLabel avail = new JLabel("Available ");
        avail.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
        avail.setBounds(60, 130, 120, 30);
        add(avail);

        String[] availOption = {"Available", "Occupied"};
        cbAvail = new JComboBox<>(availOption);
        cbAvail.setBounds(200, 130, 150, 30);
        cbAvail.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        cbAvail.setBackground(Color.white);
        add(cbAvail);

        JLabel clean = new JLabel("Clean ");
        clean.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
        clean.setBounds(60, 180, 120, 30);
        add(clean);

        String[] cleanOption = {"Cleaned", "Dirty"};
        cbClean = new JComboBox<>(cleanOption);
        cbClean.setBounds(200, 180, 150, 30);
        cbClean.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        cbClean.setBackground(Color.white);
        add(cbClean);

        JLabel price = new JLabel("Price");
        price.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
        price.setBounds(60, 230, 120, 30);
        add(price);


        tfprice= new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);

        JLabel bedtype = new JLabel("Bed type");
        bedtype.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
        bedtype.setBounds(60, 280, 120, 30);
        add(bedtype);

        String[] bedtypeOption = {"Single", "Double"};
        cbbedType = new JComboBox<>(bedtypeOption);
        cbbedType.setBounds(200, 280, 150, 30);
        cbbedType.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        cbbedType.setBackground(Color.white);
        add(cbbedType);

        addButton =  new JButton("Add Rooms");
        addButton.setBounds(60, 350, 130, 30);
        addButton.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        addButton.setForeground(goldColor);
        addButton.addActionListener(this);
        addButton.setFocusPainted(false);
        addButton.setOpaque(false);  // Make it non-opaque
        addButton.setBorder(roundedBorder);
        addButton.setUI(new CustomButtonUI());
        add(addButton);


        cancel =  new JButton("Cancel");
        cancel.setBounds(220, 350, 130, 30);
        cancel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        cancel.setForeground(goldColor);
        cancel.addActionListener(this);
        cancel.setFocusPainted(false);
        cancel.setOpaque(false);  // Make it non-opaque
        cancel.setBorder(roundedBorder);
        cancel.setUI(new CustomButtonUI());
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bed.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);


        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== addButton ){
            String roomnumber = tfroom.getText();
            String availability = (String) cbAvail.getSelectedItem();
            String cleanStatus = (String) cbClean.getSelectedItem();
            String price = tfprice.getText();
            String bedType = (String) cbbedType.getSelectedItem();

            try {
                Connector conn = new Connector();
                String query = "insert into room values('"+roomnumber+"', '"+availability+"', '"+cleanStatus+"', '"+price+"', '"+bedType+"')";
                
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Room added successfully");

                setVisible(false);
                new AddRooms();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "room not added, please try again");
            }
        } else if ( e.getSource()==cancel ){
            setVisible(false);
            new DashBoard(true);
        }
        
    }

    public static void main(String[] args) {
        new AddRooms();
    }

}
