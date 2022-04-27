package products;

public abstract class Product {
    private final int id;
    private String status;
    private String name;
    private String description;
    private double price;

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

    public abstract String[] toStorageData();

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Status: " + status +
                " | Name: " + name + " | Description: " + description + " | Price: $" + price;
    }
}
