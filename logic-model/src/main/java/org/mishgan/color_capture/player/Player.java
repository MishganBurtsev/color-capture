package org.mishgan.color_capture.player;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.game.BytePointSet;
import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.game.GameFieldUtils;
import org.mishgan.color_capture.util.validation.GameException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mishgan.color_capture.util.validation.GameValidation.checkNotNull;

public class Player {
    private final PlayerStartConfiguration playerStartConfiguration;
    /**
     * Множество клеток игрока, которые граничат с клетками другого цвета (нейтральными)
     */
    private BytePointSet border;
    /**
     * K - color, V - set of points of that color
     */
    private final Map<Byte, BytePointSet> possibleMoves = new HashMap<>();

    public Player(PlayerStartConfiguration playerStartConfiguration) {
        checkNotNull(playerStartConfiguration);

        this.playerStartConfiguration = playerStartConfiguration;
        this.border = BytePointSet.create(Set.of(playerStartConfiguration.getStartPosition()));
    }

    public PlayerStartConfiguration getPlayerStartConfiguration() {
        return playerStartConfiguration;
    }

    public void calculatePossibleMoves(GameData gameData) {
        possibleMoves.clear();

        var gameField = gameData.getGameField();

        Map<Byte, Set<Point>> neutralNeighboursMap = new HashMap<>();

        for (Point point : border.getPointSet()) {
            var pointNeutralNeighbours = gameField.getNeutralColorNeighbours(point);
            pointNeutralNeighbours.forEach((color, pointSet) -> {
                var neutralPointSet = neutralNeighboursMap.computeIfAbsent(color,
                        (newColor) -> new HashSet<>());
                neutralPointSet.addAll(pointSet);
            });
        }

        neutralNeighboursMap.forEach((color, startPointSet) -> {
            Set<Point> moveSet = GameFieldUtils.getOneColorArea(gameField, startPointSet);
            possibleMoves.put(color, BytePointSet.create(moveSet));
        });
    }

    public void chooseColor(byte chosenColor, GameData gameData) {
        var gameField = gameData.getGameField();

        var chosenMove = possibleMoves.get(chosenColor);
        if (chosenMove == null) {
            throw new GameException("Not found move for chosen color!");
        }

        var playerColor = playerStartConfiguration.getColorNumber();
        gameField.capturePoints(playerColor, chosenMove);

        // recalculating border
        var oldBorderPointSet = border.getPointSet();
        var newPointSet = chosenMove.getPointSet();

        var newBorderSet = Stream.concat(oldBorderPointSet.stream(), newPointSet.stream())
                .filter(point -> !gameField.isInternalPlayerPoint(point))
                .collect(Collectors.toSet());

        border = BytePointSet.create(newBorderSet);
    }

    public boolean hasMoveForColor(byte color) {
        return possibleMoves.containsKey(color);
    }

    public boolean hasNoAvailableMoves() {
        return possibleMoves.isEmpty();
    }
}
