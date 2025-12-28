package org.mishgan.color_capture.panel;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.settings.ViewGameSettings;

import javax.swing.*;
import java.awt.*;

public class GameFieldPanel extends JPanel {

    private static final int SQUARE_SIZE = 50;

    private final GameData gameData;
    private final ViewGameSettings viewGameSettings;

    public GameFieldPanel(GameData gameData, ViewGameSettings viewGameSettings) {
        this.viewGameSettings = viewGameSettings;
        this.gameData = gameData;
    }

    @Override
    public void paintComponent(Graphics g) {
        render(g);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Color previousColor = g2d.getColor();

        var gameField = gameData.getGameField();

        for (int i = 0; i < gameField.getXSize(); i++) {
            for (int j = 0; j < gameField.getYSize(); j++) {
                int viewX = i * SQUARE_SIZE;
                int viewY = j * SQUARE_SIZE;

                int colorNumber = gameField.getColor(i, j);
                Color color = viewGameSettings.getColor(colorNumber);

                g2d.setColor(color);
                g2d.fillRect(viewX, viewY, viewX + SQUARE_SIZE, viewY + SQUARE_SIZE);
            }
        }

        g2d.setColor(previousColor);
    }
}
