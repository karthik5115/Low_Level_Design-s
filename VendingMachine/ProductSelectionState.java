import java.util.ArrayList;
import java.util.List;

public class ProductSelectionState implements StateInterface {

    public ProductSelectionState() {
        System.out.println("[State: PRODUCT_SELECTION] Please enter the product code to purchase, or request a full refund to cancel.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new IllegalStateException("Cannot insert coins during product selection. Please select product or cancel.");
    }

    @Override
    public void productSelectionButton(VendingMachine machine) throws Exception {
        System.out.println("Already in product selection state.");
    }

    @Override
    public void selectProduct(VendingMachine machine, int code) throws Exception {
        ItemShelf shelf = machine.getInventory().getItemShelf(code);

        if (shelf == null) {
            System.out.println("❌ ERROR: Invalid product code [" + code + "]. Canceling transaction...");
            getFullRefund(machine);
            return;
        }

        if (shelf.isSoldOut()) {
            System.out.println("❌ ERROR: Product '" + shelf.getProduct().getName() + "' is SOLD OUT. Canceling transaction...");
            getFullRefund(machine);
            return;
        }

        Product product = shelf.getProduct();
        int paidAmount = machine.getInsertedAmount();
        int price = product.getPrice();

        System.out.println("-> Selected: " + product.getName() + " | Price: ₹" + price + " | Inserted Amount: ₹" + paidAmount);

        if (paidAmount < price) {
            System.out.println("❌ ERROR: Insufficient amount. Product price: ₹" + price + ", Inserted: ₹" + paidAmount + ". Canceling transaction...");
            getFullRefund(machine);
            return;
        }

        int change = paidAmount - price;
        if (change > 0) {
            List<Coin> changeCoins = machine.calculateChange(change);
            if (changeCoins == null) {
                System.out.println("❌ ERROR: Vending machine does not have suitable coins (1, 5, 10, 100) to return exact change of ₹" + change + ".");
                System.out.println("   Canceling entire transaction and returning full amount...");
                getFullRefund(machine);
                return;
            } else {
                getChange(machine, change);
            }
        } else {
            System.out.println("-> Exact amount received. No change required.");
        }

        machine.setCurrentState(new DispenseProductState());
        machine.dispenseProduct(code);
    }

    @Override
    public int getChange(VendingMachine machine, int returnChangeMoney) throws Exception {
        List<Coin> dispensedCoins = machine.deductAndDispenseChange(returnChangeMoney);
        System.out.println("💰 Change Dispensed: ₹" + returnChangeMoney + " -> " + dispensedCoins);
        return returnChangeMoney;
    }

    @Override
    public List<Coin> getFullRefund(VendingMachine machine) throws Exception {
        System.out.println("-> Refunding full amount: ₹" + machine.getInsertedAmount());
        List<Coin> refund = new ArrayList<>(machine.getInsertedCoinList());
        System.out.println("   Refunded Coins: " + refund);
        machine.clearInsertedCoins();
        machine.setCurrentState(new IdleState(machine));
        return refund;
    }

    @Override
    public Product dispenseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot dispense product without valid selection and payment verification.");
    }
}
