// Concrete State 1: No coin inserted
class NoCoinInsertedState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted. You can now select an item.");
        machine.setState(new HasCoinState()); // Transition to the HasCoin state
    }

    @Override
    public void selectItem(VendingMachine machine) {
        System.out.println("Please insert a coin first.");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("No coin inserted. Cannot dispense item.");
    }
}

// Concrete State 2: Coin inserted, waiting for item selection
class HasCoinState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("You have already inserted a coin.");
    }

    @Override
    public void selectItem(VendingMachine machine) {
        System.out.println("Item selected. Dispensing...");
        machine.setState(new DispensingState()); // Transition to Dispensing state
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Please select an item first.");
    }
}

// Concrete State 3: Dispensing item
class DispensingState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Please wait, we are dispensing your item.");
    }

    @Override
    public void selectItem(VendingMachine machine) {
        System.out.println("Already processing a request.");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Item dispensed. Returning to waiting state.");
        machine.setState(new NoCoinInsertedState()); // Reset back to initial state
    }
}
