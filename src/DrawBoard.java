import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class DrawBoard extends JPanel {
    private int posX,posY;
    private int oldX=posX,oldY = posY;
    private List<Shape> shapeList;

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
        shapeList = new ArrayList<Shape>();
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
                        shapeList.add(new Line2D.Float(oldX, oldY, posX, posY));
                        break;
                    }
                }

                singleton.repaint();
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
                        shapeList.add(new Line2D.Float(oldX, oldY, posX, posY));
                        break;
                    }
                    case "Circle":
                    {
                        shapeList.add(new Ellipse2D.Float(posX-15,posY-15,30,30));
                        break;
                    }
                    case "Square":
                    {
                        shapeList.add(new Rectangle2D.Float(posX-15,posY-15,30,30));
                        break;
                    }
                }

                singleton.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        for (Shape s : shapeList)
        {
            g2.draw(s);
            g2.fill(s);
        }
    }

    public static void clear() {
        singleton.shapeList.clear();
        singleton.repaint();
    }
}
