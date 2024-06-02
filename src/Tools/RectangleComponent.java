package Tools;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectangleComponent extends DrawComponent  {
    private Rectangle2D.Float figure;

    public RectangleComponent(float x,float y,float width,float height) {
        setColor(randomColor());
        figure = new Rectangle2D.Float(x-(width/2),y-(height/2),width,height);
    }

    public RectangleComponent(float x,float y,float width,float height,Color color) {
        this(x,y,width,height);
        setColor(color);
    }

    @Override
    public Shape paint(){
        return figure;
    }

    @Override
    public Shape getShape() {
        return figure;
    }

    @Override
    public String toString() {
        return "r "+figure.x+' '+figure.y+' '+figure.width+' '+figure.height+' '+super.color.getRGB()+'\n';
    }
}
