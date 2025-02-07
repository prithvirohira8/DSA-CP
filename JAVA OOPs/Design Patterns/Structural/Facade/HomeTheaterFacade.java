public class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Lights lights;

    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem, Lights lights) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("\n[Facilitating Home Theater Setup to Watch a Movie]\n");

        lights.dim(20);
        projector.turnOn();
        projector.setInputSource("DVD");
        soundSystem.turnOn();
        soundSystem.setVolume(10);
        dvdPlayer.turnOn();
        dvdPlayer.play(movie);

        System.out.println("\nEnjoy your movie!\n");
    }

    public void endMovie() {
        System.out.println("\n[Shutting Down Home Theater]\n");

        dvdPlayer.turnOff();
        soundSystem.turnOff();
        projector.turnOff();
        lights.turnOn();

        System.out.println("\nHome Theater Shutdown Complete!\n");
    }
}
