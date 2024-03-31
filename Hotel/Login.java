package HotalManagementSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.ResultSet;
import com.formdev.flatlaf.FlatLightLaf;

public class Login extends JFrame implements ActionListener {
    private JTextField username;
    private JPasswordField password;
    private JButton login, cancel, signup;


    Login() {
        setTitle("login ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(615, 300));
        setLayout(null);

        FlatLightLaf.install();

        getContentPane().setBackground(Color.white);

        Color goldColor = new Color(218, 165, 32);

        Border roundedBorder = new RoundedBorder(goldColor, 3);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        username.setBackground(Color.WHITE);
        username.setForeground(goldColor);
        username.setBorder(roundedBorder);
        add(username);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        password.setBackground(Color.WHITE);
        password.setForeground(goldColor);
        password.setBorder(roundedBorder);
        add(password);

        JLabel user = new JLabel("Username", SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        user.setFont(new Font("Century Schoolbook", Font.BOLD, 17));
        user.setForeground(goldColor);
        user.setBackground(Color.BLACK);
        user.setOpaque(false);  // Make it non-opaque
        user.setBorder(roundedBorder);
        user.setBounds(40, 20, 95, 30);
        add(user);

        JLabel pass = new JLabel("Password", SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        pass.setFont(new Font("Century Schoolbook", Font.BOLD, 17));
        pass.setForeground(goldColor);
        pass.setBackground(Color.BLACK);
        pass.setOpaque(false);  // Make it non-opaque
        pass.setBorder(roundedBorder);
        pass.setBounds(40, 70, 95, 30);
        add(pass);

        login = new JButton("Login");
        login.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        login.setForeground(goldColor);
        login.addActionListener(this);
        login.setFocusPainted(false);
        login.setOpaque(false);  // Make it non-opaque
        login.setBorder(roundedBorder);
        login.setBounds(40, 150, 120, 30);
        login.setUI(new CustomButtonUI());
        add(login);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        cancel.setForeground(goldColor);
        cancel.addActionListener(this);
        cancel.setFocusPainted(false);
        cancel.setOpaque(false);  // Make it non-opaque
        cancel.setBorder(roundedBorder);
        cancel.setUI(new CustomButtonUI());
        cancel.setBounds(210, 150, 120, 30);
        add(cancel);

        signup = new JButton("Sign Up");
        signup.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        signup.setForeground(goldColor);
        signup.addActionListener(this);
        signup.setFocusPainted(false);
        signup.setOpaque(false);  // Make it non-opaque
        signup.setBorder(roundedBorder);
        signup.setBounds(125, 200, 120, 30);
        signup.setUI(new CustomButtonUI());
        add(signup);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            new Login();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String user = username.getText();
            char[] passChar = password.getPassword();
            String pass = new String(passChar);

            try {
                Connector c = new Connector();

                String query = "SELECT * FROM login WHERE username = '" + user + "' AND password = '" + pass + "'";
                ResultSet rs = c.s.executeQuery(query);



                if (rs.next()) {
                    UserSession.isManager = rs.getBoolean("is_manager");
                    setVisible(false);
                    new DashBoard(UserSession.isManager);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    // setVisible(false);
                }

            } catch (Exception ae) {

            }
        } else if (e.getSource() == signup) {

            setVisible(false);
            new Checkmanager();
        } else if (e.getSource() == cancel) {
            setVisible(false);
            System.exit(0);
        }

    }
}
