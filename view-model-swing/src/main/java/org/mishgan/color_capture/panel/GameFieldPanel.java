package org.mishgan.color_capture.panel;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.render.RenderCamera;
import org.mishgan.color_capture.settings.ViewGameSettings;

import javax.swing.*;
import java.awt.*;

public class GameFieldPanel extends JPanel {

    private static final int SQUARE_SIZE = 40;

    private final GameData gameData;
    private final ViewGameSettings viewGameSettings;
    private final RenderCamera renderCamera = new RenderCamera();

    public GameFieldPanel(GameData gameData, ViewGameSettings viewGameSettings) {
        this.viewGameSettings = viewGameSettings;
        this.gameData = gameData;

        // init camera position to the center of game field
        int cameraXCoord = SQUARE_SIZE * gameData.getGameField().getXSize() / 2;
        int cameraYCoord = SQUARE_SIZE * gameData.getGameField().getYSize() / 2;
        renderCamera.setPosition(-cameraXCoord, -cameraYCoord);
    }

    @Override
    public void paintComponent(Graphics g) {
        render(g);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        renderCamera.setWidthHeight(getWidth(), getHeight());
        g2d.setTransform(renderCamera.getTransform());

        Color previousColor = g2d.getColor();

        var gameField = gameData.getGameField();

        // render colors game field
        for (int i = 0; i < gameField.getXSize(); i++) {
            for (int j = 0; j < gameField.getYSize(); j++) {
                int viewX = i * SQUARE_SIZE;
                int viewY = j * SQUARE_SIZE;

                int colorNumber = gameField.getColor(i, j);
                Color color = viewGameSettings.getColor(colorNumber);

                g2d.setColor(color);
                g2d.fillRect(viewX, viewY, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        g2d.setColor(previousColor);
    }
}
