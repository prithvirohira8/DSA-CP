// State Interface
interface VendingMachineState {
    void insertCoin(VendingMachine machine);

    void selectItem(VendingMachine machine);

    void dispenseItem(VendingMachine machine);
}