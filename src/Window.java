import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Window extends JFrame {
    private static Window singleton = null;

    private Window()
    {
        this.setSize(1000,1000);
        this.setTitle("Simple Draw");
        this.setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    if(FileManager.getCurrentFile() != null)
                        FileManager.saveAsFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(EXIT_ON_CLOSE);
            }
        });
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

        singleton.setJMenuBar(topBar);
        singleton.add(toolBar, BorderLayout.SOUTH);
        singleton.add(drawBoard, BorderLayout.CENTER);

        singleton.pack();
        singleton.setVisible(true);
    }


}
