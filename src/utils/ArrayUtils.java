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

    public static <T> T[] withNewElement(T[] array, T value) {
        T[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = value;
        return newArray;
    }

    public static <T> T[] withRemovedElement(T[] array, T value) {
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
}
