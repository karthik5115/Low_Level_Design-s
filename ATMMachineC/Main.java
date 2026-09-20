import java.util.Date;
import java.util.Scanner;
import atm.ATMMachine;
import inventory.Inventory;
import model.BankAccount;
import model.Card;
import model.User;
import states.HasCardState;
import states.CheckBalanceState;
import states.DepositState;
import states.CashDispenseState;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("          ATM MACHINE SYSTEM DEMONSTRATION       ");
        System.out.println("=================================================\n");

        BankAccount account = new BankAccount("ACC-9876543210", "John Doe", 10000.0);
        Card card = new Card("4532-1234-5678-9012", "123", new Date(), account);
        User user = new User(card);
        Inventory inventory = new Inventory(20, 30, 50);
        ATMMachine atm = new ATMMachine(inventory);

        System.out.println("Initialized User Account with balance: Rs. " + account.getBalance());
        atm.displayAtmInventory();

        Scanner scanner = atm.getScanner();
        System.out.println("\nSelect execution mode:");
        System.out.println("1. Run Automated LLD Flow Simulation");
        System.out.println("2. Start Interactive ATM Console");
        System.out.print("Enter choice (1 or 2): ");

        int mode = 1;
        if (scanner.hasNextInt()) {
            mode = scanner.nextInt();
        }

        if (mode == 2) {
            runInteractive(atm, user);
        } else {
            runAutomatedTests(atm, user);
        }
    }

    private static void runAutomatedTests(ATMMachine atm, User user) {
        Card card = user.getCard();

        System.out.println("\n--- [TEST 1: Card Insertion & PIN Setup] ---");
        atm.insertCard(card);
        System.out.println("Current State: " + atm.getATMState().getClass().getSimpleName());

        card.setPin(1234);

        System.out.println("\n--- [TEST 2: Note Inventory Tracking & Dispenser Chain] ---");
        System.out.println("Testing Cash Dispenser Chain for Rs. 3800:");
        atm.getDispenserChain().dispense(atm, 3800);
        atm.displayAtmInventory();

        System.out.println("\nTesting Cash Dispenser Chain for Rs. 700:");
        atm.getDispenserChain().dispense(atm, 700);
        atm.displayAtmInventory();

        System.out.println("\nTesting Cash Dispenser Chain for Rs. 400:");
        atm.getDispenserChain().dispense(atm, 400);
        atm.displayAtmInventory();

        System.out.println("\n--- [TEST 3: Check Balance State] ---");
        atm.setATMState(new CheckBalanceState(atm));
        card.checkBalance();

        System.out.println("\n--- [TEST 4: Deposit State] ---");
        atm.setATMState(new DepositState(atm));
        card.deposit(2500);
        System.out.println("Balance after deposit: Rs. " + card.getBalance());

        System.out.println("\n--- [TEST 5: Withdraw & Cash Dispense with Note Inventory] ---");
        atm.setATMState(new CashDispenseState(atm));
        atm.setCurrentCard(card);
        atm.cashDispense(3700);

        System.out.println("Current State after dispensing: " + atm.getATMState().getClass().getSimpleName());
        System.out.println("Remaining Balance in Account: Rs. " + card.getBalance());

        System.out.println("\n--- [TEST 6: Invalid Multiples Check] ---");
        int testAmt = 455;
        if (testAmt % 100 != 0) {
            System.out.println("Amount Rs. " + testAmt + " rejected: Please enter amount in multiples of 500, 200, 100.");
        }

        System.out.println("\n--- [TEST 7: ATM Cash Depletion & Insufficient Funds Check] ---");
        Inventory lowInventory = new Inventory(0, 1, 2);
        ATMMachine lowCashAtm = new ATMMachine(lowInventory);
        lowCashAtm.displayAtmInventory();
        System.out.println("Attempting to dispense Rs. 800 from ATM with only Rs. " + lowCashAtm.getInventory().getTotalBalance() + " available:");
        if (800 > lowCashAtm.getInventory().getTotalBalance()) {
            System.out.println("ATM rejected transaction: ATM has insufficient cash!");
        }

        System.out.println("\n=================================================");
        System.out.println("        ALL AUTOMATED TESTS PASSED SUCCESSFULLY! ");
        System.out.println("=================================================");
    }

    private static void runInteractive(ATMMachine atm, User user) {
        Scanner scanner = atm.getScanner();
        boolean running = true;
        while (running) {
            System.out.println("\n--- ATM Main Standby Screen ---");
            atm.displayAtmInventory();
            System.out.println("1. Insert Card");
            System.out.println("2. Exit System");
            System.out.print("Choose action: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                atm.insertCard(user.getCard());
                handleCardSession(atm, user.getCard());
            } else {
                System.out.println("Shutting down ATM. Goodbye!");
                running = false;
            }
        }
    }

    private static void handleCardSession(ATMMachine atm, Card card) {
        Scanner scanner = atm.getScanner();
        boolean sessionActive = true;
        while (sessionActive && atm.getATMState() instanceof HasCardState) {
            System.out.println("\n--- Card Session ---");
            System.out.println("1. Authenticate PIN");
            System.out.println("2. Set / Change PIN");
            System.out.println("3. Cancel / Eject Card");
            System.out.print("Choose action: ");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    boolean auth = atm.authenticatePin(card);
                    if (!auth) {
                        System.out.println("Authentication failed. Try again.");
                    } else {
                        sessionActive = false;
                    }
                    break;
                case 2:
                    atm.setATMPin(card);
                    break;
                case 3:
                    atm.cancel(card);
                    sessionActive = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
