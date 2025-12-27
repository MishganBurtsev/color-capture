package org.mishgan.color_capture.game;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.settings.GameSettings;

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

    public void setColor(int x, int y, byte color) {
        field[x][y] = color;
    }

    public int getXSize() {
        return field.length;
    }

    public int getYSize() {
        return field[0].length;
    }
}
