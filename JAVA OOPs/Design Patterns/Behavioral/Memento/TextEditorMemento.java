// The Memento class stores the state of the TextEditor
class TextEditorMemento {
    private final String content;

    public TextEditorMemento(String contentToSave) {
        content = contentToSave;
    }

    // Only accessible to the originator
    public String getSavedContent() {
        return content;
    }
}