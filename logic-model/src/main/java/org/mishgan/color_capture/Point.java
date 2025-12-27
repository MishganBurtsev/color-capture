package org.mishgan.color_capture;

public class Point {
    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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
