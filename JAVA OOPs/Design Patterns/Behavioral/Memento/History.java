class History {
    private ArrayList<TextEditorMemento> savedStates = new ArrayList<>();

    public void push(TextEditorMemento state) {
        savedStates.add(state);
    }

    public TextEditorMemento pop() {
        if (savedStates.isEmpty()) {
            return null;
        }
        TextEditorMemento lastState = savedStates.get(savedStates.size() - 1);
        savedStates.remove(savedStates.size() - 1);
        return lastState;
    }
}