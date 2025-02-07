// Concrete Observer: TV Display
public class TVDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("TV Display: Temperature updated to " + temperature + "°C");
    }
}

// Each display screen acts as an observer and updates when it gets notified.
