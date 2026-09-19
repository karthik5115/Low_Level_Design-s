import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private StateInterface currentState;
    private Inventory inventory;
    private List<Coin> insertedCoinList;
    private Map<Coin, Integer> coinReserve;

    public VendingMachine() {
        this.currentState = new IdleState();
        this.inventory = new Inventory();
        this.insertedCoinList = new ArrayList<>();
        this.coinReserve = new EnumMap<Coin, Integer>(Coin.class);
        for (Coin c : Coin.values()) {
            coinReserve.put(c, 0);
        }
    }

    public VendingMachine(Inventory inventory) {
        this();
        this.inventory = inventory;
    }

    public StateInterface getCurrentState() {
        return currentState;
    }

    public void setCurrentState(StateInterface currentState) {
        this.currentState = currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Coin> getInsertedCoinList() {
        return insertedCoinList;
    }

    public void setInsertedCoinList(List<Coin> insertedCoinList) {
        this.insertedCoinList = insertedCoinList;
    }

    public void addInsertedCoin(Coin coin) {
        this.insertedCoinList.add(coin);
    }

    public int getInsertedAmount() {
        int total = 0;
        for (Coin coin : insertedCoinList) {
            total += coin.getValue();
        }
        return total;
    }

    public void clearInsertedCoins() {
        this.insertedCoinList.clear();
    }

    public Map<Coin, Integer> getCoinReserve() {
        return coinReserve;
    }

    public void addCoinToReserve(Coin coin, int count) {
        coinReserve.put(coin, coinReserve.getOrDefault(coin, 0) + count);
    }

    public void addCoinsToReserve(List<Coin> coins) {
        for (Coin coin : coins) {
            addCoinToReserve(coin, 1);
        }
    }

    public List<Coin> calculateChange(int changeAmount) {
        if (changeAmount <= 0) {
            return new ArrayList<>();
        }

        List<Coin> result = new ArrayList<>();
        int remaining = changeAmount;
        Coin[] denominations = { Coin.HUNDRED, Coin.TEN, Coin.FIVE, Coin.ONE };
        Map<Coin, Integer> tempReserve = new EnumMap<Coin, Integer>(coinReserve);

        for (Coin coin : denominations) {
            int coinVal = coin.getValue();
            int availableCount = tempReserve.getOrDefault(coin, 0);

            if (remaining >= coinVal && availableCount > 0) {
                int needed = remaining / coinVal;
                int take = Math.min(needed, availableCount);

                for (int i = 0; i < take; i++) {
                    result.add(coin);
                }
                remaining -= take * coinVal;
                tempReserve.put(coin, availableCount - take);
            }
        }

        if (remaining == 0) {
            return result;
        } else {
            return null;
        }
    }

    public List<Coin> deductAndDispenseChange(int changeAmount) {
        List<Coin> changeCoins = calculateChange(changeAmount);
        if (changeCoins != null) {
            for (Coin coin : changeCoins) {
                coinReserve.put(coin, coinReserve.get(coin) - 1);
            }
        }
        return changeCoins;
    }

    public void insertCoin(Coin coin) throws Exception {
        currentState.insertCoin(this, coin);
    }

    public void productSelectionButton() throws Exception {
        currentState.productSelectionButton(this);
    }

    public void selectProduct(int code) throws Exception {
        currentState.selectProduct(this, code);
    }

    public int getChange(int returnChangeMoney) throws Exception {
        return currentState.getChange(this, returnChangeMoney);
    }

    public List<Coin> getFullRefund() throws Exception {
        return currentState.getFullRefund(this);
    }

    public Product dispenseProduct(int code) throws Exception {
        return currentState.dispenseProduct(this, code);
    }
}
