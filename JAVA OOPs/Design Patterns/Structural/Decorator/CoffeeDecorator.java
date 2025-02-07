public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // Wraps another Coffee object

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// This abstract class extends Coffee and delegates method calls to the wrapped
// Coffee instance.