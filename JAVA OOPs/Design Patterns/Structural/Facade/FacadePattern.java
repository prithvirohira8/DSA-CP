// Facade Design pattern:
// - It consists a single class that represents an entire subsystem.

public class FacadePattern {
    public static void main(String[] args) {
        // Create subsystem components
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Lights lights = new Lights();

        // Create Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvdPlayer, projector, soundSystem, lights);

        // Use the simplified interface
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}

// When to Use the Facade Pattern?

// When you need a simplified interface to a complex system.
// When working with subsystems that have multiple dependencies.

// Advantages of the Facade Pattern

// ✔ Simplifies complex systems – Hides internal details and provides an
// easy-to-use interface.
// ✔ Decouples client code – The client interacts only with the Facade, not
// individual subsystems.
// ✔ Improves maintainability – Changes to subsystems don't affect the client.
// ✔ Encapsulates subsystem complexity – Avoids exposing unnecessary details to
// the client.