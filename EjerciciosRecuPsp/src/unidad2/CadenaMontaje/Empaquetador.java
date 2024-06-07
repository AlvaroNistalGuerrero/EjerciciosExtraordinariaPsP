package unidad2.CadenaMontaje;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Empaquetador implements Runnable {
    private final int tipo;
    private final List<Integer> cadena;
    private final AtomicInteger totalEmpaquetados;
    private final JLabel totalLabel;
    private volatile boolean isPaused = false;

    public Empaquetador(int tipo, List<Integer> cadena, AtomicInteger totalEmpaquetados, JLabel totalLabel) {
        this.tipo = tipo;
        this.cadena = cadena;
        this.totalEmpaquetados = totalEmpaquetados;
        this.totalLabel = totalLabel;
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

    private void actualizarTotal() {
        SwingUtilities.invokeLater(() -> totalLabel.setText("Total empaquetados: " + totalEmpaquetados.get()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                checkPaused();
                synchronized (cadena) {
                    while (cadena.isEmpty() || !cadena.contains(tipo)) {
                        cadena.wait();
                    }
                    if (cadena.remove((Integer) tipo)) {
                        totalEmpaquetados.incrementAndGet();
                        System.out.println("Empaquetador de tipo " + tipo + " ha empaquetado un producto.");
                        actualizarTotal();
                    }
                    cadena.notifyAll();
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}