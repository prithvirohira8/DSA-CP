public class FactoryPattern {
    public static void main(String[] args) {
        // Create a Car using the Factory
        Vehicle myCar = VehicleFactory.getVehicle("car");
        myCar.drive(); // Output: Driving a car...

        // Create a Bike using the Factory
        Vehicle myBike = VehicleFactory.getVehicle("bike");
        myBike.drive(); // Output: Riding a bike...

        // Uncommenting below will throw an exception
        // Vehicle unknown = VehicleFactory.getVehicle("truck");
    }
}

// Advantages of the Factory Pattern

// ✔ Encapsulation – The object creation logic is hidden inside the factory.
// ✔ Flexibility – Easily extendable by adding new vehicle types without
// modifying existing client code.
// ✔ Code Reusability – The factory ensures centralized object creation,
// reducing redundancy.

// 🚀 When to Avoid Factory Pattern?
// When object creation is simple and does not require logic abstraction.
// If the number of classes is small and doesn’t justify an extra factory class.