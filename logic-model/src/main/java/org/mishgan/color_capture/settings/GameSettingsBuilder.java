package org.mishgan.color_capture.settings;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameSettingsBuilder {
    public static final byte MAX_PLAYER_COUNT = 8;
    public static final Point MAX_MAP_SIZE = Point.create(255, 255);

    public static final String ADD_NULL_PLAYER_ERROR = "Cannot add null player";
    public static final String MAX_PLAYER_REACHED = "Maximum player count reached";

    private List<Player> players = new ArrayList<>();
    private Point mapSize;

    public GameSettingsBuilder addPlayer(Player player) {
        if (player == null) {
            throw new GameSettingsException(ADD_NULL_PLAYER_ERROR);
        }

        if (players.size() < MAX_PLAYER_COUNT) {
            players.add(player);
        } else {
            throw new GameSettingsException(MAX_PLAYER_REACHED);
        }
        return this;
    }

    public GameSettingsBuilder mapSize(Point mapSize) {
        if (mapSize != null && mapSize.isInRect(MAX_MAP_SIZE)) {
            this.mapSize = mapSize;
        }
        return this;
    }

    public GameSettings build() {
        if (mapSize == null) {
            throw new GameSettingsException("Map size is null");
        }

        for (byte i = 0; i < players.size(); i++) {
            var currentPlayer = players.get(i);

            // update player color
            currentPlayer.colorNumber(i);

            if (currentPlayer.getStartPosition() == null ||
                    !currentPlayer.getStartPosition().isInRect(mapSize)
            ) {
                throw new GameSettingsException("Player " + i + " start position is invalid");
            }
        }

        return new GameSettings(players, mapSize);
    }
}
