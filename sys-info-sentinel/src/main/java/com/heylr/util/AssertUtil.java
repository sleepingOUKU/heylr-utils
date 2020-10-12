package com.heylr.util;

import java.util.Collection;

/**
 * Util class for checking arguments
 *
 * @author heylr
 * @date 2020-8-14 14:05:16
 */
public class AssertUtil {


    private AssertUtil() {
    }

    public static void notEmpty(String string, String message) {
        if (StringUtil.isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void notEmpty(Collection<?> collection, String message) {
        if (collection != null && collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertNotBlank(String string, String message) {
        if (StringUtil.isBlank(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertState(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
