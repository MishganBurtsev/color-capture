package org.mishgan.color_capture;

public record Point(int x, int y) {

    /**
     * Check that this point is in rect (0, 0, rect.x, rect.y)
     * @param rect
     * @return
     */
    public boolean isInRect(Point rect) {
        return rect != null && rect.x >= x && rect.y >= y;
    }

    public static Point create(int x, int y) {
        if (x >= 0 && y >= 0) {
            return new Point(x, y);
        }
        return null;
    }
}
