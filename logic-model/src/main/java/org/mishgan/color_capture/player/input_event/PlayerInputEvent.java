package org.mishgan.color_capture.player.input_event;

import org.mishgan.color_capture.controller.GameController;
import org.mishgan.color_capture.game.GameData;

public interface PlayerInputEvent {
    void update(GameController gameController);
}
