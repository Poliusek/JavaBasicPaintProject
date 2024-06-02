package Tools;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleComponent extends DrawComponent {
    private Ellipse2D.Float figure;

    public CircleComponent(float x,float y,float width,float height) {
        setColor(randomColor());
        figure = new Ellipse2D.Float(x-(width/2),y-(height/2),width,height);
    }

    public CircleComponent(float x,float y,float width,float height,Color color) {
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
        return "c "+figure.x+' '+figure.y+' '+figure.width+' '+figure.height+' '+super.color.getRGB()+'\n';
    }
}
