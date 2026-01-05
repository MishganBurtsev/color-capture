package org.mishgan.color_capture.game;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.settings.GameSettings;
import org.mishgan.color_capture.util.MyPair;

import java.util.*;
import java.util.stream.Collectors;

import static org.mishgan.color_capture.util.validation.GameValidation.checkNotNull;

public class GameField {

    private final byte[][] field;
    private final int playerCount;

    private GameField(Point mapSize, int playerCount) {
        this.field = new byte[mapSize.x()][mapSize.y()];
        this.playerCount = playerCount;
    }

    public static GameField create(GameSettings gameSettings) {
        var mapSize = gameSettings.getMapSize();

        return new GameField(mapSize, gameSettings.getPlayerCount());
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

    /**
     * Метод проверяет, являеется ли данный {@code point} внутренней точкой.
     * Внутренняя - нет соседей-нейтральных точек
     * @param point
     * @return
     */
    public boolean isInternalPlayerPoint(Point point) {
        var neutralNeighbours = getNeutralColorNeighbours(point);
        return neutralNeighbours.isEmpty();
    }

    /**
     * Возвращает мэп с данными о соседних нейтральных клетках, которые могут быть захвачены
     * @param point клетка, соседей которых ищем
     * @return мэп со списком клеток, ключ - нейтральный цвет
     */
    public Map<Byte, Set<Point>> getNeutralColorNeighbours(Point point) {
        var differentColorNeighbours = getDifferentColorNeighbours(point);
        var entrySetIterator = differentColorNeighbours.entrySet().iterator();
        while (entrySetIterator.hasNext()) {
            var entry = entrySetIterator.next();
            var color = entry.getKey();
            if (GameFieldUtils.isPlayerColor(color, playerCount)) {
                entrySetIterator.remove();
            }
        }
        return differentColorNeighbours;
    }

    public Map<Byte, Set<Point>> getDifferentColorNeighbours(Point point) {
        checkNotNull(point);

        var pointColor = getColor(point);
        return getNeighbors(point).stream()
                .map(neighbour -> new MyPair<>(getColor(neighbour), neighbour))
                .filter(myPair -> myPair.key() != pointColor)
                .collect(Collectors.toMap(
                        MyPair::key,
                        myPair -> {
                            var result = new HashSet<Point>();
                            result.add(myPair.value());
                            return result;
                        },
                        (pointSet1, pointSet2) -> {
                            pointSet1.addAll(pointSet2);
                            return pointSet1;
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
