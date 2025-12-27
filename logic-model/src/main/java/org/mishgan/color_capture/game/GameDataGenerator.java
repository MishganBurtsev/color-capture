package org.mishgan.color_capture.game;

import org.mishgan.color_capture.settings.GameSettings;

public interface GameDataGenerator {
    GameData generate(GameSettings gameSettings);
}
