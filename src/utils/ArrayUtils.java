package utils;

import java.util.Arrays;

public class ArrayUtils {
    public static <T> boolean includes(T[] array, T value) {
        for (T arrayValue : array) {
            if (arrayValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] withElementAdded(T[] array, T value) {
        T[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = value;
        return newArray;
    }

    public static <T> T[] withElementRemoved(T[] array, T value) {
        T[] newArray = Arrays.copyOf(array, array.length - 1);
        int newArrayIndex = 0;
        for (T arrayValue : array) {
            if (!arrayValue.equals(value)) {
                newArray[newArrayIndex] = arrayValue;
                newArrayIndex++;
            }
        }
        return newArray;
    }

    public static <T> T[] concat(T[] array1, T[] array2) {
        T[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        for (int i = 0; i < array2.length; i++) {
            newArray[array1.length + i] = array2[i];
        }
        return newArray;
    }

    public static String[] replaceAll(String[] array, String oldValue, String newValue) {
        String[] newArray = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i].replaceAll(oldValue, newValue);
        }
        return newArray;
    }

    public static String join(String[] array, String separator) {
        String string = "";
        for (int i = 0; i < array.length; i++) {
            string += array[i];
            if (i < array.length - 1) {
                string += separator;
            }
        }
        return string;
    }
}
