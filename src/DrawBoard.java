import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Tools.*;

public class DrawBoard extends JPanel implements KeyListener {
    private boolean shouldDelete = false;
    private List<DrawComponent> shapeList;
    private List<DrawComponent> removedElements;

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
        shapeList = new ArrayList<>();
        removedElements = new ArrayList<>();
        setPreferredSize(new Dimension(1000,800));

        MouseInnerHandler h = new MouseInnerHandler();

        this.addMouseMotionListener(h);
        this.addMouseListener(h);
    }

    public static void removeLast() {
        if(getDrawBoard().shapeList.isEmpty())
            return;
        int lastElement = getDrawBoard().shapeList.size()-1;
        getDrawBoard().removedElements.add(getDrawBoard().shapeList.get(lastElement));
        getDrawBoard().shapeList.remove(lastElement);
        getDrawBoard().repaint();
    }

    public static void reAddLast()
    {
        if(getDrawBoard().removedElements.isEmpty())
            return;
        int lastElement = getDrawBoard().removedElements.size()-1;
        getDrawBoard().shapeList.add(getDrawBoard().removedElements.get(lastElement));
        getDrawBoard().removedElements.remove(lastElement);
        getDrawBoard().repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        for (DrawComponent s : shapeList)
        {
            if(s.getColor() != null)
                g2.setColor(s.getColor());
            g2.draw(s.paint());
            g2.fill(s.paint());
        }
    }

    public static void clear() {
        singleton.shapeList.clear();
        singleton.repaint();
    }

    private class MouseInnerHandler extends MouseAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!MenuRadialButton.selectedToolName.equals("Pen"))
                return;
            super.mouseDragged(e);
            addPenStroke(e);
            singleton.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (shouldDelete)
            {
                for (Iterator i = shapeList.iterator(); i.hasNext(); ) {
                    DrawComponent c = (DrawComponent)i.next();
                    if(c.getShape().contains(e.getPoint()))
                        new PopUp("Usuwanie", "Usunąć?", new String[]{"Yes", "No"}, c);
                }
                return;
            }
            if (!MenuRadialButton.selectedToolName.equals("Pen"))
                return;
            addPenStroke(e);
            singleton.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D)
            shouldDelete = true;
        if(e.getKeyCode() == KeyEvent.VK_F1) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(point, this);
            switch (MenuRadialButton.selectedToolName)
            {
                case "Circle":
                {
                    shapeList.add(new Tools.CircleComponent((int) point.getX(),(int) point.getY(),30,30));
                    break;
                }
                case "Square":
                {
                    shapeList.add(new Tools.RectangleComponent((int) point.getX(),(int) point.getY(),30,30));
                    break;
                }
            }
            ToolBar.state.setText(FileManager.FileState.MODIFIED.getState());
            FileManager.setFs(FileManager.FileState.MODIFIED);
            singleton.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D)
            shouldDelete = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    private void addPenStroke(MouseEvent e)
    {
        shapeList.add(new Pen(e.getX(),e.getY(),color));
        ToolBar.state.setText(FileManager.FileState.MODIFIED.getState());
            FileManager.setFs(FileManager.FileState.MODIFIED);
        singleton.repaint();
    }

    public List<DrawComponent> getShapeList() {
        return shapeList;
    }

    public List<DrawComponent> getRemovedElements() {
        return removedElements;
    }
}


