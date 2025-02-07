// Adapter class that converts ThreePinPlug to USBTypeCCharger
public class ThreePintoUSBAdapter implements USBTypeCCharger {
    private final ThreePinPlug threePinPlug;

    public ThreePintoUSBAdapter(ThreePinPlug threePinPlug) {
        this.threePinPlug = threePinPlug;
    }

    @Override
    public void chargeWithUSBTypeC() {
        System.out.println("Adapter converts Three-Pin Plug to USB Type-C.");
        threePinPlug.chargeWithThreePin();
    }
}
