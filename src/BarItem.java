import javax.swing.*;

public class BarItem extends JMenuItem {
    BarItem(String text,int mnemonic, int accelerator, int acceleratormask){
        this.setText(text);
        this.setMnemonic(mnemonic);
        this.setAccelerator(KeyStroke.getKeyStroke( accelerator, acceleratormask));

        this.addActionListener(e -> {
            switch (e.getActionCommand()){
                case "Color":
                {
                    DrawBoard.setColor(JColorChooser.showDialog(this, "test",DrawBoard.getColor()));
                    break;
                }
                case "Clear":
                {
                    DrawBoard.clear();
                    break;
                }
                case "Quit":
                {
                    System.exit(Window.EXIT_ON_CLOSE);
                    break;
                }
            }
        });
    }
}
