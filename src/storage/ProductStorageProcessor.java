package storage;

import products.Product;

public interface ProductStorageProcessor {
    public boolean canProcessProduct(Product product);

    public boolean canProcessString(String storageString);

    public String productToString(Product product);

    public Product stringToProduct(String storageString);
}
