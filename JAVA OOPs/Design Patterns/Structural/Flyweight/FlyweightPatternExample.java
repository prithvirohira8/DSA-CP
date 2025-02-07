// Flyweight Design Pattern:
// - reuse objects instead of creating new instances again, setting/changing the values of the instances instead of creating new instances.

public class FlyweightPatternExample {
    public static void main(String[] args) {
        Car car1 = CarFactory.getCar("Red", "Toyota", "Corolla");
        car1.assemble("CHS1234");

        Car car2 = CarFactory.getCar("Red", "Toyota", "Corolla");
        car2.assemble("CHS5678");

        Car car3 = CarFactory.getCar("Blue", "Honda", "Civic");
        car3.assemble("CHS9876");

        Car car4 = CarFactory.getCar("Red", "Toyota", "Corolla");
        car4.assemble("CHS4321");
    }
}

// Output:
// Creating new shared car instance: Red_Toyota_Corolla
// Assembling car -> Color: Red, Brand: Toyota, Model: Corolla, Chassis Number:
// CHS1234

// Reusing existing car instance: Red_Toyota_Corolla
// Assembling car -> Color: Red, Brand: Toyota, Model: Corolla, Chassis Number:
// CHS5678

// Creating new shared car instance: Blue_Honda_Civic
// Assembling car -> Color: Blue, Brand: Honda, Model: Civic, Chassis Number:
// CHS9876

// Reusing existing car instance: Red_Toyota_Corolla
// Assembling car -> Color: Red, Brand: Toyota, Model: Corolla, Chassis Number:
// CHS4321

// When to Use Flyweight Pattern?

// When many objects share common data.
// When memory usage is a concern.
// When object creation is expensive in terms of memory and performance.
