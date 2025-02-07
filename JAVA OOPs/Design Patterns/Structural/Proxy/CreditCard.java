public class CreditCard implements Bank {
    private RealBank realBank;
    private String accountNumber;

    public CreditCard(String accountNumber) {
        this.accountNumber = accountNumber;
        this.realBank = new RealBank(); // Initialize the real bank
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        // Add extra behavior: Security check
        if (!this.accountNumber.equals(accountNumber)) {
            System.out.println("Unauthorized access attempt detected!");
            return;
        }

        // Add extra behavior: Transaction logging
        System.out.println("[Proxy] Logging transaction: $" + amount + " from " + accountNumber);

        // Forward request to RealBank
        realBank.withdraw(accountNumber, amount);
    }
}
