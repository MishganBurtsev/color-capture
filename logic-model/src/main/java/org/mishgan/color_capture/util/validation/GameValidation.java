package org.mishgan.color_capture.util.validation;

import java.util.Collection;

public class GameValidation {

    private GameValidation() {
    }

    public static void checkNotNull(Object object) {
        checkNotNull(object, "Object");
    }

    public static void checkNotNull(Object object, String objectName) {
        if (object == null) {
            throw new IllegalArgumentException(objectName + " is null!");
        }
    }

    public static <T> void checkNotEmpty(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty!");
        }
    }
}
