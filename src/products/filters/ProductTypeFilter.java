package products.filters;

import products.Bodywear;
import products.Car;
import products.Electronic;
import products.Product;
import products.constants.ProductType;

public class ProductTypeFilter implements ProductFilter {
    private final String type;

    public ProductTypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean accept(Product product) {
        if (type.equals(ProductType.CAR)) {
            return product instanceof Car;
        } else if (type.equals(ProductType.BODYWEAR)) {
            return product instanceof Bodywear;
        } else if (type.equals(ProductType.ELECTRONIC)) {
            return product instanceof Electronic;
        } else {
            return false;
        }
    }
}