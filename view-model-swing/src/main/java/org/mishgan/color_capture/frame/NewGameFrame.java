package org.mishgan.color_capture.frame;

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
        return startNewGameButton;
    }

    private void backToMainMenu() {
        this.setVisible(false);
        Frames.getMainMenuFrame().setVisible(true);
    }
}
