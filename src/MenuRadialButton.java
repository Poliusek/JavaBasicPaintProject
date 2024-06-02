import javax.swing.*;

public class MenuRadialButton extends JRadioButtonMenuItem {
    static String selectedToolName = "Pen";
    MenuRadialButton(String text,int mnemonic, int accelerator, int acceleratormask) {
        this.setText(text);
        this.setMnemonic(mnemonic);
        this.setAccelerator(KeyStroke.getKeyStroke( accelerator, acceleratormask));
        this.addActionListener(e -> {
            selectedToolName = this.getText();
            ToolBar.tool.setText(selectedToolName);
        });
    }
}
