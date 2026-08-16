import java.util.*;
class Helper{
    public static void main(String[] args) {
        ArrayList<String> billDescription = new ArrayList<>();
        System.out.println("Hello, welcome to Pizza Ordering System!");
        String name;
        String phoneNumber;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = scan.nextLine();
        System.out.println("Enter your phone number: ");
        phoneNumber = scan.nextLine();
        Customer customer = new Customer(name, phoneNumber);
        System.out.println("Welcome " + customer.getName() + ", choose your pizza base:\n1: Margherita\n2: Farmhouse\n3: Thin Crust");
        int choice = scan.nextInt();
        PizzaBase pizzaBase;
        switch(choice){
            case 1:
                pizzaBase = new Margerita();
                billDescription.add(pizzaBase.getDescription()+": "+pizzaBase.getCost());
                break;
            case 2:
                pizzaBase = new FarmHouse();
                billDescription.add(pizzaBase.getDescription()+": "+pizzaBase.getCost());
                break;
            case 3:
                pizzaBase = new ThinCrust();
                billDescription.add(pizzaBase.getDescription()+": "+pizzaBase.getCost());
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
    
    System.out.println("Choose your toppings:\n1: Cheese\n2: Olives\n3: Done");
    while(true){
        int toppingChoice = scan.nextInt();
        switch(toppingChoice){
            case 1:
                pizzaBase = new Cheese(pizzaBase);
                billDescription.add("Cheese: 20.00");
                System.out.println("Added Cheese");
                break;
            case 2:
                pizzaBase = new Olives(pizzaBase);
                billDescription.add("Olives: 15.00");
                System.out.println("Added Olives");
                break;
            case 3:
                System.out.println("Order Summary:");
                System.out.println("Customer Name: " + customer.getName());
                System.out.println("Phone Number: " + customer.getPhoneNumber());
                for(String item : billDescription){
                    System.out.println(item);
                }
                System.out.println("Total Cost: " + pizzaBase.getCost());
                System.out.println("Thank you for your order!");
                return;
            default:
                System.out.println("Invalid choice");     
        }
    }
}
}
