package products.filters;

import products.Product;

public class ProductIdFilter implements ProductFilter {
    private final int id;

    public ProductIdFilter(int id) {
        this.id = id;
    }

    @Override
    public boolean accept(Product product) {
        return product.getId() == id;
    }
}