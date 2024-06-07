package unidad2.CadenaMontaje;

import java.util.List;
import java.util.Random;


public class Colocador implements Runnable {
    private final List<Integer> cadena;
    private final int capacidad;
    private final Random random = new Random();
    private volatile boolean isPaused = false;

    public Colocador(List<Integer> cadena, int capacidad) {
        this.cadena = cadena;
        this.capacidad = capacidad;
    }

    public void pause() {
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
                synchronized (cadena) {
                    while (cadena.size() >= capacidad) {
                        cadena.wait();
                    }
                    int producto = random.nextInt(3) + 1;
                    cadena.add(producto);
                    System.out.println("Colocador ha colocado producto de tipo " + producto);
                    cadena.notifyAll();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
