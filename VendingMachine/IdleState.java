import java.util.ArrayList;
import java.util.List;

public class IdleState implements StateInterface {

    public IdleState() {
        System.out.println("[State: IDLE] Vending Machine is in Idle state. Please insert coins.");
    }

    public IdleState(VendingMachine machine) {
        machine.clearInsertedCoins();
        System.out.println("[State: IDLE] Vending Machine reset to Idle state. Ready for next customer.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        System.out.println("-> Coin inserted: " + coin + " (Value: ₹" + coin.getValue() + ")");
        machine.addInsertedCoin(coin);
        machine.setCurrentState(new HasMoneyState());
    }

    @Override
    public void productSelectionButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Cannot press product selection button in IDLE state. Please insert coins first.");
    }

    @Override
    public void selectProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot select product in IDLE state. Please insert coins first.");
    }

    @Override
    public int getChange(VendingMachine machine, int returnChangeMoney) throws Exception {
        throw new IllegalStateException("Cannot get change in IDLE state.");
    }

    @Override
    public List<Coin> getFullRefund(VendingMachine machine) throws Exception {
        System.out.println("No money to refund in IDLE state.");
        return new ArrayList<>();
    }

    @Override
    public Product dispenseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot dispense product in IDLE state.");
    }
}
