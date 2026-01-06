package org.mishgan.color_capture.thread;

public abstract class ColorCaptureViewThread extends Thread {

    private boolean isRunning;

    @Override
    public void start() {
        isRunning = true;
        super.start();
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                doWork();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void finish() {
        isRunning = false;
    }

    protected abstract void doWork() throws InterruptedException;
}
