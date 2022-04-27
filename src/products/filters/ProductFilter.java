package products.filters;

import products.Product;

public interface ProductFilter {
    public boolean accept(Product product);
}
