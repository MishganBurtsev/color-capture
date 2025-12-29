package org.mishgan.color_capture.util;

import java.util.Collection;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException( "Object is null!");
        }
    }

    public static <T> void checkNotEmpty(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty!");
        }
    }
}
