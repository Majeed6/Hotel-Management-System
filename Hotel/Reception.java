package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener {

//    boolean isManager1 = UserSession.isManager;
    JButton newCustomer, rooms, department, allEmployee, managerInfo,
            custermers, searchRoom, status, roomStatus, checkout, pickup, records, logout;

    Reception(boolean isManager){

        if(UserSession.isManager) {
            Color goldColor = new Color(218, 165, 32);
            Border roundedBorder = new RoundedBorder(goldColor, 3);

            getContentPane().setBackground(Color.WHITE);

            newCustomer = new JButton("New Customer Form");
            newCustomer.setBounds(10, 30, 200, 30);
            newCustomer.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            newCustomer.setForeground(goldColor);
            newCustomer.addActionListener(this);
            newCustomer.setFocusPainted(false);
            newCustomer.setOpaque(false);
            newCustomer.setBorder(roundedBorder);
            newCustomer.setUI(new CustomButtonUI());
            add(newCustomer);

            rooms = new JButton("Rooms");
            rooms.setBounds(10, 70, 200, 30);
            rooms.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            rooms.setForeground(goldColor);
            rooms.addActionListener(this);
            rooms.setFocusPainted(false);
            rooms.setOpaque(false);
            rooms.setBorder(roundedBorder);
            rooms.setUI(new CustomButtonUI());
            add(rooms);


            allEmployee = new JButton("All Employee");
            allEmployee.setBounds(10, 110, 200, 30);
            allEmployee.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            allEmployee.setForeground(goldColor);
            allEmployee.addActionListener(this);
            allEmployee.setFocusPainted(false);
            allEmployee.setOpaque(false);
            allEmployee.setBorder(roundedBorder);
            allEmployee.setUI(new CustomButtonUI());
            add(allEmployee);

            custermers = new JButton("Customer Info");
            custermers.setBounds(10, 150, 200, 30);
            custermers.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            custermers.setForeground(goldColor);
            custermers.addActionListener(this);
            custermers.setFocusPainted(false);
            custermers.setOpaque(false);
            custermers.setBorder(roundedBorder);
            custermers.setUI(new CustomButtonUI());
            add(custermers);


            checkout = new JButton("Checkout");
            checkout.setBounds(10, 190, 200, 30);
            checkout.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            checkout.setForeground(goldColor);
            checkout.addActionListener(this);
            checkout.setFocusPainted(false);
            checkout.setOpaque(false);
            checkout.setBorder(roundedBorder);
            checkout.setUI(new CustomButtonUI());
            add(checkout);

            status = new JButton("Update Status");
            status.setBounds(10, 230, 200, 30);
            status.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            status.setForeground(goldColor);
            status.addActionListener(this);
            status.setFocusPainted(false);
            status.setOpaque(false);
            status.setBorder(roundedBorder);
            status.setUI(new CustomButtonUI());
            add(status);

            roomStatus = new JButton("Update Room Status");
            roomStatus.setBounds(10, 270, 200, 30);
            roomStatus.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            roomStatus.setForeground(goldColor);
            roomStatus.addActionListener(this);
            roomStatus.setFocusPainted(false);
            roomStatus.setOpaque(false);
            roomStatus.setBorder(roundedBorder);
            roomStatus.setUI(new CustomButtonUI());
            add(roomStatus);



            searchRoom = new JButton("Search Room");
            searchRoom.setBounds(10, 310, 200, 30);
            searchRoom.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            searchRoom.setForeground(goldColor);
            searchRoom.addActionListener(this);
            searchRoom.setFocusPainted(false);
            searchRoom.setOpaque(false);
            searchRoom.setBorder(roundedBorder);
            searchRoom.setUI(new CustomButtonUI());
            add(searchRoom);

            records = new JButton("Previous Records");
            records.setBounds(10, 350, 200, 30);
            records.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            records.setForeground(goldColor);
            records.addActionListener(this);
            records.setFocusPainted(false);
            records.setOpaque(false);
            records.setBorder(roundedBorder);
            records.setUI(new CustomButtonUI());
            add(records);

            logout = new JButton("Back");
            logout.setBounds(10, 390, 200, 30);
            logout.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            logout.setForeground(goldColor);
            logout.addActionListener(this);
            logout.setFocusPainted(false);
            logout.setOpaque(false);
            logout.setBorder(roundedBorder);
            logout.setUI(new CustomButtonUI());
            add(logout);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/recep.jpg"));
            JLabel image = new JLabel(i1);
            image.setBounds(250, 30, 500, 470);
            add(image);

            setLayout(null);
            setSize(800, 610);
            setLocationRelativeTo(null); // Center the window on the screen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }else{

            Color goldColor = new Color(218, 165, 32);
            Border roundedBorder = new RoundedBorder(goldColor, 3);

            getContentPane().setBackground(Color.WHITE);

            newCustomer = new JButton("New Customer Form");
            newCustomer.setBounds(10, 30, 200, 30);
            newCustomer.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            newCustomer.setForeground(goldColor);
            newCustomer.addActionListener(this);
            newCustomer.setFocusPainted(false);
            newCustomer.setOpaque(false);
            newCustomer.setBorder(roundedBorder);
            newCustomer.setUI(new CustomButtonUI());
            add(newCustomer);

            rooms = new JButton("Rooms");
            rooms.setBounds(10, 70, 200, 30);
            rooms.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            rooms.setForeground(goldColor);
            rooms.addActionListener(this);
            rooms.setFocusPainted(false);
            rooms.setOpaque(false);
            rooms.setBorder(roundedBorder);
            rooms.setUI(new CustomButtonUI());
            add(rooms);


            custermers = new JButton("Customer Info");
            custermers.setBounds(10, 110, 200, 30);
            custermers.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            custermers.setForeground(goldColor);
            custermers.addActionListener(this);
            custermers.setFocusPainted(false);
            custermers.setOpaque(false);
            custermers.setBorder(roundedBorder);
            custermers.setUI(new CustomButtonUI());
            add(custermers);


            checkout = new JButton("Checkout");
            checkout.setBounds(10, 150, 200, 30);
            checkout.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            checkout.setForeground(goldColor);
            checkout.addActionListener(this);
            checkout.setFocusPainted(false);
            checkout.setOpaque(false);
            checkout.setBorder(roundedBorder);
            checkout.setUI(new CustomButtonUI());
            add(checkout);

            status = new JButton("Update Status");
            status.setBounds(10, 190, 200, 30);
            status.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            status.setForeground(goldColor);
            status.addActionListener(this);
            status.setFocusPainted(false);
            status.setOpaque(false);
            status.setBorder(roundedBorder);
            status.setUI(new CustomButtonUI());
            add(status);

            roomStatus = new JButton("Update Room Status");
            roomStatus.setBounds(10, 230, 200, 30);
            roomStatus.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            roomStatus.setForeground(goldColor);
            roomStatus.addActionListener(this);
            roomStatus.setFocusPainted(false);
            roomStatus.setOpaque(false);
            roomStatus.setBorder(roundedBorder);
            roomStatus.setUI(new CustomButtonUI());
            add(roomStatus);


            searchRoom = new JButton("Search Room");
            searchRoom.setBounds(10, 270, 200, 30);
            searchRoom.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            searchRoom.setForeground(goldColor);
            searchRoom.addActionListener(this);
            searchRoom.setFocusPainted(false);
            searchRoom.setOpaque(false);
            searchRoom.setBorder(roundedBorder);
            searchRoom.setUI(new CustomButtonUI());
            add(searchRoom);

            records = new JButton("Previous Records");
            records.setBounds(10, 310, 200, 30);
            records.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            records.setForeground(goldColor);
            records.addActionListener(this);
            records.setFocusPainted(false);
            records.setOpaque(false);
            records.setBorder(roundedBorder);
            records.setUI(new CustomButtonUI());
            add(records);

            logout = new JButton("Back");
            logout.setBounds(10, 350, 200, 30);
            logout.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
            logout.setForeground(goldColor);
            logout.addActionListener(this);
            logout.setFocusPainted(false);
            logout.setOpaque(false);
            logout.setBorder(roundedBorder);
            logout.setUI(new CustomButtonUI());
            add(logout);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/recep.jpg"));
            JLabel image = new JLabel(i1);
            image.setBounds(250, 30, 500, 470);
            add(image);

            setLayout(null);
            setSize(800, 610);
            setLocationRelativeTo(null); // Center the window on the screen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newCustomer) {
            setVisible(false);
            new CustumerForm();
        } else if (e.getSource() == rooms) {
            setVisible(false);
            new Room();
        } else if (e.getSource() == allEmployee) {
            setVisible(false);
            new Employee();
        }  else if (e.getSource() == custermers) {
            setVisible(false);
            new CustumerInfo();
        }
        else if (e.getSource() == searchRoom) {
            setVisible(false);
            new SearchRoom();
        } else if (e.getSource() == status) {
            setVisible(false);
            new UpdateCheck();
        } else if (e.getSource() == roomStatus) {
            setVisible(false);
            new UpdateRoom();
        } else if (e.getSource() == checkout) {
            setVisible(false);
            new CheckOut();
        } else if (e.getSource() == records) {
            setVisible(false);
            new Records();
        } else if (e.getSource() == logout) {
            setVisible(false);
//            System.exit(0);
            new DashBoard(UserSession.isManager);
        }
    }

    public static void main(String[] args){
        new Reception(UserSession.isManager);
    }
}
