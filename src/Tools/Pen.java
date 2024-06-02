package Tools;

import java.awt.*;
import java.awt.geom.Line2D;

public class Pen extends DrawComponent {

    private static int oldX,oldY = 0;
    private Line2D.Float line;

    public Pen(int x, int y, Color c) {
        oldX = x;
        oldY = y;
        color = c;
        line = new Line2D.Float(oldX,oldY,x,y);
    }

    public Pen(float x, float y,float x1,float y1,Color c) {
        line = new Line2D.Float(x,y,x1,y1);
        setColor(c);
    }


    @Override
    public Shape paint() {
        return line;
    }

    @Override
    public Shape getShape() {
        return line;
    }

    @Override
    public String toString() {
        return "p "+line.getX1()+' '+line.getY1()+' '+line.getX2()+' '+line.getY2()+' '+super.color.getRGB()+'\n';
    }
}
