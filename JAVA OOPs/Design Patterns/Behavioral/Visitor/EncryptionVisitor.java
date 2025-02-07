// Concrete Visitor: Encrypts files
public class EncryptionVisitor implements FileVisitor {
    @Override
    public void visit(TextFile textFile) {
        System.out.println("Encrypting text file: " + textFile.getName());
    }

    @Override
    public void visit(ImageFile imageFile) {
        System.out.println("Encrypting image file: " + imageFile.getName());
    }
}
