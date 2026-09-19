import java.util.List;

public class DispenseProductState implements StateInterface {

    public DispenseProductState() {
        System.out.println("[State: DISPENSE_PRODUCT] Preparing to dispense product...");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new IllegalStateException("Cannot insert coin while dispensing product.");
    }

    @Override
    public void productSelectionButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Cannot press product selection button while dispensing product.");
    }

    @Override
    public void selectProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot select product while dispensing product.");
    }

    @Override
    public int getChange(VendingMachine machine, int returnChangeMoney) throws Exception {
        throw new IllegalStateException("Cannot get change while dispensing product.");
    }

    @Override
    public List<Coin> getFullRefund(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Cannot refund while product is already being dispensed.");
    }

    @Override
    public Product dispenseProduct(VendingMachine machine, int code) throws Exception {
        ItemShelf shelf = machine.getInventory().getItemShelf(code);
        Product product = shelf.getProduct();

        machine.getInventory().reduceProductQuantity(code);
        machine.addCoinsToReserve(machine.getInsertedCoinList());
        machine.clearInsertedCoins();

        System.out.println("🎉 SUCCESS: Dispensing '" + product.getName() + "'! Please collect your item from the tray.");

        machine.setCurrentState(new IdleState(machine));
        return product;
    }
}
