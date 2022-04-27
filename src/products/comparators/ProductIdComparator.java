package products.comparators;

import java.util.Comparator;

import products.Product;

public class ProductIdComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Integer.compare(p1.getId(), p2.getId());
    }
}