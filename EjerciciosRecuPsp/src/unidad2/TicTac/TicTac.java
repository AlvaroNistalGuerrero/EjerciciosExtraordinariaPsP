package unidad2.TicTac;

import javax.swing.*;

public class TicTac implements Runnable {
    private final Main main;
    private final Object lock = new Object();

    public TicTac(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                while (main.isPaused()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            SwingUtilities.invokeLater(() -> main.getTextArea().append("TIC "));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (lock) {
                while (main.isPaused()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            SwingUtilities.invokeLater(() -> main.getTextArea().append("TAC "));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void pause() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void resume() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}