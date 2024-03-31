package HotalManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.Border;

public class Signup extends JFrame implements ActionListener {

    JTextField username;
    JPasswordField password;
    JButton add_new_user, cancel;
    JCheckBox isManager;

    Signup() {

        setTitle("Sign Up ");


        Color goldColor = new Color(218, 165, 32);

        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.white);
        setBounds(450, 270, 600, 300);
        setLayout(null);
        setBackground(Color.white);

        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 110, 30);
        user.setFont(new Font("Century Schoolbook", Font.BOLD, 17));
        add(user);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 110, 30);
        pass.setFont(new Font("Century Schoolbook", Font.BOLD, 17));
        add(pass);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        add_new_user = new JButton("add new user");
        add_new_user.setBounds(40, 150, 140, 30);
        add_new_user.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        add_new_user.setForeground(goldColor);
        add_new_user.addActionListener(this);
        add_new_user.setFocusPainted(false);
        add_new_user.setOpaque(false);  // Make it non-opaque
        add_new_user.setBorder(roundedBorder);
        add_new_user.setUI(new CustomButtonUI());
        add(add_new_user);

        cancel = new JButton("cancel");
        cancel.setBounds(220, 150, 80, 30);
        cancel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        cancel.setForeground(goldColor);
        cancel.addActionListener(this);
        cancel.setFocusPainted(false);
        cancel.setOpaque(false);  // Make it non-opaque
        cancel.setBorder(roundedBorder);
        cancel.setUI(new CustomButtonUI());
        add(cancel);

        isManager = new JCheckBox("Is Manager");
        isManager.setBounds(40, 120, 150, 30);
//        isManager.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        add(isManager);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Signup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add_new_user) {
            String user = username.getText();
            char[] passChar = password.getPassword();
            String pass = new String(passChar);

            // Determine the is_manager value based on the state of the checkbox
            boolean isManagerValue = isManager.isSelected();

            try {
                Connector c = new Connector();

                String query = "INSERT INTO login VALUES('" + user + "', '" + pass + "', " + isManagerValue + ")";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "User added successfully");
                setVisible(false);
                new Login();

            } catch (Exception ae) {
                JOptionPane.showMessageDialog(null, "Failed to add user");
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);
            new Login();
        }
    }
}