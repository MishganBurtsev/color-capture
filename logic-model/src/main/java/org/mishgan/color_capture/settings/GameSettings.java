package org.mishgan.color_capture.settings;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.player.Player;

import java.util.List;

public class GameSettings {

    private final Point mapSize;
    private final List<Player> players;

    GameSettings(List<Player> players, Point mapSize) {
        this.players = players;
        this.mapSize = mapSize;
    }

    public Point getMapSize() {
        return mapSize;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
