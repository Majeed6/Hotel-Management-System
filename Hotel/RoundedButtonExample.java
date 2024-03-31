package HotalManagementSystem;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class RoundedButtonExample extends JFrame {

    public RoundedButtonExample() {
        setTitle("Rounded Button Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());

        // Create a rounded button
        RoundedButton roundedButton = new RoundedButton("Click me");
        roundedButton.setPreferredSize(new Dimension(120, 40));

        contentPane.add(roundedButton);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RoundedButtonExample example = new RoundedButtonExample();
            example.setVisible(true);
        });
    }
}

class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setBackground(new Color(60, 127, 177));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Apply a rounded shape to the button
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Remove the default border
    }

    @Override
    public boolean contains(int x, int y) {
        // Make the rounded shape clickable
        if (isEnabled()) {
            int width = getWidth();
            int height = getHeight();
            Shape shape = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 20, 20);
            return shape.contains(x, y);
        }
        return false;
    }
}
