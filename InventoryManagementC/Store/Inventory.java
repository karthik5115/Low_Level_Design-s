package Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Inventory {
    private Map<Integer, ProductCategory> productCategories;

    public Inventory() {
        productCategories = new HashMap<>();
    }

    public void addProduct(Map<Integer, Integer> productCategoryIdQuantityMap) {
        for (Map.Entry<Integer, Integer> entry : productCategoryIdQuantityMap.entrySet()) {
            int categoryId = entry.getKey();
            int quantity = entry.getValue();
            ProductCategory category = productCategories.get(categoryId);
            category.addProducts(quantity);
        }
    }

    public void removeProductFromCategory(Map<Integer, Integer> productCategoryIdQuantityMap) {
        for (Map.Entry<Integer, Integer> entry : productCategoryIdQuantityMap.entrySet()) {
            int categoryId = entry.getKey();
            int quantity = entry.getValue();
            if (productCategories.containsKey(categoryId)) {
                ProductCategory category = productCategories.get(categoryId);
                category.removeProducts(quantity);
            } else {
                System.out.println("Category not found");
            }
        }
    }

    public void addCategory(ProductCategory category) {

        productCategories.put(category.getCategoryId(), category);
    }

    public void removeCategory(int categoryId) {
        productCategories.remove(categoryId);
    }

    public ProductCategory getCategory(int categoryId) {
        return productCategories.get(categoryId);
    }

    public List<ProductCategory> listCategories() {
        for (ProductCategory category : productCategories.values()) {
            System.out.println(category);
        }
        return new ArrayList<>(productCategories.values());
    }

    public boolean isProductAvailable(int categoryId, int quantity) {
        ProductCategory category = productCategories.get(categoryId);
        if (category != null && category.getQuantity() >= quantity) {
            return true;
        }
        return false;
    }

}
