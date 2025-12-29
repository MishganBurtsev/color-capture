package org.mishgan.color_capture.game;

import org.mishgan.color_capture.Point;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mishgan.color_capture.util.ValidationUtils.checkNotEmpty;
import static org.mishgan.color_capture.util.ValidationUtils.checkNotNull;

public class GameFieldUtils {

    private GameFieldUtils() {
    }

    /**
     * Returns points of area of the same color. Area can contain several points.
     * Using graph width search
     *
     * @param gameField
     * @param startPoints startPoints of the area
     * @return
     */
    public Set<Point> getOneColorArea(GameField gameField, Set<Point> startPoints) {
        checkNotNull(gameField);
        checkNotEmpty(startPoints);

        Set<Byte> startPointsColorSet = startPoints.stream()
                .map(gameField::getColor)
                .collect(Collectors.toSet());
        if (startPointsColorSet.size() > 1) {
            throw new IllegalArgumentException("Color must be one!");
        }

        byte startPointColorNumber = startPointsColorSet.stream().findFirst().get();

        Set<Point> resultAreaSet = new LinkedHashSet<>(startPoints);
        Queue<Point> pointQueue = new LinkedList<>(startPoints);

        while (!pointQueue.isEmpty()) {
            var currentPoint = pointQueue.remove();
            // getting neighbors
            gameField.getNeighbors(currentPoint).stream()
                    .filter(point -> {
                        var colorNumber = gameField.getColor(point);
                        return startPointColorNumber == colorNumber;
                    })
                    .filter(point -> !resultAreaSet.contains(point))
                    .forEach(point -> {
                        pointQueue.add(point);
                        resultAreaSet.add(point);
                    });
        }

        return resultAreaSet;
    }
}
