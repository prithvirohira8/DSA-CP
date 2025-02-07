public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.50; // Extra cost for Sugar
    }
}

// These classes add new functionalities (Milk and Sugar) without modifying the
// PlainCoffee class.