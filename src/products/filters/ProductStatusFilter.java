package products.filters;

import products.Product;

public class ProductStatusFilter implements ProductFilter {
    private final String status;

    public ProductStatusFilter(String status) {
        this.status = status;
    }

    @Override
    public boolean accept(Product product) {
        return product.getStatus().equalsIgnoreCase(status);
    }
}