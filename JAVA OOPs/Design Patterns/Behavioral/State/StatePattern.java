// The State Pattern is a behavioral design pattern that allows an object to alter its behavior when its internal state changes. It is particularly useful when an object needs to behave differently based on its current state, without using complex if-else or switch statements.

public class StatePattern {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // Insert a coin
        machine.insertCoin();

        // Select an item
        machine.selectItem();

        // Dispense the item
        machine.dispenseItem();

        // Try to dispense again without inserting a coin
        machine.dispenseItem();
    }
}

// Coin inserted. You can now select an item.
// Item selected. Dispensing...
// Item dispensed. Returning to waiting state.
// No coin inserted. Cannot dispense item.

// Key Takeaways
// ✅ Encapsulation: Each state has its own class, reducing complex conditional
// logic.
// ✅ Flexibility: The state can change dynamically without modifying the
// VendingMachine class.
// ✅ Open/Closed Principle: New states can be added without modifying existing
// code.
// ✅ Improves Maintainability: Avoids messy if-else or switch-case statements.