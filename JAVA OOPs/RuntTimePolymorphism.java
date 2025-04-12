// Run-Time Polymorphism: Whenever an object is bound with the functionality at run time, this is known as runtime polymorphism. The runtime polymorphism can be achieved by method overriding. Java virtual machine determines the proper method to call at the runtime, not at the compile time. It is also called dynamic or late binding. Method overriding says the child class has the same method as declared in the parent class. It means if the child class provides the specific implementation of the method that has been provided by one of its parent classes then it is known as method overriding.

import java.util.*;
import java.lang.*;

class Bike {
    void run() {
        System.out.println("Bike is running");
    }
}

class Main extends Bike {
    void run() {
        System.out.println("Running safely at 60");
    }

    public static void main(String args[]) {
        Bike b = new Main();
        b.run();
    }
}

// Output: Running safely at 60
// Bike b = new Main(); is an example of upcasting where a subclass object is
// referred to by a superclass reference.
// Upcasting can only occur when inheritance is involved. If there is no
// inheritance relationship between the classes, upcasting is not possible, even
// if all the classes are public.

// This is also an example if Method Overriding