// Concrete Aircraft 1: Passenger Plane
class PassengerPlane extends Aircraft {
    public PassengerPlane(AirTrafficControl atc, String identifier) {
        super(atc, identifier);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(identifier + " (Passenger Plane) sends: " + message);
        atc.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message, Aircraft sender) {
        System.out.println(identifier + " (Passenger Plane) receives from " + sender.identifier + ": " + message);
    }
}

// Concrete Aircraft 2: Cargo Plane
class CargoPlane extends Aircraft {
    public CargoPlane(AirTrafficControl atc, String identifier) {
        super(atc, identifier);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(identifier + " (Cargo Plane) sends: " + message);
        atc.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message, Aircraft sender) {
        System.out.println(identifier + " (Cargo Plane) receives from " + sender.identifier + ": " + message);
    }
}
