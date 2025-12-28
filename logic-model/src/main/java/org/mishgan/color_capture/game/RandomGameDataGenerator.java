package org.mishgan.color_capture.game;

import org.mishgan.color_capture.player.Player;
import org.mishgan.color_capture.settings.GameSettings;

import java.util.Random;

public class RandomGameDataGenerator implements GameDataGenerator {

    @Override
    public GameData generate(GameSettings gameSettings) {

        var gameField = GameField.create(gameSettings);

        var playerCount = gameSettings.getPlayerCount();
        var neutralColorCount = gameSettings.getNeutralColorsCount();

        var random = new Random();
        var lowBound = playerCount;
        var highBound = neutralColorCount + lowBound;

        for (int i = 0; i < gameField.getXSize(); i++) {
            for (int j = 0; j < gameField.getYSize(); j++) {
                var randomColor = random.nextInt(lowBound, highBound);
                gameField.setColor(i, j, (byte) randomColor);
            }
        }

        // initialize player start positions
        for (Player player : gameSettings.getPlayers()) {
            var startPosition = player.getStartPosition();
            gameField.setColor(startPosition.x(), startPosition.y(), player.getColorNumber());
        }

        return new GameData(gameSettings, gameField);
    }
}
