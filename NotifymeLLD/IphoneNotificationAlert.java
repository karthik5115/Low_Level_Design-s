class IphoneNotificationAlert extends Customer {
    IphoneNotificationAlert(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
    @Override
    public void update(String message) {
        System.out.println(message);
    }       
}