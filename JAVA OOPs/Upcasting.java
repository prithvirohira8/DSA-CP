class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Upcasting {
    public static void main(String[] args) {
        Animal a; // Superclass reference
        a = new Dog(); // Upcasting
        a.sound(); // Outputs: Dog barks

        a = new Cat(); // Upcasting
        a.sound(); // Outputs: Cat meows
    }
}

// The primary advantage of upcasting in Java lies in its ability to provide
// flexibility, extensibility, and polymorphism. By treating a subclass object
// as an instance of its superclass, Java enables dynamic method dispatch and a
// more generic way to handle objects.
// In the example, the sound() method is dynamically dispatched at runtime based
// on the actual object type (Dog or Cat).