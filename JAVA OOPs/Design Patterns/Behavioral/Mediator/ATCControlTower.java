import java.util.ArrayList;
import java.util.List;

// Concrete Mediator
class ATCControlTower implements AirTrafficControl {
    private List<Aircraft> aircrafts = new ArrayList<>();

    @Override
    public void registerAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    @Override
    public void sendMessage(String message, Aircraft sender) {
        for (Aircraft aircraft : aircrafts) {
            // The sender should not receive their own message
            if (aircraft != sender) {
                aircraft.receiveMessage(message, sender);
            }
        }
    }
}
