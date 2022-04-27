package storage;

import core.FileManager;
import products.Product;
import products.ProductFilter;
import utils.ArrayUtils;

public class ProductStorage {
    final FileManager fileManager;
    final ProductCreator[] productCreators;

    public ProductStorage(FileManager fileManager, ProductCreator[] productCreators) {
        this.fileManager = fileManager;
        this.productCreators = productCreators;
    }

    public int getMaxProductId() {
        int maxProductId = 0;
        for (Product product : getProducts()) {
            if (product.getId() > maxProductId) {
                maxProductId = product.getId();
            }
        }
        return maxProductId;
    }

    private static String encodeData(String[] data) {
        String[] encodedData = ArrayUtils.replaceAll(data, ",", "\\,");
        String encodedString = ArrayUtils.join(encodedData, ",");
        return encodedString;
    }

    private static String[] decodeData(String data) {
        String[] decodedData = data.split("(?<!\\\\),");
        decodedData = ArrayUtils.replaceAll(decodedData, "\\\\,", ",");
        return decodedData;
    }

    private String encodeProduct(Product product) {
        return encodeData(product.toStorageData());
    }

    private Product decodeProduct(String storageString) {
        String[] storageData = decodeData(storageString);
        for (ProductCreator productCreator : productCreators) {
            if (productCreator.canCreateFromStorageData(storageData)) {
                return productCreator.createFromStorageData(storageData);
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        String storageString = encodeProduct(product);
        fileManager.addLine(storageString);
    }

    public Product removeProduct(int id) {
        Product productToRemove = getProduct(id);
        if (productToRemove != null) {
            fileManager.removeLine(encodeProduct(productToRemove));
            return productToRemove;
        } else {
            return null;
        }
    }

    public Product removeProduct(Product product) {
        return removeProduct(product.getId());
    }

    public Product[] getProducts() {
        String[] productStrings = fileManager.readLines();
        Product[] products = new Product[productStrings.length];

        for (int i = 0; i < productStrings.length; i++) {
            products[i] = decodeProduct(productStrings[i]);
        }

        return products;
    }

    public Product[] getProducts(ProductFilter[] filters) {
        Product[] products = getProducts();
        Product[] filteredProducts = {};

        for (Product product : products) {
            boolean isFiltered = false;
            for (ProductFilter filter : filters) {
                if (!isFiltered && filter != null && !filter.accept(product)) {
                    isFiltered = true;
                }
            }
            if (!isFiltered) {
                filteredProducts = ArrayUtils.withElementAdded(filteredProducts, product);
            }
        }

        return filteredProducts;
    }

    public Product[] getProducts(ProductFilter filter) {
        return getProducts(new ProductFilter[] { filter });
    }

    public Product getProduct(ProductFilter[] filters) {
        Product[] filteredProducts = getProducts(filters);
        if (filteredProducts.length == 0) {
            return null;
        } else {
            return filteredProducts[0];
        }
    }

    public Product getProduct(ProductFilter filter) {
        return getProduct(new ProductFilter[] { filter });
    }

    public Product getProduct(int id) {
        return getProduct(new Product.IdFilter(id));
    }

    public Product getProduct(Product product) {
        return getProduct(product.getId());
    }

    public boolean hasProduct(Product product) {
        return getProduct(product) != null;
    }

    public void syncProduct(Product product) {
        if (hasProduct(product)) {
            removeProduct(product);
        }
        addProduct(product);
    }

    public int size() {
        return getProducts().length;
    }

    @Override
    public String toString() {
        String output = "";
        for (Product product : getProducts()) {
            output += "- " + product + "\n";
        }
        // Remove last new line character
        output = output.substring(0, output.length() - 1);
        return output;
    }
}
