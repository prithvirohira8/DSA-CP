// Visitor interface with visit methods for different elements
public interface FileVisitor {
    void visit(TextFile textFile);

    void visit(ImageFile imageFile);
}
