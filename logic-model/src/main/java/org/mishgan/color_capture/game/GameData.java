package org.mishgan.color_capture.game;

import org.mishgan.color_capture.player.Player;
import org.mishgan.color_capture.settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    private final GameSettings gameSettings;
    private final GameField gameField;
    private int currentMoveNumber = 1;
    private final List<Player> players;

    public GameData(GameSettings gameSettings, GameField gameField) {
        this.gameSettings = gameSettings;
        this.gameField = gameField;
        // init players
        players = new ArrayList<>();
        gameSettings.getPlayerStartConfigurations().forEach(playerStartConfiguration -> {
            players.add(new Player(playerStartConfiguration));
        });
    }

    public GameField getGameField() {
        return gameField;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
