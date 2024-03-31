package HotalManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicButtonUI;

import com.formdev.flatlaf.FlatLightLaf;

public class Main extends JFrame implements ActionListener {
    private JButton start; // Declare the start button as a class member
    private JLabel text; // Declare the text label as a class member

    Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(615, 638)); // set preferred size of frame
        setLayout(null); // by default layout is null

        FlatLightLaf.install(); // Set the FlatLaf look and feel

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/CAESAR.png")); // added main image
        Image originalImage = i1.getImage();
        Image resizedImage = originalImage.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 600, 600);
        add(imageLabel);

        text = new JLabel("CEASAR HOTEL SYSTEM");
        text.setHorizontalAlignment(SwingConstants.LEFT);
        text.setForeground(new Color(218, 165, 32)); // Set the text color to a bronzy color
        text.setBackground(Color.BLACK); // Set the background color to black
        text.setOpaque(true); // Make the label opaque to show the background color
        text.setFont(new Font("Century Schoolbook", Font.BOLD, 24)); // Decreased font size to 24 for better fit
        text.setBounds(10, imageLabel.getHeight() - 50, 329, 40);
        text.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true)); // Add the same frame as the button
        imageLabel.add(text);

        start = new JButton("START");
        start.addActionListener(this);
        start.setBackground(Color.BLACK); // Set the button background color to black
        start.setForeground(new Color(218, 165, 32)); // Set the text color inside the button to the same bronzy color
        start.setFont(new Font("Century Schoolbook", Font.BOLD, 18)); // Decreased font size to 18 for better fit
        start.setBounds(imageLabel.getWidth() - 150, imageLabel.getHeight() - 60, 120, 40); // Adjusted button position and size
        start.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3, true)); // Add the same frame as the text label
        // Set custom UI for the button to add hover effect
        start.setUI(new CustomButtonUI());
        imageLabel.add(start);

        // Customize the button appearance using the FlatLaf UI defaults
        UIDefaults defaults = UIManager.getDefaults();
        defaults.put("Button.arc", 10); // Rounded corners
        defaults.put("Button.highlight", new Color(218, 0, 0)); // Highlight color on mouse over
        defaults.put("Button.focusWidth", 0); // Remove focus border

        pack(); // pack the components to fit the preferred size
        setLocationRelativeTo(null); // center the frame on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false); // Main menu will disappear
        new Login(); // login page will open
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }

    // Custom ButtonUI class for hover effect
    private static class CustomButtonUI extends BasicButtonUI {
        private Color hoverColor = new Color(50, 50, 50, 150);
        private Color defaultColor = Color.BLACK;

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton button = (AbstractButton) c;
            ButtonModel model = button.getModel();
            if (model.isRollover()) {
                g.setColor(hoverColor);
            } else {
                g.setColor(defaultColor);
            }
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
            super.paint(g, c);
        }
    }
}
