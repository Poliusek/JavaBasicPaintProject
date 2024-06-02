import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    static JLabel tool;
    static JLabel state;
    public ToolBar() {
        this.setLayout(new BorderLayout());
        tool = new JLabel(MenuRadialButton.selectedToolName);
        state = new JLabel(FileManager.getFs().getState());
        this.add(state,BorderLayout.EAST);
        this.add(tool,BorderLayout.WEST);
    }
}
