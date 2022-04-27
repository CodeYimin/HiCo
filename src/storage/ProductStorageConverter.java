package storage;

import products.Product;

public interface ProductStorageConverter {
    public boolean canConvertProduct(Product product);

    public boolean canConvertString(String storageString);

    public String convertProduct(Product product);

    public Product convertString(String storageString);
}
