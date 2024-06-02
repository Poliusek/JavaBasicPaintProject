import Tools.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TopBar extends JMenuBar {
    public TopBar() {
        JMenu jMenu = new JMenu("File");
        //Open
        jMenu.add(new BarItem(Type.OPEN, KeyEvent.VK_O, KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        //Save
        jMenu.add(new BarItem(Type.SAVE, KeyEvent.VK_S, KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //Save as
        jMenu.add(new BarItem(Type.SAVEAS,KeyEvent.VK_A, KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        jMenu.addSeparator();
        jMenu.add(new BarItem(Type.UNDO,KeyEvent.VK_U, KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        jMenu.add(new BarItem(Type.REDO,KeyEvent.VK_R, KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        jMenu.addSeparator();
        //Quit
        jMenu.add(new BarItem(Type.QUIT,KeyEvent.VK_Q, KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

        this.add(jMenu);

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
        jMenu1.add(new BarItem(Type.COLOR,KeyEvent.VK_C, KeyEvent.VK_C, ActionEvent.ALT_MASK+ActionEvent.SHIFT_MASK));
        jMenu1.addSeparator();
        //Clear
        jMenu1.add(new BarItem(Type.CLEAR,KeyEvent.VK_C, KeyEvent.VK_N, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));

        this.add(jMenu1);
    }
}
