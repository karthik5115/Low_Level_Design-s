package dispensers;

import atm.ATMMachine;

public abstract class CashDispenser {
    protected CashDispenser nextDispenser;

    public CashDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public abstract void dispense(ATMMachine atmMachine, int amount);
}
