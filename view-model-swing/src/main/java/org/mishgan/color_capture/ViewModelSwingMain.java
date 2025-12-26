package org.mishgan.color_capture;

import org.mishgan.color_capture.frame.MainMenuFrame;

import javax.swing.*;

public class ViewModelSwingMain {
    public static void main(String[] args) {
        // Set the Look and Feel to the System's default (which is Windows on a Windows machine)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        var mainMenuFrame = new MainMenuFrame();

        mainMenuFrame.setVisible(true);
    }
}