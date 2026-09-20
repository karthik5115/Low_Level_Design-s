package states;

import java.util.Scanner;
import atm.ATMMachine;
import model.Card;

public class HasCardState implements ATMState {
    private ATMMachine atmMachine;

    public HasCardState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public Card InsertCard(Card card) {
        System.out.println("A card is already inserted in the ATM.");
        return card;
    }

    @Override
    public boolean AuthenticatePin(Card card) {
        if (card == null) {
            System.out.println("No card found.");
            return false;
        }

        Scanner scanner = atmMachine.getScanner();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        if (card.validatePin(pin)) {
            System.out.println("PIN authenticated successfully.");
            ATMState optionsState = new OptionsSelectionState(atmMachine);
            atmMachine.setATMState(optionsState);
            optionsState.OptionstoSelect();
            return true;
        } else {
            System.out.println("Incorrect PIN! Authentication failed.");
            return false;
        }
    }

    @Override
    public void SetATMPin(Card card) {
        if (card == null) {
            System.out.println("No card found.");
            return;
        }

        Scanner scanner = atmMachine.getScanner();
        System.out.print("Enter new 4-digit PIN to set: ");
        int newPin = scanner.nextInt();
        card.setPin(newPin);
        System.out.println("PIN has been set successfully. Please authenticate PIN to proceed.");
    }

    @Override
    public void Withdraw(Card card) {
        System.out.println("Please authenticate PIN first.");
    }

    @Override
    public void Deposit(Card card) {
        System.out.println("Please authenticate PIN first.");
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("Transaction cancelled.");
        ReturnCard(card);
    }

    @Override
    public void OptionstoSelect() {
        System.out.println("Please authenticate PIN first.");
    }

    @Override
    public void ReturnCard(Card card) {
        System.out.println("Returning card... Please collect your card.");
        atmMachine.setCurrentCard(null);
        atmMachine.setATMState(new IdleState(atmMachine));
    }

    @Override
    public void checkBalance(Card card) {
        System.out.println("Please authenticate PIN first.");
    }

    @Override
    public void returntoOptions() {
        System.out.println("Please authenticate PIN first.");
    }

    @Override
    public void cashDispense(double amt) {
        System.out.println("Cannot dispense cash in HasCard state.");
    }
}
