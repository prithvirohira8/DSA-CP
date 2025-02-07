import java.util.ArrayList;
import java.util.List;

// Subject interface that allows observers to register, remove, and get updates
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
