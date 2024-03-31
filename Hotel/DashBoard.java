package HotalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import com.formdev.flatlaf.FlatLightLaf;

public class DashBoard extends JFrame implements ActionListener {
    public boolean isManager;

    public DashBoard() {

    }

    public static void main(String[] args) {
        boolean isManager = true;
        new DashBoard(UserSession.isManager);
    }

    public boolean getisManager() {
        return isManager;
    }



    DashBoard(boolean isManager) {
        if(UserSession.isManager) {
//            this.isManager = isManager;


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(-2, 8, 1350, 600);
            setLayout(null);
            FlatLightLaf.install();

            getContentPane().setBackground(Color.WHITE);

            Color goldColor = new Color(218, 165, 32);
            Border roundedBorder = new RoundedBorder(goldColor, 3);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotel_view1.jpg"));
            Image i2 = i1.getImage().getScaledInstance(1450, 900, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0, 0, 1450, 800);
            add(image);


            JLabel text = new JLabel("CAESAR GROUP WELCOMES YOU");
            text.setFont(new Font("Century Schoolbook", Font.BOLD, 46));
            text.setForeground(goldColor);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setBounds(-70, 0, 1450, 50);
            text.setForeground(new Color(218, 165, 32)); // Set the text color to a bronzy color
            text.setBackground(Color.BLACK); // Set the background color to black
            text.setOpaque(true); // Make the label opaque to show the background color
            text.setFont(new Font("Century Schoolbook", Font.BOLD, 24)); // Decreased font size to 24 for better fit
            //        text.setBounds(10, imageLabel.getHeight() - 50, 329, 40);
            text.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(text);


            JButton hotelButton = new JButton("HOTEL MANAGEMENT");
            hotelButton.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
            hotelButton.setForeground(goldColor);
            hotelButton.setBackground(Color.BLACK);
            hotelButton.setUI(new CustomButtonUI()); // Apply custom button UI
            hotelButton.setOpaque(true); // Make the label opaque to show the background color
            hotelButton.setBounds(100, 300, 320, 50);
            hotelButton.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(hotelButton);

            JButton adminButton = new JButton("MANAGER");
            adminButton.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
            adminButton.setForeground(goldColor);
            adminButton.setBackground(Color.BLACK);
            adminButton.setUI(new CustomButtonUI()); // Apply custom button UI
            adminButton.setBounds(800, 300, 200, 50);
            adminButton.setOpaque(true);
            adminButton.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(adminButton);

            JButton logout = new JButton("LOG OUT");
            logout.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
            logout.setForeground(goldColor);
            logout.setBackground(Color.BLACK);
            logout.setUI(new CustomButtonUI()); // Apply custom button UI
            logout.setBounds(535, 450, 200, 50);
            logout.setOpaque(true);
            logout.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(logout);

            JPopupMenu hotelMenu = new JPopupMenu();
            hotelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            hotelMenu.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
            hotelMenu.setBackground(Color.WHITE);

            JMenuItem receptionItem = new JMenuItem("RECEPTION");
            receptionItem.setForeground(Color.BLUE);
            receptionItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            receptionItem.addActionListener(this);
            hotelMenu.add(receptionItem);

            hotelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hotelMenu.show(hotelButton, 0, hotelButton.getHeight());
                }
            });

            JPopupMenu adminMenu = new JPopupMenu();
            adminMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            adminMenu.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
            adminMenu.setBackground(Color.WHITE);

            JMenuItem addEmployeeItem = new JMenuItem("Add New Employee");
            addEmployeeItem.setForeground(Color.BLUE);
            addEmployeeItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            addEmployeeItem.addActionListener(this);
            adminMenu.add(addEmployeeItem);

            JMenuItem DeleteEmployeeItem = new JMenuItem("Delete Employees");
            DeleteEmployeeItem.setForeground(Color.RED);
            DeleteEmployeeItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            DeleteEmployeeItem.addActionListener(this);
            adminMenu.add(DeleteEmployeeItem);

            JMenuItem UpdateEmployeeItem = new JMenuItem("Update Employees");
            UpdateEmployeeItem.setForeground(Color.GREEN);
            UpdateEmployeeItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            UpdateEmployeeItem.addActionListener(this);
            adminMenu.add(UpdateEmployeeItem);

            JMenuItem addRoomsItem = new JMenuItem("Add New Rooms");
            addRoomsItem.setForeground(Color.BLUE);
            addRoomsItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            addRoomsItem.addActionListener(this);
            adminMenu.add(addRoomsItem);

            JMenuItem DeleteRoomsItem = new JMenuItem("Delete Rooms");
            DeleteRoomsItem.setForeground(Color.RED);
            DeleteRoomsItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            DeleteRoomsItem.addActionListener(this);
            adminMenu.add(DeleteRoomsItem);


        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminMenu.show(adminButton, 0, adminButton.getHeight());

            }
        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == logout) {
                    setVisible(false);
                    new Login();
                }
            }
        });

        // Center the buttons
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        int buttonWidth = 320;
        int buttonHeight = 50;

        int hotelButtonX = centerX - buttonWidth - 50;
        int hotelButtonY = centerY - buttonHeight / 2;

        int adminButtonX = centerX + 50;
        int adminButtonY = centerY - buttonHeight / 2;

        hotelButton.setBounds(hotelButtonX, hotelButtonY, buttonWidth, buttonHeight);
        adminButton.setBounds(adminButtonX, adminButtonY, buttonWidth, buttonHeight);

        setVisible(true);
    }else{
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(-2, 8, 1350, 600);
            setLayout(null);
            FlatLightLaf.install();

            getContentPane().setBackground(Color.WHITE);

            Color goldColor = new Color(218, 165, 32);
            Border roundedBorder = new RoundedBorder(goldColor, 3);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotel_view1.jpg"));
            Image i2 = i1.getImage().getScaledInstance(1450, 900, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0, 0, 1450, 800);
            add(image);


            JLabel text = new JLabel("CAESAR GROUP WELCOMES YOU");
            text.setFont(new Font("Century Schoolbook", Font.BOLD, 46));
            text.setForeground(goldColor);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setBounds(-70, 0, 1450, 50);
            text.setForeground(new Color(218, 165, 32)); // Set the text color to a bronzy color
            text.setBackground(Color.BLACK); // Set the background color to black
            text.setOpaque(true); // Make the label opaque to show the background color
            text.setFont(new Font("Century Schoolbook", Font.BOLD, 24)); // Decreased font size to 24 for better fit
            //        text.setBounds(10, imageLabel.getHeight() - 50, 329, 40);
            text.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(text);


            JButton hotelButton = new JButton("HOTEL MANAGEMENT");
            hotelButton.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
            hotelButton.setForeground(goldColor);
            hotelButton.setBackground(Color.BLACK);
            hotelButton.setUI(new CustomButtonUI()); // Apply custom button UI
            hotelButton.setOpaque(true); // Make the label opaque to show the background color
            hotelButton.setBounds(100, 300, 320, 50);
            hotelButton.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(hotelButton);



            JButton logout = new JButton("LOG OUT");
            logout.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
            logout.setForeground(goldColor);
            logout.setBackground(Color.BLACK);
            logout.setUI(new CustomButtonUI()); // Apply custom button UI
            logout.setBounds(800, 300, 200, 50);
            logout.setOpaque(true);
            logout.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true));
            image.add(logout);

            JPopupMenu hotelMenu = new JPopupMenu();
            hotelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            hotelMenu.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
            hotelMenu.setBackground(Color.WHITE);

            JMenuItem receptionItem = new JMenuItem("RECEPTION");
            receptionItem.setForeground(Color.BLUE);
            receptionItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            receptionItem.addActionListener(this);
            hotelMenu.add(receptionItem);

            hotelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hotelMenu.show(hotelButton, 0, hotelButton.getHeight());
                }
            });

            JPopupMenu adminMenu = new JPopupMenu();
            adminMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            adminMenu.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
            adminMenu.setBackground(Color.WHITE);

            JMenuItem addEmployeeItem = new JMenuItem("Add New Employee");
            addEmployeeItem.setForeground(Color.BLUE);
            addEmployeeItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            addEmployeeItem.addActionListener(this);
            adminMenu.add(addEmployeeItem);
            JMenuItem DeleteEmployeeItem = new JMenuItem("Delete Employees");
            DeleteEmployeeItem.setForeground(Color.RED);
            DeleteEmployeeItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            DeleteEmployeeItem.addActionListener(this);
            adminMenu.add(DeleteEmployeeItem);
            JMenuItem UpdateEmployeeItem = new JMenuItem("Update Employees");
            UpdateEmployeeItem.setForeground(Color.GREEN);
            UpdateEmployeeItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            UpdateEmployeeItem.addActionListener(this);
            adminMenu.add(UpdateEmployeeItem);

            JMenuItem addRoomsItem = new JMenuItem("Add New Rooms");
            addRoomsItem.setForeground(Color.BLUE);
            addRoomsItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            addRoomsItem.addActionListener(this);
            adminMenu.add(addRoomsItem);
            JMenuItem DeleteRoomsItem = new JMenuItem("Delete Rooms");
            DeleteRoomsItem.setForeground(Color.RED);
            DeleteRoomsItem.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
            DeleteRoomsItem.addActionListener(this);
            adminMenu.add(DeleteRoomsItem);


            logout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == logout) {
                          setVisible(false);
                          new Login();
                    }
                }
            });

            // Center the buttons
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = screenSize.width / 2;
            int centerY = screenSize.height / 2;

            int buttonWidth = 320;
            int buttonHeight = 50;

            int hotelButtonX = centerX - buttonWidth - 50;
            int hotelButtonY = centerY - buttonHeight / 2;

            int adminButtonX = centerX + 50;
            int adminButtonY = centerY - buttonHeight / 2;

            hotelButton.setBounds(hotelButtonX, hotelButtonY, buttonWidth, buttonHeight);
            logout.setBounds(adminButtonX, adminButtonY, buttonWidth, buttonHeight);

            setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add New Employee")) {
            new AddEmployee();
        } else if (e.getActionCommand().equals("Add New Rooms")) {
            new AddRooms();
        } else if (e.getActionCommand().equals("RECEPTION")) {
                new Reception(UserSession.isManager);
        } else if (e.getActionCommand().equals("Delete Employees")) {
            new DeleteEmployee();
        }
        else if (e.getActionCommand().equals("Update Employees")) {
            new UpdateEmployee();
        } else if (e.getActionCommand().equals("Delete Rooms")) {
            new DeleteRoom();
        }


    }
}
