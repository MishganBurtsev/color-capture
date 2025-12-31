package org.mishgan.color_capture.player.strategy;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.player.input_event.PlayerInputEvent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mishgan.color_capture.util.validation.GameValidation.checkNotNull;

public class UiHumanPlayerStrategy extends PlayerStrategy {

    private final BlockingQueue<PlayerInputEvent> inputEvents = new LinkedBlockingQueue<>();

    @Override
    public PlayerInputEvent take(GameData gameData) {
        try {
            return inputEvents.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting event from UIHumanPlayer");
        }
    }

    public void publishEvent(PlayerInputEvent playerInputEvent) {
        checkNotNull(playerInputEvent);
        inputEvents.add(playerInputEvent);
    }
}
