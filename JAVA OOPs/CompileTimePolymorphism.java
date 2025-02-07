// compile-time polymorphism
// Compile Time Polymorphism: Whenever an object is bound with its functionality at the compile time, this is known as the compile-time polymorphism. At compile-time, java knows which method to call by checking the method signatures. So this is called compile-time polymorphism or static or early binding. Compile-time polymorphism is achieved through method overloading.

import java.util.*;
import java.lang.*;

class Main {
    public static int add(int a, int b) {
        return a + b;
    }

    public static double add(
            double a, double b) {
        return a + b;
    }

    public static void main(String args[]) {
        System.out.println(add(2, 3));
        System.out.println(add(2.0, 3.0));
    }
}