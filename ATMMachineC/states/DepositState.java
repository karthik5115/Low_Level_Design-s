package states;

import java.util.Scanner;
import atm.ATMMachine;
import model.Card;

public class DepositState implements ATMState {
    private ATMMachine atmMachine;

    public DepositState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public Card InsertCard(Card card) {
        System.out.println("Card is already inserted.");
        return card;
    }

    @Override
    public boolean AuthenticatePin(Card card) {
        System.out.println("Card is already authenticated.");
        return true;
    }

    @Override
    public void Deposit(Card card) {
        if (card == null) {
            System.out.println("No card found.");
            atmMachine.setATMState(new IdleState(atmMachine));
            return;
        }

        Scanner scanner = atmMachine.getScanner();
        System.out.print("\nEnter amount to deposit: Rs. ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            card.deposit(amount);
            System.out.println("Deposit successful!");
        }

        System.out.println("\n1. Return to Main Menu");
        System.out.println("2. Cancel & Exit");
        System.out.print("Select an option (1-2): ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            returntoOptions();
        } else {
            Cancel(card);
        }
    }

    @Override
    public void Withdraw(Card card) {
        System.out.println("Currently in Deposit state. Please return to options to withdraw.");
    }

    @Override
    public void SetATMPin(Card card) {
        System.out.println("Cannot set PIN during deposit.");
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("Deposit operation finished / cancelled.");
        ReturnCard(card);
    }

    @Override
    public void OptionstoSelect() {
        returntoOptions();
    }

    @Override
    public void ReturnCard(Card card) {
        System.out.println("Returning card... Please collect your card.");
        atmMachine.setCurrentCard(null);
        atmMachine.setATMState(new IdleState(atmMachine));
    }

    @Override
    public void checkBalance(Card card) {
        System.out.println("Currently in Deposit state. Please return to options to check balance.");
    }

    @Override
    public void returntoOptions() {
        System.out.println("Returning to options menu...");
        ATMState optionsState = new OptionsSelectionState(atmMachine);
        atmMachine.setATMState(optionsState);
        optionsState.OptionstoSelect();
    }

    @Override
    public void cashDispense(double amt) {
        System.out.println("Cannot dispense cash in Deposit state.");
    }
}
