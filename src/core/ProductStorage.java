package core;

import products.Product;
import products.filters.ProductFilter;
import products.filters.ProductIdFilter;
import utils.ArrayUtils;

/**
 * The product storage acts as an interface between
 * file and memory. This class is responsible for
 * storing, retrieving, and modifying products in file storage.
 * The methods provided only require product instances,
 * and only return product instances, never raw file data.
 */
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

        // Find product to remove and remove it from the product array
        // (removed from memory only at this point)
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

        // Convert the products array into string format and write to file
        String[] productStorageStrings = new String[products.length];
        for (int i = 0; i < productStorageStrings.length; i++) {
            productStorageStrings[i] = products[i].toStorageString();
        }
        fileManager.writeLines(productStorageStrings);

        // Return instance of the removed product
        return removedProduct;
    }

    public Product[] getProducts() throws Exception {
        // Get the raw lines from the file. Each line represents a product.
        String[] productStorageStrings = fileManager.readLines();

        // Convert each raw line to a new product instance to load it in memory.
        Product[] products = new Product[productStorageStrings.length];
        for (int i = 0; i < productStorageStrings.length; i++) {
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
     * Syncs a product in file-storage with a product in memory-storage.
     * The product instance data overrides the file stored data.
     * Memory data --> Storage data (Not the other way around)
     * The product to sync is determined by the product id.
     * If the product with the same id is not found,
     * an error is thrown and nothing is updated.
     * 
     * @param product
     * @throws Exception
     */
    public void syncProduct(Product product) throws Exception {
        if (removeProduct(product) != null) {
            addProduct(product);
        } else {
            throw new Exception("Product with id " + product.getId() + " not found in file storage.");
        }
    }

    public int numOfProducts() throws Exception {
        return getProducts().length;
    }
}
