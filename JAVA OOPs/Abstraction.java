// Abstraction
// It is used to hide the complexity of the system and only showing the essential functionality or features to the user. 

import java.util.*;
import java.lang.*;

abstract class Car {
    abstract void start();
}

class Audi extends Car {
    @Override
    void start() {
        System.out.println("Audi has started");
    }
}

class BMW extends Car {
    @Override
    void start() {
        System.out.println("BMW has started");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Car bmw = new BMW();
        Car audi = new Audi();
        bmw.start();
        audi.start();
    }
}

// An abstract class can have both abstract and non abstract methods, this is
// not the same for interface.
// An object of the abstract class cannot be made.
// Implementation of abstract functions should be defined in the funtions of
// child class inheriting, else it will give a compile time error