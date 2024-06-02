package Tools;

public enum Type {
    SAVE("Save"),
    SAVEAS("Save As..."),
    OPEN("Open"),
    QUIT("Quit"),
    CLEAR("Clear"),
    COLOR("Color"),
    UNDO("Undo"),
    REDO("Redo");

    public final String label;

    Type(String label) {
        this.label = label;
    }

    public String getTypeName() {
        return label;
    }
}
