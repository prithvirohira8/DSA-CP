import java.util.HashMap;
import java.util.Map;

public class CarFactory {
    private static final Map<String, SharedCar> carCache = new HashMap<>();

    public static Car getCar(String color, String brand, String model) {
        String key = color + "_" + brand + "_" + model;

        if (!carCache.containsKey(key)) {
            System.out.println("Creating new shared car instance: " + key);
            carCache.put(key, new SharedCar(color, brand, model));
        } else {
            System.out.println("Reusing existing car instance: " + key);
        }

        return carCache.get(key);
    }
}
