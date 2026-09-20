package model;

import java.util.Date;

public class Card {
    private String cardNO;
    private int pin;
    private String CVV;
    private Date expiryDate;
    private BankAccount account;

    public Card(String cardNO, String CVV, Date expiryDate, BankAccount account) {
        this.cardNO = cardNO;
        this.CVV = CVV;
        this.expiryDate = expiryDate;
        this.account = account;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        System.out.println("pin setted successfully..");
        this.pin = pin;
    }

    public boolean validatePin(int pin) {
        return this.pin == pin;
    }

    public void deposit(double amt) {
        account.deposit(amt);
    }

    public void withdraw(double amt) {
        account.withdraw(amt);
    }

    public void checkBalance() {
        System.out.println("Account Balance: " + account.getBalance());
    }

    public void accountDetails() {
        account.accountDetails();
    }

    public BankAccount getAccount() {
        return this.account;
    }

    public double getBalance() {
        return account.getBalance();
    }

    public String getCardNO() {
        return this.cardNO;
    }
}
