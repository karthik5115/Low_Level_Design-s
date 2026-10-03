package Payment;

public class Payment {

    Paymentmode paymentMode;

    public Payment(Paymentmode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public boolean makePayment() {
        return paymentMode.makePayment();
    }

}
