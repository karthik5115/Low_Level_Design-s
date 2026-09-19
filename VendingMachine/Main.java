public class Main {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("          VENDING MACHINE LOW-LEVEL DESIGN DEMO");
        System.out.println("===============================================================\n");

        Inventory inventory = new Inventory();
        inventory.addItemShelf(new ItemShelf(101, new Product("Coca Cola", 25), 3));
        inventory.addItemShelf(new ItemShelf(102, new Product("Pepsi", 20), 2));
        inventory.addItemShelf(new ItemShelf(103, new Product("Lays Chips", 15), 1));
        inventory.addItemShelf(new ItemShelf(104, new Product("Red Bull", 110), 2));
        inventory.addItemShelf(new ItemShelf(105, new Product("Snickers", 35), 0));

        VendingMachine machine = new VendingMachine(inventory);

        machine.addCoinToReserve(Coin.ONE, 20);
        machine.addCoinToReserve(Coin.FIVE, 10);
        machine.addCoinToReserve(Coin.TEN, 10);
        machine.addCoinToReserve(Coin.HUNDRED, 2);

        inventory.displayInventory();

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("TEST 1: Successful Purchase with Change Calculation");
        System.out.println("---------------------------------------------------------------");
        try {
            machine.insertCoin(Coin.HUNDRED);
            machine.productSelectionButton();
            machine.selectProduct(101);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("TEST 2: User Inserts Coins and Cancels at HasMoneyState");
        System.out.println("---------------------------------------------------------------");
        try {
            machine.insertCoin(Coin.TEN);
            machine.insertCoin(Coin.FIVE);
            machine.getFullRefund();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("TEST 3: Insufficient Funds (Inserted ₹10, Price ₹20)");
        System.out.println("---------------------------------------------------------------");
        try {
            machine.insertCoin(Coin.TEN);
            machine.productSelectionButton();
            machine.selectProduct(102);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("TEST 4: Selecting Sold Out Product (Snickers - 105)");
        System.out.println("---------------------------------------------------------------");
        try {
            machine.insertCoin(Coin.HUNDRED);
            machine.productSelectionButton();
            machine.selectProduct(105);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("TEST 5: Machine Cannot Dispense Exact Change -> Full Refund");
        System.out.println("---------------------------------------------------------------");
        try {
            Inventory emptyChangeInventory = new Inventory();
            emptyChangeInventory.addItemShelf(new ItemShelf(201, new Product("Cold Coffee", 35), 5));
            VendingMachine machineWithoutChange = new VendingMachine(emptyChangeInventory);

            machineWithoutChange.insertCoin(Coin.HUNDRED);
            machineWithoutChange.productSelectionButton();
            machineWithoutChange.selectProduct(201);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("TEST 6: Exact Payment (Inserted ₹15 for Lays Chips ₹15)");
        System.out.println("---------------------------------------------------------------");
        try {
            machine.insertCoin(Coin.TEN);
            machine.insertCoin(Coin.FIVE);
            machine.productSelectionButton();
            machine.selectProduct(103);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("FINAL INVENTORY STATE");
        System.out.println("---------------------------------------------------------------");
        inventory.displayInventory();
    }
}
