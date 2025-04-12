// Abstraction
// It is used to hide the complexity of the system and only showing the essential functionality or features to the user. 

abstract class Animal {
    // Abstract method - must be implemented by subclasses
    public abstract void makeSound();

    // Concrete method - inherited by subclasses
    public void eat() {
        System.out.println("Animal is eating.");
    }

    // Another concrete method that can be overridden
    public void sleep() {
        System.out.println("Animal is sleeping.");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is snoring."); // Overriding the sleep method
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.makeSound(); // Calls the overridden abstract method
        myDog.eat(); // Calls the inherited concrete method
        myDog.sleep(); // Calls the overridden concrete method

        Cat myCat = new Cat();
        myCat.makeSound(); // Calls the overridden abstract method
        myCat.eat(); // Calls the inherited concrete method
        myCat.sleep(); // Calls the inherited concrete method
    }
}

// An abstract class can have both abstract and non abstract methods, this is
// not the same for interface.
// An object of the abstract class cannot be made.
// Implementation of abstract functions should be defined in the funtions of
// child class inheriting, else it will give a compile time error