package Store;

public class Product {
    private int productId;
    private String productDescription;

    public Product(int productId, String productDescription) {
        this.productId = productId;
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getproductDescription() {
        return productDescription;
    }

    public void setproductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}