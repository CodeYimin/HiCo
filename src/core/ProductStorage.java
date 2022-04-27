package core;

import products.Product;
import products.creators.ProductCreator;
import products.filters.ProductFilter;
import products.filters.ProductIdFilter;
import utils.ArrayUtils;

public class ProductStorage {
    final FileManager fileManager;
    final ProductCreator[] productCreators;

    public ProductStorage(FileManager fileManager, ProductCreator[] productCreators) {
        this.fileManager = fileManager;
        this.productCreators = productCreators;
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

    private static String encodeData(String[] data) {
        String[] encodedData = ArrayUtils.replaceAll(data, ",", "\\\\,");
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

    public void addProduct(Product product) throws Exception {
        String storageString = encodeProduct(product);
        fileManager.addLine(storageString);
    }

    public Product removeProduct(Product product) throws Exception {
        Product[] products = getProducts();
        Product removedProduct = null;
        for (Product p : products) {
            if (removedProduct == null && p.equals(product)) {
                removedProduct = p;
                products = ArrayUtils.withoutElement(products, p);
            }
        }
        if (removedProduct == null) {
            return null;
        }

        String[] encodedProducts = new String[products.length];
        for (int i = 0; i < encodedProducts.length; i++) {
            encodedProducts[i] = encodeProduct(products[i]);
        }
        fileManager.writeLines(encodedProducts);

        return removedProduct;
    }

    public Product[] getProducts() throws Exception {
        String[] productStrings = fileManager.readLines();

        Product[] products = new Product[productStrings.length];

        for (int i = 0; i < productStrings.length; i++) {
            products[i] = decodeProduct(productStrings[i]);
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
                filteredProducts = ArrayUtils.withElement(filteredProducts, product);
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

    public void syncProduct(Product product) throws Exception {
        if (removeProduct(product) != null) {
            addProduct(product);
        }
    }

    public int numOfProducts() throws Exception {
        return getProducts().length;
    }
}
