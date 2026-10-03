package User;

import Utilities.Address;
import java.util.Map;
import Payment.Payment;
import Payment.Paymentmode;
import Payment.UPIpaymentMode;
import Store.WareHouse;

public class Order {
    User user;
    Address deliveryAddress;
    Map<Integer, Integer> productCategoryIdQuantityMap;
    WareHouse wareHouse;
    Invoice invoice;
    Payment payment;
    OrderStatus orderStatus;

    public Order(User user, WareHouse wareHouse) {
        this.user = user;
        this.deliveryAddress = user.getAddress();
        this.productCategoryIdQuantityMap = user.getCart().getProductCategoryIdQuantityMap();
        this.invoice = new Invoice(this, wareHouse.getInventory());
        this.orderStatus = OrderStatus.PLACED;
        this.wareHouse = wareHouse;
    }

    public void checkOut() {
        wareHouse.getInventory().removeProductFromCategory(productCategoryIdQuantityMap);
        this.orderStatus = OrderStatus.PACKED;
        boolean isPaymentSuccess = makePayment(new UPIpaymentMode());
        if (isPaymentSuccess) {
            this.orderStatus = OrderStatus.DELIVERED;
        } else {
            this.orderStatus = OrderStatus.CANCELLED;
            wareHouse.getInventory().addProduct(productCategoryIdQuantityMap);
        }

    }

    public boolean makePayment(Paymentmode paymentmode) {
        payment = new Payment(paymentmode);
        return payment.makePayment();
    }

    public Invoice generateInvoice() {
        return invoice.generateInvoice();
    }

}
