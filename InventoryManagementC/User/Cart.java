package User;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, Integer> productCategoryIdQuantityMap;

    public Cart() {
        this.productCategoryIdQuantityMap = new HashMap<>();
    }

    public void addProduct(int categoryId, int quantity) {
        // check wheter category is already exists or not
        if (productCategoryIdQuantityMap.containsKey(categoryId)) {
            // get the current quantity
            int currentQuantity = productCategoryIdQuantityMap.get(categoryId);
            // add the new quantity
            productCategoryIdQuantityMap.put(categoryId, currentQuantity + quantity);
        } else {
            // add the new category
            productCategoryIdQuantityMap.put(categoryId, quantity);
        }
    }

    public void removeProduct(int categoryId) {
        // check wheter category is already exists or not
        if (productCategoryIdQuantityMap.containsKey(categoryId)) {
            int currentQuantity = productCategoryIdQuantityMap.get(categoryId);
            if (currentQuantity - 1 == 0) {
                productCategoryIdQuantityMap.remove(categoryId);
            } else {
                productCategoryIdQuantityMap.put(categoryId, currentQuantity - 1);
            }
        } else {
            // print error message
            System.out.println("Category not found");
        }
    }

    public Map<Integer, Integer> getProductCategoryIdQuantityMap() {
        return productCategoryIdQuantityMap;
    }

    @Override
    public String toString() {
        return "Cart [productCategoryIdQuantityMap=" + productCategoryIdQuantityMap + "]";
    }

}
