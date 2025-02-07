// The Decorator Design Pattern is a structural pattern that allows adding new functionalities to an object dynamically without modifying its structure. It follows the principle of Open-Closed Design, 

public class DecoratorPattern {
    public static void main(String[] args) {
        // Start with a plain coffee
        Coffee coffee = new PlainCoffee();
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add Milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add Sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());
    }
}

// Output
// Plain Coffee -> $5.0
// Plain Coffee, Milk -> $6.5
// Plain Coffee, Milk, Sugar -> $7.0

// Advantages of the Decorator Pattern

// ✔ Dynamically adds functionality at runtime.
// ✔ Follows Open-Closed Principle – No modification to existing classes.

// When to Use the Decorator Pattern?

// When subclassing leads to too many subclasses.
// When different combinations of functionalities are needed at runtime.
// When you need multiple independent features (e.g., toppings in a coffee
// shop).