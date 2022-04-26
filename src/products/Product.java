package products;

import java.util.Comparator;

import utils.IdGenerator;

public abstract class Product {
    private final int id;
    private String status;
    private String name;
    private String description;
    private double price;

    public Product(String status, String name, String description, double price) {
        this.id = IdGenerator.generateId();
        this.status = status;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(int id, String status, String name, String description, double price) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Status: " + status +
                " | Name: " + name + " | Description: " + description + " | Price: $" + price;
    }

    public static class PriceComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getPrice(), p2.getPrice());
        }
    }

    public static class NameComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }

    public static class IdComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Integer.compare(p1.getId(), p2.getId());
        }
    }

    public static class IdFilter implements ProductFilter {
        private final int id;

        public IdFilter(int id) {
            this.id = id;
        }

        @Override
        public boolean accept(Product product) {
            return product.getId() == id;
        }
    }

    public static class StatusFilter implements ProductFilter {
        private final String status;

        public StatusFilter(String status) {
            this.status = status;
        }

        @Override
        public boolean accept(Product product) {
            return product.getStatus().equals(status);
        }
    }

    public static class TypeFilter<T extends Product> implements ProductFilter {
        private final Class<T> type;

        public TypeFilter(Class<T> type) {
            this.type = type;
        }

        @Override
        public boolean accept(Product product) {
            return type.isInstance(product);
        }
    }
}
