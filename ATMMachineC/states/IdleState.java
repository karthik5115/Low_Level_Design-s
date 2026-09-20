package states;

import atm.ATMMachine;
import model.Card;

public class IdleState implements ATMState {
    private ATMMachine atmMachine;

    public IdleState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public Card InsertCard(Card card) {
        System.out.println("Card inserted successfully.");
        atmMachine.setCurrentCard(card);
        atmMachine.setATMState(new HasCardState(atmMachine));
        return card;
    }

    @Override
    public boolean AuthenticatePin(Card card) {
        System.out.println("Please insert your card first.");
        return false;
    }

    @Override
    public void Withdraw(Card card) {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void SetATMPin(Card card) {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void Deposit(Card card) {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void Cancel(Card card) {
        System.out.println("No active transaction to cancel.");
    }

    @Override
    public void OptionstoSelect() {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void ReturnCard(Card card) {
        System.out.println("No card to return.");
    }

    @Override
    public void checkBalance(Card card) {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void returntoOptions() {
        System.out.println("Please insert your card first.");
    }

    @Override
    public void cashDispense(double amt) {
        System.out.println("Cannot dispense cash in idle state.");
    }
}
