import Tools.CircleComponent;
import Tools.Pen;
import Tools.RectangleComponent;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;

public class FileManager {
    private static File currentFile = null;

    public static File getCurrentFile() {
        return currentFile;
    }

    private static FileState fs = FileState.NEW;

    public static FileState getFs() {
        return fs;
    }

    public static void setFs(FileState fs) {
        FileManager.fs = fs;
    }

    public enum FileState
    {
        SAVED("Saved"),
        NEW("New"),
        MODIFIED("Modified");

        private String state;

        FileState(String saved) {
            this.state = saved;
        }

        public String getState() {
            return state;
        }
    }

    public static void openFile() throws FileNotFoundException {
        System.out.println("Opening file...");
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            DrawBoard.clear();
            br.lines().forEach(e -> {
                String[] line = e.trim().split(" ");
                switch (line[0]){
                    case "r":
                    {
                        DrawBoard.getDrawBoard().getShapeList().add(new RectangleComponent(Float.parseFloat(line[1]),Float.parseFloat(line[2]),Float.parseFloat(line[3]),Float.parseFloat(line[4]),new Color(Integer.parseInt(line[5]))));
                        break;
                    }
                    case "c":
                    {
                        DrawBoard.getDrawBoard().getShapeList().add(new CircleComponent(Float.parseFloat(line[1]),Float.parseFloat(line[2]),Float.parseFloat(line[3]),Float.parseFloat(line[4]),new Color(Integer.parseInt(line[5]))));
                        break;
                    }
                    case "p":
                    {
                        DrawBoard.getDrawBoard().getShapeList().add(new Pen(Float.parseFloat(line[1]),Float.parseFloat(line[2]),Float.parseFloat(line[3]),Float.parseFloat(line[4]),new Color(Integer.parseInt(line[5]))));
                        break;
                    }
                }
            });
            Window.createGui().setTitle("Simple draw: "+file.getName());
            currentFile = file;
            fs = FileState.SAVED;
        }
    }

    public static void saveAsFile() throws IOException {
        System.out.println("Saving as file...");
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogTitle("Save As");
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            FileWriter fw = new FileWriter(chooser.getSelectedFile());
            new FileManager().save(fw);
            fw.close();
        }
    }

    public static void saveFile() throws IOException {
        System.out.println("Saving file...");
        if(currentFile == null)
        {
            saveAsFile();
            return;
        }
        FileWriter fw = new FileWriter(currentFile);
        new FileManager().save(fw);
        fw.close();
    }

    private void save(FileWriter fw)
    {
        DrawBoard.getDrawBoard().getShapeList().forEach(e -> {
            try {
                fw.write(e.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ToolBar.state.setText(FileState.SAVED.getState());
        setFs(FileState.SAVED);
    }
}
