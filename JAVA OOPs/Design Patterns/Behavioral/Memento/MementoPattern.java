// Example usage
class MementoPattern {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        // Write some text and save state
        editor.write("First draft");
        history.push(editor.save());

        // Write more text and save state
        editor.write("Second draft");
        history.push(editor.save());

        // Write final text
        editor.write("Final draft");

        // Undo to previous state
        editor.restore(history.pop());
        System.out.println(editor.getContent()); // Outputs: "Second draft"

        // Undo to first state
        editor.restore(history.pop());
        System.out.println(editor.getContent()); // Outputs: "First draft"
    }
}