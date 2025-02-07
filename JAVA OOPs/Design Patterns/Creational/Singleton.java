import java.util.*;

class Singleton_Design {
    private static Singleton_Design instance;

    private Singleton_Design() {
        System.out.println("Singleton is instantiated");
    }

    public static Singleton_Design getInstance() {
        if (instance == null) {
            instance = new Singleton_Design();
        }
        return instance;
    }

    public static void doSomething() {
        System.out.println("Somethong is Done.");
    }

}

public class Singleton {
    public static void main(String h[]) {
        Singleton_Design.getInstance().doSomething();
    }
}

// Output:
// Singleton is instantiated
// Somethong is Done.

// Key Points:
// The goal: An instance can only be created once
// Constructor is always private and so is instance variable to avoid others to
// get access.
// The methods are always public and static to ensure global access.
// The instance variable is made static to ensure that the single instance is
// associated with the class itself
// and not with any specific object of the class