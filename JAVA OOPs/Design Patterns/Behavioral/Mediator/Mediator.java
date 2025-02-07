
// The Mediator Pattern is a behavioral design pattern that reduces direct communication between objects by centralizing their interactions within a mediator. This promotes loose coupling and simplifies communication between multiple objects.
public class Mediator {
    public static void main(String[] args) {
        AirTrafficControl atc = new ATCControlTower();

        Aircraft plane1 = new PassengerPlane(atc, "Flight 101");
        Aircraft plane2 = new CargoPlane(atc, "Cargo 202");
        Aircraft plane3 = new PassengerPlane(atc, "Flight 303");

        atc.registerAircraft(plane1);
        atc.registerAircraft(plane2);
        atc.registerAircraft(plane3);

        // Airplanes communicate through ATC
        plane1.sendMessage("Requesting landing permission.");
        plane2.sendMessage("Taking off, requesting clearance.");
        plane3.sendMessage("Descending to 10,000 feet.");
    }
}

// Output:
// Flight 101 (Passenger Plane) sends: Requesting landing permission.
// Cargo 202 (Cargo Plane) receives from Flight 101: Requesting landing
// permission.
// Flight 303 (Passenger Plane) receives from Flight 101: Requesting landing
// permission.

// Cargo 202 (Cargo Plane) sends: Taking off, requesting clearance.
// Flight 101 (Passenger Plane) receives from Cargo 202: Taking off, requesting
// clearance.
// Flight 303 (Passenger Plane) receives from Cargo 202: Taking off, requesting
// clearance.

// Flight 303 (Passenger Plane) sends: Descending to 10,000 feet.
// Flight 101 (Passenger Plane) receives from Flight 303: Descending to 10,000
// feet.
// Cargo 202 (Cargo Plane) receives from Flight 303: Descending to 10,000 feet.

// Key Takeaways
// ✅ Loose Coupling: Planes communicate only through the ATC tower, not
// directly.
// ✅ Scalability: Adding new aircraft types doesn’t require modifying existing
// ones.
// ✅ Centralized Communication: The ATC tower acts as a single communication
// hub.
// ✅ Better Maintainability: Reduces dependencies between objects.