// Mediator Interface
interface AirTrafficControl {
    void registerAircraft(Aircraft aircraft);

    void sendMessage(String message, Aircraft sender);
}
