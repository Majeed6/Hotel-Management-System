package HotalManagementSystem;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CustomButtonUI extends BasicButtonUI {
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
        g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 15, 15);
        super.paint(g, c);
    }
}
