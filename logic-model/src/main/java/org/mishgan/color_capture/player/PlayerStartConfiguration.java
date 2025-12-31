package org.mishgan.color_capture.player;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.player.strategy.PlayerStrategy;

public class PlayerStartConfiguration {
    private byte colorNumber;
    private Point startPosition;
    private PlayerStrategy playerStrategy;

    public PlayerStartConfiguration colorNumber(int colorNumber) {
        this.colorNumber = (byte) colorNumber;
        return this;
    }

    public byte getColorNumber() {
        return colorNumber;
    }

    public PlayerStartConfiguration startPosition(Point startPosition) {
        this.startPosition = startPosition;
        return this;
    }

    public Point getStartPosition() {
        return this.startPosition;
    }

    public PlayerStartConfiguration playerStrategy(PlayerStrategy playerStrategy) {
        this.playerStrategy = playerStrategy;
        return this;
    }

    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }
}
