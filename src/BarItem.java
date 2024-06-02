import javax.swing.*;
import java.io.*;

import Tools.*;

public class BarItem extends JMenuItem {
    Tools.Type type;
    BarItem(Tools.Type type, int mnemonic, int accelerator, int acceleratormask){
        this.type = type;
        this.setText(type.getTypeName());
        this.setMnemonic(mnemonic);
        this.setAccelerator(KeyStroke.getKeyStroke( accelerator, acceleratormask));

        this.addActionListener(e -> {
            BarItem b = (BarItem) e.getSource();
            switch (b.getType()){
                case COLOR:
                {
                    DrawBoard.setColor(JColorChooser.showDialog(this, "test",DrawBoard.getColor()));
                    break;
                }
                case CLEAR:
                {
                    DrawBoard.clear();
                    ToolBar.state.setText(FileManager.FileState.MODIFIED.getState());
                    FileManager.setFs(FileManager.FileState.MODIFIED);
                    break;
                }
                case QUIT:
                {
                    if(FileManager.getFs() == FileManager.FileState.NEW || FileManager.getFs() == FileManager.FileState.MODIFIED)
                        if(FileManager.getCurrentFile()==null)
                            try {
                                FileManager.saveAsFile();
                            } catch (IOException ex) { throw new RuntimeException(ex); }
                        else
                            try {
                                FileManager.saveFile();
                            } catch (IOException ex) { throw new RuntimeException(ex); }
                    System.exit(Window.EXIT_ON_CLOSE);
                    break;
                }
                case UNDO:
                {
                    DrawBoard.removeLast();
                    ToolBar.state.setText(FileManager.FileState.MODIFIED.getState());
                    FileManager.setFs(FileManager.FileState.MODIFIED);
                    break;
                }
                case REDO:
                {
                    DrawBoard.reAddLast();
                    ToolBar.state.setText(FileManager.FileState.MODIFIED.getState());
                    FileManager.setFs(FileManager.FileState.MODIFIED);
                    break;
                }
                case OPEN:
                {
                    try {
                        FileManager.openFile();
                    }
                    catch (FileNotFoundException ex) { throw new RuntimeException(ex); }
                    break;
                }
                case SAVE:
                {
                    try {
                        FileManager.saveFile();
                    } catch (IOException ex) { throw new RuntimeException(ex); }
                    break;
                }
                case SAVEAS:
                {
                    try {
                        FileManager.saveAsFile();
                    } catch (IOException ex) { throw new RuntimeException(ex); }
                    break;
                }
            }
        });
    }

    public Type getType() {
        return type;
    }
}
