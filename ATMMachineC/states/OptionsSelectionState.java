package states;

import java.util.Scanner;
import atm.ATMMachine;
import model.Card;

public class OptionsSelectionState implements ATMState {
    private ATMMachine atmMachine;

    public OptionsSelectionState(ATMMachine atmMachine) {
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
    public void OptionstoSelect() {
        Card card = atmMachine.getCurrentCard();
        if (card == null) {
            System.out.println("No card found.");
            atmMachine.setATMState(new IdleState(atmMachine));
            return;
        }

        System.out.println("\n========== ATM Options ==========");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. Deposit Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Cancel / Exit");
        System.out.print("Please select an option (1-4): ");

        Scanner scanner = atmMachine.getScanner();
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                ATMState withdrawState = new WithdrawState(atmMachine);
                atmMachine.setATMState(withdrawState);
                withdrawState.Withdraw(card);
                break;
            case 2:
                ATMState depositState = new DepositState(atmMachine);
                atmMachine.setATMState(depositState);
                depositState.Deposit(card);
                break;
            case 3:
                ATMState checkBalanceState = new CheckBalanceState(atmMachine);
                atmMachine.setATMState(checkBalanceState);
                checkBalanceState.checkBalance(card);
                break;
            case 4:
                Cancel(card);
                break;
            default:
                System.out.println("Invalid option! Please select between 1 and 4.");
                OptionstoSelect();
                break;
        }
    }

    @Override
    public void Withdraw(Card card) {
        ATMState withdrawState = new WithdrawState(atmMachine);
        atmMachine.setATMState(withdrawState);
        withdrawState.Withdraw(card);
    }

    @Override
    public void SetATMPin(Card card) {
        System.out.println("PIN is already configured.");
    }

    @Override
    public void Deposit(Card card) {
        ATMState depositState = new DepositState(atmMachine);
        atmMachine.setATMState(depositState);
        depositState.Deposit(card);
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("Exiting options menu.");
        ReturnCard(card);
    }

    @Override
    public void ReturnCard(Card card) {
        System.out.println("Returning card... Please collect your card.");
        atmMachine.setCurrentCard(null);
        atmMachine.setATMState(new IdleState(atmMachine));
    }

    @Override
    public void checkBalance(Card card) {
        ATMState checkBalanceState = new CheckBalanceState(atmMachine);
        atmMachine.setATMState(checkBalanceState);
        checkBalanceState.checkBalance(card);
    }

    @Override
    public void returntoOptions() {
        OptionstoSelect();
    }

    @Override
    public void cashDispense(double amt) {
        System.out.println("Please select withdraw option first.");
    }
}
