class Helper    {
    // This is a helper class for demonstration purposes.
    public static void main(String[] args) {
        IphoneStore iphoneStore = new IphoneStore();
        SamsungStore samsungStore = new SamsungStore();
        Customer customer1 = new IphoneNotificationAlert("karthik", "karthik@example.com", "123-456-7890");
        Customer customer2 = new IphoneNotificationAlert("john", "john@example.com", "987-654-3210");
        Customer customer3 = new samsungNotify("alice", "alice@example.com", "555-555-5555");
        iphoneStore.addObserver(customer1);
        iphoneStore.addObserver(customer2);
        iphoneStore.setInstock(true);
        samsungStore.addObserver(customer3);
        samsungStore.setInStock(true);
    }
}