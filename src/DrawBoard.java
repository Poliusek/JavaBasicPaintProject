import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawBoard extends JPanel {
    private int posX,posY;
    private int oldX=posX,oldY = posY;

    private static Color color = Color.black;

    public static void setColor(Color newColor) {
        color = newColor;
    }

    public static Color getColor() {
        return color;
    }

    static DrawBoard singleton = null;

    public static DrawBoard getDrawBoard()
    {
        if(singleton == null)
            singleton = new DrawBoard();
        return singleton;
    }

    private DrawBoard()
    {
        setPreferredSize(new Dimension(1000,800));
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics2D g = (Graphics2D)getGraphics();
                g.setPaintMode();
                g.setColor(color);
                posX = e.getX();
                posY = e.getY();
                switch (MenuRadialButton.selectedToolName)
                {
                    case "Pen":
                    {
                        oldX = posX;
                        oldY = posY;
                        g.setStroke(new BasicStroke(2));
                        g.drawLine(oldX, oldY, posX, posY);
                        break;
                    }
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Graphics2D g = (Graphics2D)getGraphics();
                g.setPaintMode();
                g.setColor(color);
                posX = e.getX();
                posY = e.getY();
                switch (MenuRadialButton.selectedToolName)
                {
                    case "Pen":
                    {
                        oldX = posX;
                        oldY = posY;
                        g.setStroke(new BasicStroke(2));
                        g.drawLine(oldX, oldY, posX, posY);
                        break;
                    }
                    case "Circle":
                    {
                        g.fillOval(posX-15,posY-15,30,30);
                        break;
                    }
                    case "Square":
                    {
                        g.fillRect(posX-15,posY-15,30,30);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(0,0,500,50);
    }

    public static void clear() {
        singleton.repaint();
    }
}
