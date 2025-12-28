package org.mishgan.color_capture.frame;

import org.mishgan.color_capture.game.GameData;
import org.mishgan.color_capture.panel.GameFieldPanel;
import org.mishgan.color_capture.settings.ViewGameSettings;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    private static final int PANEL_PADDING = 10;

    private GameData gameData;
    private ViewGameSettings viewGameSettings;

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

        var gamePanel = new GameFieldPanel(gameData, viewGameSettings);
        mainPanel.add(gamePanel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                backToMainMenu();
            }
        });

        this.pack();
    }

    private void backToMainMenu() {
        this.dispose();
        Frames.getMainMenuFrame().setVisible(true);
    }
}
