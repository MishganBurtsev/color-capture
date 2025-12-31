package org.mishgan.color_capture.player.input_event;

import org.mishgan.color_capture.controller.GameController;

public class FinishMoveEvent implements PlayerInputEvent {
    @Override
    public void update(GameController gameController) {
        gameController.setCurrentMoveFinished();
    }
}
