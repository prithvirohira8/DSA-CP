// The Proxy Design Pattern is a structural design pattern that provides a surrogate or placeholder for another object. It is often used to control access, add additional behavior, or optimize performance.

public class ProxyPattern {
    public static void main(String[] args) {
        Bank myCreditCard = new CreditCard("12345-67890");

        // Valid transaction
        myCreditCard.withdraw("12345-67890", 500.00);

        System.out.println();

        // Invalid transaction (Unauthorized)
        myCreditCard.withdraw("98765-43210", 300.00);
    }
}
// Advantages of Proxy Design Pattern

// ✔ Access Control – Prevents unauthorized access to the bank.
// ✔ Logging & Monitoring – Can track transactions before forwarding them.
// ✔ Performance Optimization – Avoids direct interaction with a heavy object
// unless needed.
// ✔ Lazy Initialization – Only creates a RealBank instance when required.

// When to Use Proxy Pattern?

// Security & Access Control – When an object should be accessible only under
// specific conditions.
// Logging & Auditing – To track actions before reaching the real subject.
// Lazy Initialization – When creating an expensive object should be delayed
// until necessary.
// Virtual Proxy – To represent a large object that should be created only when
// required.
