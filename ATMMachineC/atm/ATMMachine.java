package atm;

import java.util.Scanner;
import model.Card;
import states.ATMState;
import states.IdleState;
import inventory.Inventory;
import dispensers.CashDispenser;
import dispensers.FiveHundredDispenser;
import dispensers.TwoHundredDispenser;
import dispensers.HundredDispenser;

public class ATMMachine {
    private ATMState currentATMState;
    private Card currentCard;
    private Scanner scanner;
    private CashDispenser dispenserChain;
    private Inventory inventory;

    public ATMMachine() {
        this(new Inventory(20, 30, 50));
    }

    public ATMMachine(Inventory inventory) {
        this.scanner = new Scanner(System.in);
        this.currentATMState = new IdleState(this);
        this.inventory = inventory;

        CashDispenser hundred = new HundredDispenser(null);
        CashDispenser twoHundred = new TwoHundredDispenser(hundred);
        CashDispenser fiveHundred = new FiveHundredDispenser(twoHundred);
        this.dispenserChain = fiveHundred;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void displayAtmInventory() {
        if (inventory != null) {
            inventory.displayInventory();
        }
    }

    public Scanner getScanner() {
        if (this.scanner == null) {
            this.scanner = new Scanner(System.in);
        }
        return this.scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ATMState getATMState() {
        return currentATMState;
    }

    public void setATMState(ATMState currentATMState) {
        this.currentATMState = currentATMState;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public CashDispenser getDispenserChain() {
        return dispenserChain;
    }

    public void setDispenserChain(CashDispenser dispenserChain) {
        this.dispenserChain = dispenserChain;
    }

    public Card insertCard(Card card) {
        return this.currentATMState.InsertCard(card);
    }

    public boolean authenticatePin(Card card) {
        return this.currentATMState.AuthenticatePin(card);
    }

    public void setATMPin(Card card) {
        this.currentATMState.SetATMPin(card);
    }

    public void optionstoSelect() {
        this.currentATMState.OptionstoSelect();
    }

    public void withdraw(Card card) {
        this.currentATMState.Withdraw(card);
    }

    public void deposit(Card card) {
        this.currentATMState.Deposit(card);
    }

    public void checkBalance(Card card) {
        this.currentATMState.checkBalance(card);
    }

    public void cancel(Card card) {
        this.currentATMState.Cancel(card);
    }

    public void returnCard(Card card) {
        this.currentATMState.ReturnCard(card);
    }

    public void returntoOptions() {
        this.currentATMState.returntoOptions();
    }

    public void cashDispense(double amt) {
        this.currentATMState.cashDispense(amt);
    }
}
