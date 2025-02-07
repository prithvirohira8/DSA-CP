// Colleague Class
abstract class Aircraft {
    protected AirTrafficControl atc;
    protected String identifier;

    public Aircraft(AirTrafficControl atc, String identifier) {
        this.atc = atc;
        this.identifier = identifier;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message, Aircraft sender);
}
