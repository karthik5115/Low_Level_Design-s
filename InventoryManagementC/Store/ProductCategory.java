package Store;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    private int categoryId;
    private String categoryName;
    private List<Product> products;
    private Double price;

    public ProductCategory(int categoryId, String categoryName, Double price) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.price = price;
        products = new ArrayList<>();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(int Quantity) {
        for (int i = 0; i < Quantity; i++) {
            this.products.add(new Product(categoryId, categoryName));
        }
    }

    public void removeProducts(int count) {
        for (int i = 0; i < count; i++) {
            if (products.size() > 0) {
                this.products.remove(this.products.get(0));
            } else {
                System.out.println(count + "No of Products not Available");
                break;
            }
        }
    }

    public int getQuantity() {
        return products.size();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "Category Name: " + categoryName + " Price: " + price +
                "Quantity Available:" + products.size();
    }
}
