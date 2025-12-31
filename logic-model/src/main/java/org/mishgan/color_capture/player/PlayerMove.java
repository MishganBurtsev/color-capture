package org.mishgan.color_capture.player;

import org.mishgan.color_capture.game.BytePointSet;

public record PlayerMove(
        byte colorNumber,
        BytePointSet points
) {
}
