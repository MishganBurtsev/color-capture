package org.mishgan.color_capture.settings;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.player.PlayerStartConfiguration;

import java.util.List;

public class GameSettings {

    private final Point mapSize;

    private final List<PlayerStartConfiguration> playerStartConfigurations;
    private final int playerCount;

    private final int neutralColorsCount;

    GameSettings(List<PlayerStartConfiguration> playerStartConfigurations, Point mapSize, int neutralColorsCount) {
        this.playerStartConfigurations = playerStartConfigurations;
        this.playerCount = playerStartConfigurations.size();
        this.mapSize = mapSize;
        this.neutralColorsCount = neutralColorsCount;
    }

    public Point getMapSize() {
        return mapSize;
    }

    public List<PlayerStartConfiguration> getPlayerStartConfigurations() {
        return playerStartConfigurations;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getNeutralColorsCount() {
        return neutralColorsCount;
    }
}
