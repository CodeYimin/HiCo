package products.constants;

public class ProductStatus {
    public static final String REQUESTED = "REQUESTED";
    public static final String AVAILABLE = "AVAILABLE";
    public static final String ON_HOLD = "ON_HOLD";
    public static final String SOLD = "SOLD";
    public static final String RETURNED = "RETURNED";

    public static String[] getAllStatuses() {
        return new String[] { REQUESTED, AVAILABLE, ON_HOLD, SOLD, RETURNED };
    }

    public static String fromString(String status) {
        for (String s : getAllStatuses()) {
            if (s.equalsIgnoreCase(status.trim())) {
                return s;
            }
        }
        return null;
    }
}
