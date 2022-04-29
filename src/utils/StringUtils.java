package utils;

public class StringUtils {
    public static String repeat(String string, int times) {
        String result = "";
        for (int i = 0; i < times; i++) {
            result += string;
        }
        return result;
    }

    /**
     * Pads the string with spaces on the right
     * until the total string length is equal to the specified length.
     * 
     * @param string
     * @param length
     * @return
     */
    public static String padRight(String string, int length) {
        return string + repeat(" ", length - string.length());
    }
}
