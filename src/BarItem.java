import javax.swing.*;
import java.io.File;

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
                case "Open":
                {
                    openFile();
                    break;
                }
            }
        });
    }

    private void openFile()
    {
        System.out.println("Opening file...");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        System.out.println(file.getAbsolutePath());
    }
}
