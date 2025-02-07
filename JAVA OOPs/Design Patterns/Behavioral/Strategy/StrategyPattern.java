public class StrategyPattern {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // User chooses to pay using Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(100);

        // User chooses to pay using PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);

        // User chooses to pay using Bitcoin
        cart.setPaymentStrategy(new BitcoinPayment("1A2b3C4d5E6F"));
        cart.checkout(300);
    }
}

// Output:
// Paid $100 using Credit Card (Card Number: 1234-5678-9876-5432).
// Paid $200 using PayPal (Email: user@example.com).
// Paid $300 using Bitcoin (Wallet Address: 1A2b3C4d5E6F).

// ✅ Encapsulation: Different algorithms are encapsulated within separate
// classes.
// ✅ Interchangeability: You can switch between strategies dynamically.
// ✅ Open/Closed Principle: You can add new strategies without modifying the
// existing context class.
// ✅ Flexibility: The client code (ShoppingCart) is not tightly coupled to any
// specific payment method.