package Payment;

public class UPIpaymentMode implements Paymentmode {
    public boolean makePayment() {
        System.out.println("UPI payment mode");
        return true;
    }
}
