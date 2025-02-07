// Concrete Element: TextFile
public class TextFile implements FileElement {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this); // Accepting a visitor
    }
}
