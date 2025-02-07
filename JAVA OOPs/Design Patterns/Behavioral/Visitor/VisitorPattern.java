public class VisitorPattern {
    public static void main(String[] args) {
        // Create files
        TextFile textFile1 = new TextFile("document.txt");
        ImageFile imageFile1 = new ImageFile("photo.jpg");

        // Add files to the structure
        FileStructure fileStructure = new FileStructure();
        fileStructure.addFile(textFile1);
        fileStructure.addFile(imageFile1);

        // Create visitors
        FileVisitor compressionVisitor = new CompressionVisitor();
        FileVisitor encryptionVisitor = new EncryptionVisitor();

        // Apply Compression Visitor
        System.out.println("Applying Compression:");
        fileStructure.applyVisitor(compressionVisitor);

        // Apply Encryption Visitor
        System.out.println("\nApplying Encryption:");
        fileStructure.applyVisitor(encryptionVisitor);
    }
}

// Output:
// Applying Compression:
// Compressing text file: document.txt
// Compressing image file: photo.jpg

// Applying Encryption:
// Encrypting text file: document.txt
// Encrypting image file: photo.jpg

// Key Takeaways

// 1️⃣ Decouples Operations → We can add new operations (CompressionVisitor,
// EncryptionVisitor) without modifying TextFile or ImageFile.
// 2️⃣ Extensibility → Adding a new operation is as simple as creating a new
// visitor.
// 3️⃣ Encapsulation of Behavior → The logic for operations is encapsulated in
// visitors rather than the elements.
// 4️⃣ Use Case → Best for scenarios where you need to apply multiple operations
// on a set of objects without modifying their classes.

// When to Use the Visitor Pattern?

// ✔ When you need to add new operations to existing classes without modifying
// them.
// ✔ When multiple operations need to be performed on a group of related
// objects.
// ✔ When you want to keep the operations separate from the data structure.