package products;

public class ProductStatus {
    public static final String AVAILABLE = "available";
    public static final String RESERVED = "reserved";
    public static final String REQUESTED = "requested";

    public static boolean isValidStatusChange(String fromStatus, String toStatus) {
        return fromStatus.equals(AVAILABLE) && toStatus.equals(RESERVED) || fromStatus.equals(RESERVED)
                && toStatus.equals(AVAILABLE) || fromStatus.equals(REQUESTED) && toStatus.equals(AVAILABLE)
                || fromStatus.equals(AVAILABLE) && toStatus.equals(REQUESTED);
    }
}
