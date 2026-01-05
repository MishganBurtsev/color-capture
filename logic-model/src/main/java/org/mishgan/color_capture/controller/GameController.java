package org.mishgan.color_capture.controller;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.player.Player;

import static org.mishgan.color_capture.util.validation.GameValidation.checkNotNull;

public class GameController {
    private final GameData gameData;

    private Player currentPlayer;
    private boolean isCurrentMoveFinished;
    private boolean isGameRunning;

    public GameController(GameData gameData) {
        checkNotNull(gameData, "gameData");
        this.gameData = gameData;
    }

    public void startGame() {
        isGameRunning = true;

        while (!isGameFinished()) {
            for (Player player : gameData.getPlayers()) {
                currentPlayer = player;
                isCurrentMoveFinished = false;
                var playerStrategy = player.getPlayerStartConfiguration().getPlayerStrategy();

                player.calculatePossibleMoves(gameData);

                while (!isCurrentMoveFinished) {
                    var playerInputEvent = playerStrategy.take(gameData);
                    playerInputEvent.update(this);
                }
            }
        }
    }

    public void stopGame() {
        isGameRunning = false;
    }

    public void setCurrentMoveFinished() {
        isCurrentMoveFinished = true;
    }

    private boolean isGameFinished() {
        // TODO check for winners or draw
        return isGameRunning;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameData getGameData() {
        return gameData;
    }
}
