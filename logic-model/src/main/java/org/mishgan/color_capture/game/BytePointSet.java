package org.mishgan.color_capture.game;

import org.mishgan.color_capture.Point;

import java.util.Set;

import static org.mishgan.color_capture.util.ValidationUtils.checkNotNull;

public class BytePointSet {
    /**
     * Byte array for storing point coords. bytePoints[i][0] - x, bytePoints[i][1] - y
     */
    private final byte[][] bytePoints;

    private BytePointSet(Set<Point> pointSet) {
        var size = pointSet.size();
        this.bytePoints = new byte[size][2];

        int i = 0;
        for (Point point : pointSet) {
            bytePoints[i][0] = (byte) point.x();
            bytePoints[i][1] = (byte) point.y();
            i++;
        }
    }

    public boolean containsPoint(Point point) {
        checkNotNull(point);
        for (int i = 0; i < bytePoints.length; i++) {
            if (bytePoints[i][0] == point.x() && bytePoints[i][1] == point.y()) {
                return true;
            }
        }
        return false;
    }

    public static BytePointSet create(Set<Point> pointSet) {
        if (pointSet == null || pointSet.isEmpty()) {
            throw new IllegalArgumentException("Point collection is empty");
        }

        return new BytePointSet(pointSet);
    }
}
