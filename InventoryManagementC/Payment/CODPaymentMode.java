package Payment;

public class CODPaymentMode implements Paymentmode {
    public boolean makePayment() {
        System.out.println("COD payment mode");
        return true;
    }
}
