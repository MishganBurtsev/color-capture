package org.mishgan.color_capture.frame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainMenuFrame extends JFrame {

    private static final int PANEL_PADDING = 10;
    private static final int FRAME_MINIMAL_WIDTH = 300;
    private static final int FRAME_MINIMAL_HEIGHT = 100;

    public MainMenuFrame() {
        super("Color Capture");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        var mainPanel = new JPanel();
        this.add(mainPanel);

        mainPanel.setBorder(new CompoundBorder(
                BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING),
                BorderFactory.createEtchedBorder()
        ));
        mainPanel.setLayout(new GridLayout(0, 1, 10, 5));

        // buttons
        mainPanel.add(createNewGameButton());
        mainPanel.add(createLoadGameButton());
        mainPanel.add(createExitButton());

        // Use pack() to size the frame based on the preferred sizes of its components
        // (including the frame's own setPreferredSize, which is a common way for JFrames)
        this.pack();

        this.setMinimumSize(new Dimension(FRAME_MINIMAL_WIDTH, FRAME_MINIMAL_HEIGHT));

        this.getRootPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // This code block runs every time the frame is resized (as the user drags the mouse)
                int newWidth = e.getComponent().getWidth();
                int newHeight = e.getComponent().getHeight();
                System.out.println("Frame resized to: " + newWidth + "x" + newHeight);

                // You can add your specific logic here, e.g., repositioning components
                // or calling a method to update your application's layout.
            }
        });
    }

    private JButton createNewGameButton() {
        var newGameButton = new JButton("New game");
        // TODO implement
        return newGameButton;
    }

    private JButton createLoadGameButton() {
        var loadGameButton = new JButton("Load game");
        // TODO implement
        return loadGameButton;
    }

    private JButton createExitButton() {
        var exitButton = new JButton("Exit game");
        exitButton.addActionListener((e) -> {
            // exit from main menu
            this.dispose();
        });
        return exitButton;
    }
}
