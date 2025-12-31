package org.mishgan.color_capture.player;

import org.mishgan.color_capture.game.BytePointSet;
import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.util.validation.GameException;

import java.util.Map;
import java.util.Set;

import static org.mishgan.color_capture.util.validation.GameValidation.checkNotNull;

public class Player {
    private final PlayerStartConfiguration playerStartConfiguration;
    private BytePointSet playerBorder;
    /**
     * K - color, V - set of points of that color
     */
    private Map<Byte, BytePointSet> possibleMoves;

    public Player(PlayerStartConfiguration playerStartConfiguration) {
        checkNotNull(playerStartConfiguration);

        this.playerStartConfiguration = playerStartConfiguration;
        this.playerBorder = BytePointSet.create(Set.of(playerStartConfiguration.getStartPosition()));
    }

    public PlayerStartConfiguration getPlayerStartConfiguration() {
        return playerStartConfiguration;
    }

    public void calculatePossibleMoves() {

    }

    public void chooseColor(byte chosenColor, GameData gameData) {
        var chosenMove = possibleMoves.get(chosenColor);
        if (chosenMove == null) {
            throw new GameException("Not found move for chosen color!");
        }

        gameData.getGameField().capturePoints(chosenColor, chosenMove);
        // TODO recalculate border
    }
}
