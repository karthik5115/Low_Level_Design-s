package dispensers;

import atm.ATMMachine;
import inventory.TwoHundredNote;
import inventory.Note;

public class TwoHundredDispenser extends CashDispenser {

    public TwoHundredDispenser(CashDispenser nextDispenser) {
        super(nextDispenser);
    }

    @Override
    public void dispense(ATMMachine atmMachine, int amount) {
        Note note = new TwoHundredNote();
        int noteVal = note.getValue();
        int requiredNotes = amount / noteVal;
        int availableNotes = atmMachine.getInventory().getNoteQuantity(note);
        int notesToDispense = Math.min(requiredNotes, availableNotes);

        if (notesToDispense > 0) {
            atmMachine.getInventory().deductNotes(note, notesToDispense);
            System.out.println("Dispensing " + notesToDispense + " note(s) of Rs. " + noteVal);
        }

        int remainder = amount - (notesToDispense * noteVal);
        if (remainder > 0) {
            if (nextDispenser != null) {
                nextDispenser.dispense(atmMachine, remainder);
            } else {
                System.out.println("Remaining amount of Rs. " + remainder + " cannot be dispensed due to note unavailability.");
            }
        }
    }
}
