package org.mishgan.color_capture.settings;

import org.mishgan.color_capture.util.ColorUtils;

import java.awt.*;
import java.util.Random;

public class ViewGameSettings {
    private Color[] colors;

    public ViewGameSettings(int playerCount, int neutralColorsCount) {
        this.colors = new Color[playerCount + neutralColorsCount];
    }

    public void setColor(int colorNumber, Color color) {
        colors[colorNumber] = color;
    }

    /**
     * Generates random colors for not filled colors
     */
    public void generateRandomColors() {
        var random = new Random();

        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == null) {
                Color currentColor;
                do {
                    currentColor = ColorUtils.createRandomColor(random);
                } while (hasColor(currentColor));
                colors[i] = currentColor;
            }
        }
    }

    private boolean hasColor(Color color) {
        for (Color value : colors) {
            if (value != null && ColorUtils.isEqualColors(value, color)) {
                return true;
            }
        }
        return false;
    }
}
