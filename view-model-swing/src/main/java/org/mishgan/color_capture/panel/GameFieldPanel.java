package org.mishgan.color_capture.panel;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.render.RenderCamera;
import org.mishgan.color_capture.settings.ViewGameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;

public class GameFieldPanel extends JPanel {

    private static final int SQUARE_SIZE = 40;

    private final GameData gameData;
    private final ViewGameSettings viewGameSettings;
    private final RenderCamera renderCamera = new RenderCamera();

    private Point mouseClickedPoint;
    private Point startDragCameraPoint;

    public GameFieldPanel(GameData gameData, ViewGameSettings viewGameSettings) {
        this.viewGameSettings = viewGameSettings;
        this.gameData = gameData;

        // init camera position to the center of game field
        int cameraXCoord = SQUARE_SIZE * gameData.getGameField().getXSize() / 2;
        int cameraYCoord = SQUARE_SIZE * gameData.getGameField().getYSize() / 2;
        renderCamera.setPosition(-cameraXCoord, -cameraYCoord);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseClickedPoint != null && !e.getPoint().equals(mouseClickedPoint)) {
                    renderCamera.setPosition(
                            startDragCameraPoint.getX() + e.getX() - mouseClickedPoint.getX(),
                            startDragCameraPoint.getY() + e.getY() - mouseClickedPoint.getY()
                    );
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Point myPoint = new Point();
                    AffineTransform t = renderCamera.getTransform();
                    t.inverseTransform(e.getPoint(), myPoint);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        // TODO logic to left mouse button
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        // TODO logic for right mouse button
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickedPoint = e.getPoint();
                startDragCameraPoint = renderCamera.getPosition();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseClickedPoint = null;
                startDragCameraPoint = null;
            }
        });

        addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                renderCamera.increaseScale();
            } else if (e.getWheelRotation() > 0) {
                renderCamera.decreaseScale();
            }
        });
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

    private Point getLogicCoords(Point viewCoords) {
        return new Point(viewCoords.x / SQUARE_SIZE, viewCoords.y / SQUARE_SIZE);
    }
}
