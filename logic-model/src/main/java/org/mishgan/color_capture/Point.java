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

    public Point getNeighbor(Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction is null!");
        }

        return Point.create(x + direction.incX, y + direction.incY);
    }

    public enum Direction {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        private final int incX;
        private final int incY;

        Direction(int incX, int incY) {
            this.incX = incX;
            this.incY = incY;
        }
    }
}
