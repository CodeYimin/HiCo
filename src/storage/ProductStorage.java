package storage;

import java.io.File;
import java.util.Arrays;

import products.Product;
import products.ProductFilter;
import products.ProductStatus;
import utils.FileUtils;
import utils.IdGenerator;

public class ProductStorage {
    final File file;
    final ProductStorageProcessor[] processors;

    public ProductStorage(File file, ProductStorageProcessor[] processors) {
        this.file = file;
        this.processors = processors;

        int maxProductId = 0;
        for (Product product : getProducts()) {
            if (product.getId() > maxProductId) {
                maxProductId = product.getId();
            }
        }
        IdGenerator.setLastUsedId(maxProductId);
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
        FileUtils.addLine(file, productToStorageString(product));
    }

    public Product removeProduct(Product product) {
        Product productToRemove = getProduct(new Product.IdFilter(product.getId()));
        if (productToRemove != null) {
            FileUtils.removeLine(file, productToStorageString(productToRemove));
            return productToRemove;
        } else {
            return null;
        }
    }

    public Product[] getProducts() {
        String[] productStrings = FileUtils.readLines(file);
        Product[] products = new Product[productStrings.length];

        for (int i = 0; i < productStrings.length; i++) {
            products[i] = storageStringToProduct(productStrings[i]);
        }

        return products;
    }

    public Product[] getProducts(ProductFilter[] filters) {
        Product[] products = getProducts();
        Product[] filteredProducts = new Product[products.length];
        int filteredProductsCount = 0;

        for (Product product : products) {
            boolean isFiltered = false;
            for (ProductFilter filter : filters) {
                if (!isFiltered && filter != null && !filter.accept(product)) {
                    isFiltered = true;
                }
            }
            if (!isFiltered) {
                filteredProducts[filteredProductsCount++] = product;
            }
        }

        // Remove empty elements
        Product[] filteredProductsResult = Arrays.copyOfRange(filteredProducts, 0, filteredProductsCount);
        return filteredProductsResult;
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

    public boolean reserveProduct(int id) {
        ProductFilter[] filters = { new Product.IdFilter(id), new Product.StatusFilter(ProductStatus.AVAILABLE) };
        Product productToReserve = getProduct(filters);

        if (productToReserve == null) {
            return true;
        }

        productToReserve.setStatus(ProductStatus.RESERVED);
        return true;
    }

    public boolean unreserveProduct(int id) {
        ProductFilter[] filters = { new Product.IdFilter(id), new Product.StatusFilter(ProductStatus.RESERVED) };
        Product productToUnreserve = getProduct(filters);

        if (productToUnreserve == null) {
            return true;
        }

        productToUnreserve.setStatus(ProductStatus.AVAILABLE);
        return true;
    }

    public boolean hasProduct(Product product) {
        return FileUtils.includesLine(file, productToStorageString(product));
    }

    public int size() {
        return getProducts().length;
    }

    @Override
    public String toString() {
        String output = "";
        for (Product product : getProducts()) {
            output += product + "\n";
        }
        return output;
    }
}
