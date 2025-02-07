public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Create WeatherStation (Observable)
        WeatherStation weatherStation = new WeatherStation();

        // Create Observers (Display Screens)
        PhoneDisplay phone1 = new PhoneDisplay("Phone 1");
        PhoneDisplay phone2 = new PhoneDisplay("Phone 2");
        TVDisplay tvDisplay = new TVDisplay();

        // Register observers to the WeatherStation
        weatherStation.registerObserver(phone1);
        weatherStation.registerObserver(phone2);
        weatherStation.registerObserver(tvDisplay);

        // Update temperature and notify observers
        System.out.println("Setting temperature to 25°C...");
        weatherStation.setTemperature(25);

        System.out.println("\nRemoving Phone 2 from updates...");
        weatherStation.removeObserver(phone2);

        System.out.println("\nSetting temperature to 30°C...");
        weatherStation.setTemperature(30);
    }
}

// Output:
// Setting temperature to 25°C...
// Phone 1 Display: Temperature updated to 25.0°C
// Phone 2 Display: Temperature updated to 25.0°C
// TV Display: Temperature updated to 25.0°C

// Removing Phone 2 from updates...

// Setting temperature to 30°C...
// Phone 1 Display: Temperature updated to 30.0°C
// TV Display: Temperature updated to 30.0°C

// Key Takeaways

// 1️⃣ Subject (WeatherStation) → Keeps track of observers and notifies them of
// changes.
// 2️⃣ Observer Interface (Observer) → Defines how observers get notified.
// 3️⃣ Concrete Observers (PhoneDisplay, TVDisplay) → Implement the Observer
// interface to react to updates.
// 4️⃣ Loose Coupling → The subject and observers communicate via an interface,
// making it easy to extend.

// When to Use the Observer Pattern?

// ✔ When multiple objects need to react to changes in another object
// automatically.
