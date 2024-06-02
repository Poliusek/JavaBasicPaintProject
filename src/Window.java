import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window singleton = null;

    private Window()
    {
        this.setSize(1000,1000);
        this.setTitle("Simple Draw");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static Window createGui() {
        if(singleton == null)
        {
            singleton = new Window();
            addElements();
        }
        return singleton;
    }

    private static void addElements()
    {
        TopBar topBar = new TopBar();
        DrawBoard drawBoard = DrawBoard.getDrawBoard();
        ToolBar toolBar = new ToolBar();

        singleton.addKeyListener(drawBoard);

        singleton.add(topBar, BorderLayout.NORTH);
        singleton.add(toolBar, BorderLayout.SOUTH);
        singleton.add(drawBoard, BorderLayout.CENTER);

        singleton.pack();
        singleton.setVisible(true);
    }


}
