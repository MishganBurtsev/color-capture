package org.mishgan.color_capture.game;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.settings.GameSettings;
import org.mishgan.color_capture.util.MyPair;

import java.util.*;
import java.util.stream.Collectors;

import static org.mishgan.color_capture.util.validation.GameValidation.checkNotNull;

public class GameField {

    private byte[][] field;

    private GameField(Point mapSize) {
        this.field = new byte[mapSize.x()][mapSize.y()];
    }

    public static GameField create(GameSettings gameSettings) {
        var mapSize = gameSettings.getMapSize();

        return new GameField(mapSize);
    }

    public byte getColor(int x, int y) {
        return field[x][y];
    }

    public byte getColor(Point point) {
        checkNotNull(point);
        return field[point.x()][point.y()];
    }

    public void setColor(int x, int y, byte color) {
        field[x][y] = color;
    }

    public int getXSize() {
        return field.length;
    }

    public int getYSize() {
        return field[0].length;
    }

    public List<Point> getNeighbors(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point is null!");
        }

        if (!hasPoint(point)) {
            throw new IllegalArgumentException("Point " + point + " is out of game field area");
        }

        return Arrays.stream(Point.Direction.values())
                .map(point::getNeighbor)
                .filter(Objects::nonNull)
                .toList();
    }

    public Map<Byte, List<Point>> getDifferentColorNeighbours(Point point) {
        checkNotNull(point);

        var pointColor = getColor(point);
        return getNeighbors(point).stream()
                .map(neighbour -> new MyPair<>(getColor(neighbour), neighbour))
                .filter(myPair -> myPair.key() != pointColor)
                .collect(Collectors.toMap(
                        MyPair::key,
                        myPair -> {
                            var result = new ArrayList<Point>();
                            result.add(myPair.value());
                            return result;
                        },
                        (pointList1, pointList2) -> {
                            pointList1.addAll(pointList2);
                            return pointList1;
                        }
                        ));
    }

    public boolean hasPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point is null!");
        }

        return point.x() >= 0 && point.x() < getXSize() &&
                point.y() >= 0 && point.y() < getYSize();
    }

    public void capturePoints(byte color, BytePointSet bytePointSet) {
        byte[][] points = bytePointSet.getBytePoints();
        for (int i = 0; i < points.length; i++) {
            var x = points[i][0];
            var y = points[i][1];

            field[x][y] = color;
        }
    }
}
