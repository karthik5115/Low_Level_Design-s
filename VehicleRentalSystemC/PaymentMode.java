/**
 * PaymentMode
 */
public interface PaymentMode {

    public PaymentStatus paybill(double amount);
}

class CashMode implements PaymentMode {

    @Override
    public PaymentStatus paybill(double amount) {
        System.out.println("Payment by Cash Mode");
        return PaymentStatus.Paid;
    }
}

class UPI implements PaymentMode {

    @Override
    public PaymentStatus paybill(double amount) {
        System.out.println("Payment by UPI Mode");
        return PaymentStatus.Paid;
    }
}

class CreditCard implements PaymentMode {

    @Override
    public PaymentStatus paybill(double amount) {
        System.out.println("Payment by Credit Card Mode");
        return PaymentStatus.Paid;
    }
}