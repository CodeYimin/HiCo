package products;

import utils.ArrayUtils;

public abstract class Product {
    private final int id;
    private final String type;
    private String status;
    private String name;
    private String description;
    private double price;

    public Product(int id, String type, String name, String status, String description, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
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

    public abstract String[] extraStorageData();

    public final String toStorageData() {
        String[] storageFields = {
                String.valueOf(getId()),
                getType(),
                getName(),
                getStatus(),
                getDescription(),
                String.valueOf(getPrice())
        };
        String[] fullStorageFields = ArrayUtils.concat(storageFields, extraStorageData());

        String[] encodedFields = ArrayUtils.replaceAll(fullStorageFields, ",", "\\\\,");
        String encodedString = ArrayUtils.join(encodedFields, ",");

        return encodedString;
    }

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
        return "ID: " + id
                + " | Type: " + type
                + " | Name: " + name
                + " | Status: " + status
                + " | Description: " + description
                + " | Price: $" + price;
    }
}
