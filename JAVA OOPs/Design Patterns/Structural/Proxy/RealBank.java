public class RealBank implements Bank {
    @Override
    public void withdraw(String accountNumber, double amount) {
        System.out.println("Withdrawing $" + amount + " from Bank Account: " + accountNumber);
    }
}
