package org.mishgan.color_capture.settings;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.player.Player;

import java.util.List;

public class GameSettings {

    private final Point mapSize;

    private final List<Player> players;
    private final int playerCount;

    private final int neutralColorsCount;

    GameSettings(List<Player> players, Point mapSize, int neutralColorsCount) {
        this.players = players;
        this.playerCount = players.size();
        this.mapSize = mapSize;
        this.neutralColorsCount = neutralColorsCount;
    }

    public Point getMapSize() {
        return mapSize;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getNeutralColorsCount() {
        return neutralColorsCount;
    }
}
