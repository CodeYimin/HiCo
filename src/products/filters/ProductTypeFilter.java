package products.filters;

import products.Product;

public class ProductTypeFilter implements ProductFilter {
    private final String type;

    public ProductTypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean accept(Product product) {
        return product.getType().equals(type);
    }
}