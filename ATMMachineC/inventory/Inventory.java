package inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Note, Integer> noteQuantityMap;

    public Inventory() {
        this.noteQuantityMap = new HashMap<>();
        this.noteQuantityMap.put(new FiveHundredNote(), 0);
        this.noteQuantityMap.put(new TwoHundredNote(), 0);
        this.noteQuantityMap.put(new HundredNote(), 0);
    }

    public Inventory(int notes500, int notes200, int notes100) {
        this();
        addNotes(new FiveHundredNote(), notes500);
        addNotes(new TwoHundredNote(), notes200);
        addNotes(new HundredNote(), notes100);
    }

    public Map<Note, Integer> getNoteQuantityMap() {
        return noteQuantityMap;
    }

    public void addNotes(Note note, int quantity) {
        if (quantity > 0) {
            noteQuantityMap.put(note, noteQuantityMap.getOrDefault(note, 0) + quantity);
        }
    }

    public int getNoteQuantity(Note note) {
        return noteQuantityMap.getOrDefault(note, 0);
    }

    public void deductNotes(Note note, int quantity) {
        int currentQty = getNoteQuantity(note);
        if (currentQty >= quantity) {
            noteQuantityMap.put(note, currentQty - quantity);
        } else {
            noteQuantityMap.put(note, 0);
        }
    }

    public double getTotalBalance() {
        double total = 0;
        for (Map.Entry<Note, Integer> entry : noteQuantityMap.entrySet()) {
            total += entry.getKey().getValue() * entry.getValue();
        }
        return total;
    }

    public void displayInventory() {
        int qty500 = getNoteQuantity(new FiveHundredNote());
        int qty200 = getNoteQuantity(new TwoHundredNote());
        int qty100 = getNoteQuantity(new HundredNote());
        System.out.println("ATM Notes Available -> 500: " + qty500 + " | 200: " + qty200 + " | 100: " + qty100 + " | Total ATM Cash: Rs. " + getTotalBalance());
    }
}
