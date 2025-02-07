public class Engine {
    private String type;
    private int horsepower;

    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "Engine[type=" + type + ", horsepower=" + horsepower + "]";
    }
}
