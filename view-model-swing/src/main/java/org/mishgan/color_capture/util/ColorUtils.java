package org.mishgan.color_capture.util;

import java.awt.*;
import java.util.Random;

public class ColorUtils {

    private ColorUtils() {
    }

    public static Color createRandomColor(Random random) {
        int randomRed = random.nextInt(256);
        int randomGreen = random.nextInt(256);
        int randomBlue = random.nextInt(256);

        return new Color(randomRed, randomGreen, randomBlue);
    }

    public static boolean isEqualColors(Color c1, Color c2) {
        return c1.getRed() == c2.getRed() &&
                c1.getGreen() == c2.getGreen() &&
                c1.getBlue() == c2.getBlue();
    }
}
