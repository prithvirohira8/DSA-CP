// Encapsulation allows implementation details to be hidden while exposing a public interface for interaction.

import java.util.*;
import java.lang.*;

// Java program demonstrating Encapsulation
class Programmer {
    private String name;

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Encapsulation {
    public static void main(String[] args) {
        Programmer p = new Programmer();
        p.setName("Geek");
        System.out.println("Name=> " + p.getName());
    }
}

// Keeping the variables of a class private is a fundamental aspect of
// encapsulation in object-oriented programming. It ensures that the internal
// state of an object cannot be directly accessed or modified from outside the
// class. Instead, controlled access is provided using getter and setter
// methods.