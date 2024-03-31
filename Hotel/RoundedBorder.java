package HotalManagementSystem;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {
    private Color borderColor;
    private int borderThickness;

    RoundedBorder(Color borderColor, int borderThickness) {
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawRoundRect(x, y, width - 1, height - 1, 15, 15);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int edge = borderThickness / 2;
        return new Insets(edge, edge, edge, edge);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
