import java.util.ArrayList;
import java.util.List;

public class HasMoneyState implements StateInterface {

    public HasMoneyState() {
        System.out.println("[State: HAS_MONEY] Money accepted. You can insert more coins, press product selection button, or cancel for full refund.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        System.out.println("-> Additional coin inserted: " + coin + " (Value: ₹" + coin.getValue() + ")");
        machine.addInsertedCoin(coin);
        System.out.println("   Current Total Balance: ₹" + machine.getInsertedAmount());
    }

    @Override
    public void productSelectionButton(VendingMachine machine) throws Exception {
        System.out.println("-> Product selection button pressed.");
        machine.setCurrentState(new ProductSelectionState());
    }

    @Override
    public void selectProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Please press the product selection button before choosing a product code.");
    }

    @Override
    public int getChange(VendingMachine machine, int returnChangeMoney) throws Exception {
        throw new IllegalStateException("Cannot get change in HAS_MONEY state. Please select a product or cancel.");
    }

    @Override
    public List<Coin> getFullRefund(VendingMachine machine) throws Exception {
        System.out.println("-> Cancel requested. Refunding full amount: ₹" + machine.getInsertedAmount());
        List<Coin> refund = new ArrayList<>(machine.getInsertedCoinList());
        System.out.println("   Refunded Coins: " + refund);
        machine.clearInsertedCoins();
        machine.setCurrentState(new IdleState(machine));
        return refund;
    }

    @Override
    public Product dispenseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot dispense product in HAS_MONEY state.");
    }
}
