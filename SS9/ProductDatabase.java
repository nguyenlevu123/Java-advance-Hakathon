import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> productList;

    private ProductDatabase() {
        productList = new ArrayList<>();
    }

    public static synchronized ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product findById(String id) {
        for (Product product : productList) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    public boolean updateProduct(String id, String newName, double newPrice, double newExtraValue) {
        Product product = findById(id);
        if (product == null) {
            return false;
        }

        product.setName(newName);
        product.setPrice(newPrice);

        if (product instanceof PhysicalProduct) {
            ((PhysicalProduct) product).setWeight(newExtraValue);
        } else if (product instanceof DigitalProduct) {
            ((DigitalProduct) product).setSizeInMB(newExtraValue);
        }

        return true;
    }

    public boolean deleteProduct(String id) {
        Product product = findById(id);
        if (product == null) {
            return false;
        }
        return productList.remove(product);
    }
}