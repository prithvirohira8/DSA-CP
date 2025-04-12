// Interfaces like classes help in creating the blueprint

import java.util.*;
import java.lang.*;

interface Car {
    final String mileage_car = "20km/l";

    void drive();
}

interface Bike {
    final String mileage_bike = "25km/l";

    void ride();
}

class Transport implements Car, Bike {
    @Override
    public void drive() {
        System.out.println("Reaching time is 30 mins");
        System.out.println("Mileage = " + mileage_car);
    }

    @Override
    public void ride() {
        System.out.println("Reaching time is 20 mins");
        System.out.println("Mileage = " + mileage_bike);
    }
}

public class Interface {
    public static void main(String[] args) {
        Transport t = new Transport();
        t.drive();
        t.ride();
    }
}

// Interfaces support multiple inheritance
// All methods in an interfce are by default abstract
// and the class variables declared in a class are by default "public static
// final". This is not the same for abstract classes.
// When an interface is inherited,
// it is compulsory to provide implementation for all its methods. This too is
// not same for abstract classes.

// import java.util.*;
// interface Bank{
// HashMap<int, Account> accounts = new HashMap<>();
// void addAccount(Account acc);
// void removeAccount(Account acc);
// void depositMoney(Account acc, int amount);
// void withdrawMoney(Account acc, int amount);
// }

// class BankManagement implements Bank{
// @Override
// void addAccount(Account acc){
// accounts.put(acc.account_number, acc);
// }

// @Override
// void removeAccount(Account acc){
// accounts.remove(acc.account_number);
// }

// @Override
// void depositMoney(Account acc, int amount){
// acc.balance += amount;
// }

// @Override
// void withdrawMoney(Account acc, int amount){
// acc.balance -= amount;
// }

// void viewAllAccounts(){
// for(Account acc: accounts){
// System.out.println(acc.account_number+" "+acc.balance);
// }
// }
// }

// class Account{
// private int account_number;
// private String name;
// private int balance;

// void setAccountNumber(int account_number){
// this.account_number = account_number;
// }
// void setName(int name){
// this.name = name;
// }
// void setBalance(int balance){
// this.balance = balance;
// }
// }
// class Main {
// public static void main(String[] args) {
// Account a1 = new Account;
// a1.setAccountNumber(100);
// a1.setName("Harsh");
// a1.setBlaance(50000);

// Account a2 = new Account;
// a2.setAccountNumber(200);
// a2.setName("Utkarsh");
// a2.setBlaance(100000);

// BankManagement bm = new BankManagement();
// bm.addAccount(a1);
// bm.addAccount(a2);

// bm.viewAllAccounts();

// bm.depositMoney(a1, 100);
// bm.removeAccount(a2);

// bm.viewAllAccounts();
// }
// }