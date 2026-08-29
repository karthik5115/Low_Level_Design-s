import java.sql.Date;

public class Payment {
    private int paymentId;
    private double amount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
    private PaymentMode pmode;

    public Payment(PaymentMode pmode, Date paymentDate, PaymentStatus paymentStatus, double amount) {
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.pmode = pmode;
        this.amount = amount;
        this.paymentId = (int) (Math.random() * 10000);
    }

    // getters
    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMode getPmode() {
        return pmode;
    }

    // setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPmode(PaymentMode pmode) {
        this.pmode = pmode;
    }

}
