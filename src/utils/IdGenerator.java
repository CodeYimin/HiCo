package utils;

public class IdGenerator {
    private static int lastUsedId = 0;

    public static void setLastUsedId(int lastUsedId) {
        IdGenerator.lastUsedId = lastUsedId;
    }

    public static int generateId() {
        return ++lastUsedId;
    }
}
