package states;

import atm.ATMMachine;
import model.Card;

public class CashDispenseState implements ATMState {
    private ATMMachine atmMachine;

    public CashDispenseState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public Card InsertCard(Card card) {
        System.out.println("Cannot insert card during cash dispensing.");
        return card;
    }

    @Override
    public boolean AuthenticatePin(Card card) {
        System.out.println("Cannot authenticate PIN during cash dispensing.");
        return false;
    }

    @Override
    public void Withdraw(Card card) {
        System.out.println("Already processing withdrawal.");
    }

    @Override
    public void SetATMPin(Card card) {
        System.out.println("Cannot set PIN during cash dispensing.");
    }

    @Override
    public void Deposit(Card card) {
        System.out.println("Cannot deposit during cash dispensing.");
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("Cannot cancel while dispensing cash.");
    }

    @Override
    public void OptionstoSelect() {
        System.out.println("Cannot select options while dispensing cash.");
    }

    @Override
    public void ReturnCard(Card card) {
        System.out.println("Returning card... Please collect your card.");
        atmMachine.setCurrentCard(null);
        atmMachine.setATMState(new IdleState(atmMachine));
    }

    @Override
    public void checkBalance(Card card) {
        System.out.println("Cannot check balance during cash dispensing.");
    }

    @Override
    public void returntoOptions() {
        System.out.println("Cannot return to options during cash dispensing.");
    }

    @Override
    public void cashDispense(double amt) {
        Card card = atmMachine.getCurrentCard();
        if (card == null) {
            System.out.println("No card found to dispense cash.");
            atmMachine.setATMState(new IdleState(atmMachine));
            return;
        }

        System.out.println("\n========== Cash Dispensing ==========");
        card.withdraw(amt);
        System.out.println("Notes breakdown from ATM:");
        atmMachine.getDispenserChain().dispense(atmMachine, (int) amt);
        System.out.println("Cash dispensed successfully!");
        atmMachine.displayAtmInventory();
        System.out.println("=====================================");

        ReturnCard(card);
    }
}
