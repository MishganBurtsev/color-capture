package org.mishgan.color_capture.frame;

import org.mishgan.color_capture.Point;
import org.mishgan.color_capture.game.GameDataGenerator;
import org.mishgan.color_capture.game.RandomGameDataGenerator;
import org.mishgan.color_capture.player.PlayerStartConfiguration;
import org.mishgan.color_capture.settings.GameSettings;
import org.mishgan.color_capture.settings.GameSettingsBuilder;
import org.mishgan.color_capture.settings.ViewGameSettings;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Frame with settings of new game
 */
public class NewGameFrame extends JFrame {

    private static final int PANEL_PADDING = 10;
    private static final int FRAME_MINIMAL_WIDTH = 300;
    private static final int FRAME_MINIMAL_HEIGHT = 100;

    public NewGameFrame() {
        super("Color Capture -> New Game");

        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        var mainPanel = new JPanel();
        this.add(mainPanel);

        mainPanel.setBorder(new CompoundBorder(
                BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING),
                BorderFactory.createEtchedBorder()
        ));
//        mainPanel.setLayout(new GridLayout(0, 1, 10, 5));

        // buttons
        mainPanel.add(createBackButton());
        mainPanel.add(createStartGameButton());

        // Use pack() to size the frame based on the preferred sizes of its components
        // (including the frame's own setPreferredSize, which is a common way for JFrames)
        this.pack();

        // must be done after pack
        this.setMinimumSize(new Dimension(FRAME_MINIMAL_WIDTH, FRAME_MINIMAL_HEIGHT));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                backToMainMenu();
            }
        });
    }

    private JButton createBackButton() {
        var backButton = new JButton("<- Back to main menu");
        backButton.addActionListener((e) -> {
            backToMainMenu();
        });
        return backButton;
    }

    private JButton createStartGameButton() {
        var startNewGameButton = new JButton(" Start New Game -> ");
        startNewGameButton.addActionListener((e) -> {
            // TODO add form to manipulate game settings
            // temp hardcode
            var gameSettings = createGameSettings();
            var viewGameSettings = createViewGameSettings(gameSettings);

            GameDataGenerator gameDataGenerator = new RandomGameDataGenerator();
            var gameData = gameDataGenerator.generate(gameSettings);

            GameFrame gameFrame = new GameFrame(gameData, viewGameSettings);

            NewGameFrame.this.setVisible(false);
            gameFrame.setVisible(true);
        });
        return startNewGameButton;
    }

    private void backToMainMenu() {
        this.setVisible(false);
        Frames.getMainMenuFrame().setVisible(true);
    }

    private GameSettings createGameSettings() {
        var mapSize = Point.create(20, 20);

        GameSettingsBuilder gameSettingsBuilder = new GameSettingsBuilder()
                .mapSize(mapSize)
                .addPlayer(new PlayerStartConfiguration()
                        .colorNumber(0)
                        .startPosition(Point.create(0, 0)))
                .addPlayer(new PlayerStartConfiguration()
                        .colorNumber(1)
                        .startPosition(Point.create(mapSize.x() - 1, mapSize.y() - 1)))
                .neutralColorsCount(5);

        GameSettings gameSettings = gameSettingsBuilder.build();
        return gameSettings;
    }

    private ViewGameSettings createViewGameSettings(GameSettings gameSettings) {
        ViewGameSettings viewGameSettings = new ViewGameSettings(
                gameSettings.getPlayerCount(),
                gameSettings.getNeutralColorsCount());
        viewGameSettings.setColor(0, Color.RED);
        viewGameSettings.setColor(1, Color.BLUE);
        viewGameSettings.generateRandomColors();
        return viewGameSettings;
    }
}
