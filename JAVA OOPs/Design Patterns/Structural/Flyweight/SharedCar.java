public class SharedCar implements Car {
    private final String color;
    private final String brand;
    private final String model;

    public SharedCar(String color, String brand, String model) {
        this.color = color;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public void assemble(String chassisNumber) {
        System.out.println("Assembling car -> Color: " + color + ", Brand: " + brand + ", Model: " + model
                + ", Chassis Number: " + chassisNumber);
    }
}
