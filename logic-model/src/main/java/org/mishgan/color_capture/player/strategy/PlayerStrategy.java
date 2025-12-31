package org.mishgan.color_capture.player.strategy;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.player.input_event.PlayerInputEvent;

public abstract class PlayerStrategy {
    public abstract PlayerInputEvent take(GameData gameData);
}
