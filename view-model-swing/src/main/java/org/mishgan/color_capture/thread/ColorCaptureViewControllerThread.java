package org.mishgan.color_capture.thread;

import org.mishgan.color_capture.controller.GameController;

public class ColorCaptureViewControllerThread extends ColorCaptureViewThread {
    private final GameController controller;

    public ColorCaptureViewControllerThread(GameController controller) {
        this.controller = controller;
    }

    @Override
    protected void doWork() throws InterruptedException {
        controller.startGame();
    }

    @Override
    public void finish() {
        controller.stopGame();
        super.finish();
    }
}
