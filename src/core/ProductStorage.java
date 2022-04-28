package core;

import products.Product;
import products.filters.ProductFilter;
import products.filters.ProductIdFilter;
import utils.ArrayUtils;

public class ProductStorage {
    final FileManager fileManager;

    public ProductStorage(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public int getMaxProductId() throws Exception {
        int maxProductId = 0;
        for (Product product : getProducts()) {
            if (product.getId() > maxProductId) {
                maxProductId = product.getId();
            }
        }
        return maxProductId;
    }

    public void addProduct(Product product) throws Exception {
        fileManager.addLine(product.toStorageString());
    }

    public Product removeProduct(Product product) throws Exception {
        Product[] products = getProducts();

        // Find product to remove and remove it
        Product removedProduct = null;
        for (Product p : products) {
            if (removedProduct == null && p.equals(product)) {
                removedProduct = p;
                products = ArrayUtils.slice(products, removedProduct);
            }
        }
        if (removedProduct == null) {
            return null;
        }

        // Convert the updated products array
        // to string format to override the file with the new content
        String[] productStorageStrings = new String[products.length];
        for (int i = 0; i < productStorageStrings.length; i++) {
            productStorageStrings[i] = products[i].toStorageString();
        }
        fileManager.writeLines(productStorageStrings);

        return removedProduct;
    }

    public Product[] getProducts() throws Exception {
        // Get the raw lines from the file. Each line represents a product.
        String[] productStorageStrings = fileManager.readLines();

        Product[] products = new Product[productStorageStrings.length];
        for (int i = 0; i < productStorageStrings.length; i++) {
            // Convert the raw line to a product object in memory.
            products[i] = Product.fromStorageString(productStorageStrings[i]);
        }

        return products;
    }

    public Product[] getProducts(ProductFilter[] filters) throws Exception {
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
                filteredProducts = ArrayUtils.concat(filteredProducts, product);
            }
        }

        return filteredProducts;
    }

    public Product[] getProducts(ProductFilter filter) throws Exception {
        return getProducts(new ProductFilter[] { filter });
    }

    public Product getProduct(ProductFilter[] filters) throws Exception {
        Product[] filteredProducts = getProducts(filters);
        if (filteredProducts.length == 0) {
            return null;
        } else {
            return filteredProducts[0];
        }
    }

    public Product getProduct(ProductFilter filter) throws Exception {
        return getProduct(new ProductFilter[] { filter });
    }

    public Product getProduct(int id) throws Exception {
        return getProduct(new ProductIdFilter(id));
    }

    public Product getProduct(Product product) throws Exception {
        return getProduct(product.getId());
    }

    public boolean hasProduct(Product product) throws Exception {
        return getProduct(product) != null;
    }

    /**
     * Syncs the product in the storage with the product in memory.
     * 
     * @param product
     * @throws Exception
     */
    public void syncProduct(Product product) throws Exception {
        if (removeProduct(product) != null) {
            addProduct(product);
        }
    }

    public int numOfProducts() throws Exception {
        return getProducts().length;
    }
}
