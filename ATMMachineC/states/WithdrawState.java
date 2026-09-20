package states;

import java.util.Scanner;
import atm.ATMMachine;
import model.Card;

public class WithdrawState implements ATMState {
    private ATMMachine atmMachine;

    public WithdrawState(ATMMachine atmMachine) {
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
    public void Withdraw(Card card) {
        if (card == null) {
            System.out.println("No card found.");
            atmMachine.setATMState(new IdleState(atmMachine));
            return;
        }

        Scanner scanner = atmMachine.getScanner();
        System.out.print("\nEnter amount to withdraw (multiples of 500, 200, 100): Rs. ");
        int amount = scanner.nextInt();

        if (amount <= 0 || amount % 100 != 0) {
            System.out.println("Invalid amount! Please enter amount in multiples of 500, 200, 100.");
            promptNextAction(card);
            return;
        }

        if (card.getBalance() < amount) {
            System.out.println("Insufficient balance! Your current account balance is: Rs. " + card.getBalance());
            promptNextAction(card);
            return;
        }

        if (amount > atmMachine.getInventory().getTotalBalance()) {
            System.out.println("ATM has insufficient cash! Available ATM cash: Rs. " + atmMachine.getInventory().getTotalBalance());
            promptNextAction(card);
            return;
        }

        ATMState dispenseState = new CashDispenseState(atmMachine);
        atmMachine.setATMState(dispenseState);
        dispenseState.cashDispense(amount);
    }

    private void promptNextAction(Card card) {
        System.out.println("1. Return to Main Menu");
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
    public void SetATMPin(Card card) {
        System.out.println("Cannot set PIN during withdrawal.");
    }

    @Override
    public void Deposit(Card card) {
        System.out.println("Currently in Withdraw state. Please return to options to deposit.");
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("Withdrawal cancelled.");
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
        System.out.println("Currently in Withdraw state. Please return to options to check balance.");
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
        System.out.println("Processing cash dispense...");
    }
}
