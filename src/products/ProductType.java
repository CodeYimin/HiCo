package products;

public class ProductType {
    public static final String BODYWEAR = "Bodywear";
    public static final String CAR = "Car";
    public static final String ELECTRONIC = "Electronic";

    public static String[] getAllTypes() {
        return new String[] { BODYWEAR, CAR, ELECTRONIC };
    }

    public static String fromString(String type) {
        for (String t : getAllTypes()) {
            if (t.equalsIgnoreCase(type.trim())) {
                return t;
            }
        }

        return null;
    }
}
