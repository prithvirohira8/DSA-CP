
public class VehicleFactory {
    // Factory method to create vehicles
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car2();
        } else if (type.equalsIgnoreCase("bike")) {
            return new Bike();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
