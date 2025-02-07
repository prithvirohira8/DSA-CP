public class Car {
    private String model;
    private Engine engine; // Change from String to Engine object
    private boolean isAirConditioned;
    private boolean hasGPS;

    // Private constructor to prevent direct instantiation
    private Car(CarBuilder builder) {
        this.model = builder.model;
        this.engine = builder.engine;
        this.isAirConditioned = builder.isAirConditioned;
        this.hasGPS = builder.hasGPS;
    }

    public static class CarBuilder {
        private String model;
        private Engine engine; // Change from String to Engine object
        private boolean isAirConditioned;
        private boolean hasGPS;

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setEngine(Engine engine) { // Accept an Engine object
            this.engine = engine;
            return this;
        }

        public CarBuilder setAirConditioned(boolean isAirConditioned) {
            this.isAirConditioned = isAirConditioned;
            return this;
        }

        public CarBuilder setGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car [model=" + model + ", engine=" + engine + ", airConditioned=" + isAirConditioned + ", hasGPS="
                + hasGPS + "]";
    }
}
