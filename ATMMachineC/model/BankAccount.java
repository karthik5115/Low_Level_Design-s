package model;

public class BankAccount {
    private String AccountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String AccountNumber, String ownerName, double balance) {
        this.AccountNumber = AccountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". New balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount.");
            return false;
        }
    }

    public void accountDetails() {
        System.out.println("AccountNumber: " + AccountNumber);
        System.out.println("Owner Name: " + ownerName);
    }
}
