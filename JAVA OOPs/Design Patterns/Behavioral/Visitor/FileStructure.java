import java.util.ArrayList;
import java.util.List;

// Object structure: Manages files and applies visitors
public class FileStructure {
    private List<FileElement> files = new ArrayList<>();

    public void addFile(FileElement file) {
        files.add(file);
    }

    public void applyVisitor(FileVisitor visitor) {
        for (FileElement file : files) {
            file.accept(visitor);
        }
    }
}
