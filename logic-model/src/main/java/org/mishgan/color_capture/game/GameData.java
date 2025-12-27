package org.mishgan.color_capture.game;

import org.mishgan.color_capture.settings.GameSettings;

public class GameData {

    private final GameSettings gameSettings;
    private final GameField gameField;

    public GameData(GameSettings gameSettings, GameField gameField) {
        this.gameSettings = gameSettings;
        this.gameField = gameField;
    }
}
