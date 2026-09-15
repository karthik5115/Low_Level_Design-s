import java.util.UUID;

public class Payment {

    private final UUID paymentId;
    private PaymentStatus status;
    private double amount;
    private PaymentMethod paymentMethod;

    public Payment(PaymentStatus status, double amount, PaymentMethod paymentMethod) {
        this.paymentId = UUID.randomUUID();
        this.status = status;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}

enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED,
    REFUNDED
}

enum PaymentMethod {
    DEBIT_CARD,
    CREDIT_CARD,
    UPI,
    NET_BANKING
}