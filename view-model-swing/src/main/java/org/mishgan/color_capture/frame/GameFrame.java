package org.mishgan.color_capture.frame;

import org.mishgan.color_capture.controller.GameController;
import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.panel.GameFieldPanel;
import org.mishgan.color_capture.settings.ViewGameSettings;
import org.mishgan.color_capture.thread.ColorCaptureViewControllerThread;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    private static final int PANEL_PADDING = 10;

    private GameData gameData;
    private ViewGameSettings viewGameSettings;
    private final Timer repaintTimer;
    private final ColorCaptureViewControllerThread colorCaptureViewControllerThread;

    public GameFrame(GameData gameData, ViewGameSettings viewGameSettings) {
        super("Color Capture");

        this.gameData = gameData;
        this.viewGameSettings = viewGameSettings;

        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(500, 500));

        var mainPanel = new JPanel();
        this.add(mainPanel);

        mainPanel.setBorder(new CompoundBorder(
                BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING),
                BorderFactory.createEtchedBorder()
        ));
        mainPanel.setLayout(new GridLayout(0, 1, 10, 5));

        GameController gameController = new GameController(gameData);

        var gamePanel = new GameFieldPanel(gameData, viewGameSettings, gameController);
        mainPanel.add(gamePanel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                backToMainMenu();
            }
        });

        this.pack();

        // making full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // init timer
        this.repaintTimer = new Timer(1000 / 60, (e) -> {
           mainPanel.repaint();
        });
        repaintTimer.start();

        colorCaptureViewControllerThread = new ColorCaptureViewControllerThread(gameController);
        colorCaptureViewControllerThread.start();
    }

    private void backToMainMenu() {
        repaintTimer.stop();
        colorCaptureViewControllerThread.finish();
        this.dispose();
        Frames.getMainMenuFrame().setVisible(true);
    }
}
