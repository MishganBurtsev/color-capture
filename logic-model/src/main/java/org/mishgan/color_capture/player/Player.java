package org.mishgan.color_capture.player;

import org.mishgan.color_capture.Point;

public class Player {
    private byte colorNumber;
    private Point startPosition;

    public Player colorNumber(int colorNumber) {
        this.colorNumber = (byte) colorNumber;
        return this;
    }

    public byte getColorNumber() {
        return colorNumber;
    }

    public Player startPosition(Point startPosition) {
        this.startPosition = startPosition;
        return this;
    }

    public Point getStartPosition() {
        return this.startPosition;
    }
}
