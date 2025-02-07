// Concrete Observer: Phone Display
public class PhoneDisplay implements Observer {
    private String name;

    public PhoneDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " Display: Temperature updated to " + temperature + "°C");
    }
}
