public class ItemShelf {
    private Product product;
    private int code;
    private int quantity;

    public ItemShelf(int code, Product product, int quantity) {
        this.code = code;
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSoldOut() {
        return quantity <= 0;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    @Override
    public String toString() {
        return "[Code: " + code + " | " + product.getName() + " | Price: ₹" + product.getPrice() + " | Stock: " + quantity + "]";
    }
}
