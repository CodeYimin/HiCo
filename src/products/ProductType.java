package products;

public class ProductType {
    public static final String BODYWEAR = "Bodywear";
    public static final String CAR = "Car";

    public static String[] getAllTypes() {
        return new String[] { BODYWEAR, CAR };
    }

    public static String getType(String type) {
        for (String t : getAllTypes()) {
            if (t.equalsIgnoreCase(type.trim())) {
                return type;
            }
        }

        return null;
    }
}
