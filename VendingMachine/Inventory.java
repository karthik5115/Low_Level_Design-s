import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ItemShelf> shelves;

    public Inventory() {
        this.shelves = new ArrayList<>();
    }

    public Inventory(List<ItemShelf> shelves) {
        this.shelves = shelves;
    }

    public List<ItemShelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<ItemShelf> shelves) {
        this.shelves = shelves;
    }

    public void addItemShelf(ItemShelf shelf) {
        this.shelves.add(shelf);
    }

    public ItemShelf getItemShelf(int code) {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                return shelf;
            }
        }
        return null;
    }

    public Product getProduct(int code) {
        ItemShelf shelf = getItemShelf(code);
        return shelf != null ? shelf.getProduct() : null;
    }

    public boolean isProductAvailable(int code) {
        ItemShelf shelf = getItemShelf(code);
        return shelf != null && !shelf.isSoldOut();
    }

    public void reduceProductQuantity(int code) {
        ItemShelf shelf = getItemShelf(code);
        if (shelf != null && !shelf.isSoldOut()) {
            shelf.decrementQuantity();
        }
    }

    public void displayInventory() {
        System.out.println("========== CURRENT INVENTORY ==========");
        for (ItemShelf shelf : shelves) {
            System.out.println(shelf);
        }
        System.out.println("========================================");
    }
}
