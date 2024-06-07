package unidad2.CenaFilosofosChinos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

class Filosofo implements Runnable {
    private final String nombre;
    private final Lock palilloIzquierdo;
    private final Lock palilloDerecho;
    private final Random random = new Random();
    private volatile boolean isPaused = false;
    private final JLabel estadoLabel;

    public Filosofo(String nombre, Lock palilloIzquierdo, Lock palilloDerecho, JLabel estadoLabel) {
        this.nombre = nombre;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
        this.estadoLabel = estadoLabel;
    }

    private void actualizarEstado(String estado) {
        SwingUtilities.invokeLater(() -> estadoLabel.setText(nombre + " " + estado));
    }

    private void pensar() throws InterruptedException {
        actualizarEstado("está pensando.");
        Thread.sleep(random.nextInt(1000) + 500);
    }

    private void comer() throws InterruptedException {
        actualizarEstado("está comiendo.");
        Thread.sleep(random.nextInt(1000) + 500);
    }

    public synchronized void pause() {
        isPaused = true;
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
    }

    private synchronized void checkPaused() throws InterruptedException {
        while (isPaused) {
            wait();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                checkPaused();
                pensar();
                checkPaused();

                actualizarEstado("está esperando para comer.");
                palilloIzquierdo.lock();
                try {
                    palilloDerecho.lock();
                    try {
                        comer();
                    } finally {
                        palilloDerecho.unlock();
                    }
                } finally {
                    palilloIzquierdo.unlock();
                }

                Thread.sleep(random.nextInt(100));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}