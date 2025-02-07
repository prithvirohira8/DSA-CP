// Context Class
class VendingMachine {
    private VendingMachineState state;

    public VendingMachine() {
        this.state = new NoCoinInsertedState(); // Initial state
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void insertCoin() {
        state.insertCoin(this);
    }

    public void selectItem() {
        state.selectItem(this);
    }

    public void dispenseItem() {
        state.dispenseItem(this);
    }
}
