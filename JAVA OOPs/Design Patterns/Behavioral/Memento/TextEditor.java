// The Originator class represents the object whose state we want to save
class TextEditor {
    private String content;

    public void write(String text) {
        content = text;
    }

    public String getContent() {
        return content;
    }

    // Creates a Memento containing the current state
    public TextEditorMemento save() {
        return new TextEditorMemento(content);
    }

    // Restores state from a Memento
    public void restore(TextEditorMemento memento) {
        content = memento.getSavedContent();
    }
}