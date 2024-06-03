import Tools.DrawComponent;

import javax.swing.*;
import java.awt.*;

public class PopUp extends JPopupMenu {
    private DrawComponent ite;
    public PopUp(String name, String label, String[] params, DrawComponent i) {
        ite = i;
        setLabel(name);
        add(new JLabel(label));
        for (String param : params) {
            JMenuItem it = new JMenuItem(param);
            it.setActionCommand(param);
            it.addActionListener(e -> {
                if (e.getActionCommand().equals("Tak"))
                {
                    if(!DrawBoard.getDrawBoard().getShapeList().isEmpty()) {
                        DrawBoard.getDrawBoard().getRemovedElements().add(ite);
                        DrawBoard.getDrawBoard().getShapeList().remove(ite);
                        DrawBoard.getDrawBoard().repaint();
                    }
                }
            });
            add(it);
        }
        Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, DrawBoard.singleton);

        show(DrawBoard.singleton, (int)point.getX(), (int)point.getY());
    }
}
