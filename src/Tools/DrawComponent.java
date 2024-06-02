package Tools;

import java.awt.*;
import java.util.Random;

public abstract class DrawComponent {
    protected Color color = DrawComponent.randomColor();
    abstract public Shape paint();
    abstract public Shape getShape();

    public static Color randomColor()
    {
        Random random  = new Random();
        return new Color(random.nextInt());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
