import java.util.ArrayList;
import java.util.List;

// Concrete Subject (Observable)
public class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    // Method to update temperature and notify observers
    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

// This WeatherStation class: ✅ Stores a list of observers.
// ✅ Notifies all observers when the temperature changes.
// ✅ Allows observers to register or unregister.