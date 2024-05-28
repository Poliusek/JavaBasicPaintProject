import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Main {
    static JLabel tool;
    public static void main(String[] args) {
        Window window = new Window();

        JMenuBar p = new JMenuBar();

        JMenu jMenu = new JMenu("File");
        //Open
        jMenu.add(new BarItem("Open", KeyEvent.VK_O, KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        //Save
        jMenu.add(new BarItem("Save", KeyEvent.VK_S, KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //Save as
        jMenu.add(new BarItem("Save As...",KeyEvent.VK_A, KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        jMenu.addSeparator();
        //Quit
        jMenu.add(new BarItem("Quit",KeyEvent.VK_Q, KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        p.add(jMenu);


        JMenu jMenu1 = new JMenu("Draw");
        JMenu jMenu1SubMenu = new JMenu("Figure");
        //Submenu
        jMenu1SubMenu.setMnemonic(KeyEvent.VK_F);

        MenuRadialButton circle = new MenuRadialButton("Circle", KeyEvent.VK_C, KeyEvent.VK_C, ActionEvent.CTRL_MASK);
        MenuRadialButton square = new MenuRadialButton("Square", KeyEvent.VK_R, KeyEvent.VK_R, ActionEvent.CTRL_MASK);
        MenuRadialButton pen = new MenuRadialButton("Pen", -1, KeyEvent.VK_E, ActionEvent.CTRL_MASK);
        pen.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(circle);
        bg.add(square);
        bg.add(pen);
        jMenu1SubMenu.add(circle);
        jMenu1SubMenu.add(square);
        jMenu1SubMenu.add(pen);
        jMenu1.add(jMenu1SubMenu);
        //Color
        jMenu1.add(new BarItem("Color",KeyEvent.VK_C, KeyEvent.VK_C, ActionEvent.ALT_MASK+ActionEvent.SHIFT_MASK));
        jMenu1.addSeparator();
        //Clear
        jMenu1.add(new BarItem("Clear",KeyEvent.VK_C, KeyEvent.VK_N, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        p.add(jMenu1);

        JToolBar p1 = new JToolBar();
        p1.setLayout(new BorderLayout());
        tool = new JLabel(MenuRadialButton.selectedToolName);

        JLabel state = new JLabel("Saved");
        p1.add(state,BorderLayout.EAST);
        p1.add(tool,BorderLayout.WEST);

        DrawBoard d = DrawBoard.getDrawBoard();


        window.add(p, BorderLayout.NORTH);
        window.add(p1, BorderLayout.SOUTH);
        window.add(d, BorderLayout.CENTER);

        window.pack();
        window.setVisible(true);
    }
}