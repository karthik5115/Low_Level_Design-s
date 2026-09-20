package states;

import java.util.Scanner;
import atm.ATMMachine;
import model.Card;

public class CheckBalanceState implements ATMState {
    private ATMMachine atmMachine;

    public CheckBalanceState(ATMMachine atmMachine) {
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
    public void checkBalance(Card card) {
        if (card == null) {
            System.out.println("No card found.");
            atmMachine.setATMState(new IdleState(atmMachine));
            return;
        }

        System.out.println("\n========== Account Balance ==========");
        card.checkBalance();
        System.out.println("=====================================");

        System.out.println("\n1. Return to Main Menu");
        System.out.println("2. Cancel & Exit");
        System.out.print("Select an option (1-2): ");

        Scanner scanner = atmMachine.getScanner();
        int choice = scanner.nextInt();
        if (choice == 1) {
            returntoOptions();
        } else {
            Cancel(card);
        }
    }

    @Override
    public void Withdraw(Card card) {
        System.out.println("Currently in CheckBalance state. Please return to options to withdraw.");
    }

    @Override
    public void SetATMPin(Card card) {
        System.out.println("Cannot set PIN during balance check.");
    }

    @Override
    public void Deposit(Card card) {
        System.out.println("Currently in CheckBalance state. Please return to options to deposit.");
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("Exiting balance inquiry.");
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
    public void returntoOptions() {
        System.out.println("Returning to options menu...");
        ATMState optionsState = new OptionsSelectionState(atmMachine);
        atmMachine.setATMState(optionsState);
        optionsState.OptionstoSelect();
    }

    @Override
    public void cashDispense(double amt) {
        System.out.println("Cannot dispense cash in CheckBalance state.");
    }
}
