package org.mishgan.color_capture.player.input_event;

import org.mishgan.color_capture.controller.GameController;
import org.mishgan.color_capture.player.Player;

public record ChooseColorPlayerInputEvent(byte chosenColor) implements PlayerInputEvent {
    @Override
    public void update(GameController gameController) {
        Player currentPlayer = gameController.getCurrentPlayer();
        currentPlayer.chooseColor(chosenColor, gameController.getGameData());
    }
}
