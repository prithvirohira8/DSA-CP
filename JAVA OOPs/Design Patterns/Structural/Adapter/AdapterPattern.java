public class AdapterPattern {
    public static void main(String[] args) {
        // Existing charger with a three-pin plug
        ThreePinPlug oldCharger = new ThreePinPlug();

        // Using an adapter to charge via USB-C
        USBTypeCCharger adapter = new ThreePintoUSBAdapter(oldCharger);
        adapter.chargeWithUSBTypeC();
    }
}

// Output:
// Adapter converts Three-Pin Plug to USB Type-C.
// Charging using a three-pin plug.

// Simply override the method defined in the interface with the logic for
// conversion.

// 1️⃣ Target Interface (USBTypeCCharger) → Represents the expected interface.
// 2️⃣ Adaptee (ThreePinPlug) → Existing incompatible interface.
// 3️⃣ Adapter (ThreePinToUSBAdapter) → Converts one interface to another.
// 4️⃣ Client (AdapterPatternExample) → Uses the adapter to make two
// incompatible systems work together.