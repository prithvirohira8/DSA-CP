public class BuilderPattern {
    public static void main(String[] args) {
        // Create an Engine object
        Engine v8Engine = new Engine("V8", 400);

        // Create a Car using the builder pattern
        Car car = new Car.CarBuilder()
                .setModel("Sedan")
                .setEngine(v8Engine) // Passing Engine object instead of String
                .setAirConditioned(true)
                .setGPS(true)
                .build();

        System.out.println(car);
    }
}

// Setters are used which mak object instantiation much more easier and
// efficient
// CarBuilder is a static class within Car, that populates the fields of the car
// the constructor of car is private.
// The build() method is called in the end that creates the car object by
// passing the correct reference

// Product: This is the final object that is being constructed. (In this example
// the car)
//