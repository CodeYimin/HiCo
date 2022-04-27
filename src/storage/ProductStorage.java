package storage;

import core.FileManager;
import products.Product;
import products.ProductFilter;
import utils.ArrayUtils;

public class ProductStorage {
    final FileManager fileManager;
    final ProductStorageProcessor[] processors;

    public ProductStorage(FileManager fileManager, ProductStorageProcessor[] processors) {
        this.fileManager = fileManager;
        this.processors = processors;
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

    public String productToStorageString(Product product) {
        for (ProductStorageProcessor processor : processors) {
            if (processor.canProcessProduct(product)) {
                return processor.productToString(product);
            }
        }
        return null;
    }

    public Product storageStringToProduct(String storageString) {
        for (ProductStorageProcessor processor : processors) {
            if (processor.canProcessString(storageString)) {
                return processor.stringToProduct(storageString);
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        fileManager.addLine(productToStorageString(product));
    }

    public Product removeProduct(int id) {
        Product productToRemove = getProduct(id);
        if (productToRemove != null) {
            fileManager.removeLine(productToStorageString(productToRemove));
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
            products[i] = storageStringToProduct(productStrings[i]);
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
