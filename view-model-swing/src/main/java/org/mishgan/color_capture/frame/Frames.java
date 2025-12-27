package org.mishgan.color_capture.frame;

/**
 * Class with all game frames
 * Singleton
 */
public class Frames {

    private Frames() {
    }

    private static MainMenuFrame mainMenuFrame = null;// lazy
    private static NewGameFrame newGameFrame = null;

    public static MainMenuFrame getMainMenuFrame() {
        if (mainMenuFrame == null) {
            mainMenuFrame = new MainMenuFrame();
        }
        return mainMenuFrame;
    }

    public static NewGameFrame getNewGameFrame() {
        if (newGameFrame == null) {
            newGameFrame = new NewGameFrame();
        }
        return newGameFrame;
    }
}
