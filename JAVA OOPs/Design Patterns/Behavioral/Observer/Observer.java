// Observer interface that will be implemented by all observers
public interface Observer {
    void update(float temperature);
}

// Each observer (e.g., PhoneDisplay, TVDisplay) will implement this interface
// and update its display when notified.