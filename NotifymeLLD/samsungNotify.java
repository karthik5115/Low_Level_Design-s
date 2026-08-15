class samsungNotify extends Customer{
    public samsungNotify(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }

    @Override
    void update(String message) {
        System.out.println(message);
    }
}