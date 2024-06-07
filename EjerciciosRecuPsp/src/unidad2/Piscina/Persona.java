package unidad2.Piscina;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public abstract class Persona implements Runnable {
    protected String nombre;
    protected Semaphore semaforo;
    protected JLabel estadoLabel;
    protected JTextArea logTextArea;
    protected static AtomicInteger hombres = new AtomicInteger(0);
    protected static AtomicInteger mujeres = new AtomicInteger(0);
    protected static AtomicInteger niños = new AtomicInteger(0);
    protected static AtomicInteger niñas = new AtomicInteger(0);
    protected static AtomicInteger submarinistas = new AtomicInteger(0);

    public Persona(String nombre, Semaphore semaforo, JLabel estadoLabel, JTextArea logTextArea) {
        this.nombre = nombre;
        this.semaforo = semaforo;
        this.estadoLabel = estadoLabel;
        this.logTextArea = logTextArea;
    }

    protected void actualizarEstado() {
        SwingUtilities.invokeLater(() -> estadoLabel.setText(
                "Hombres: " + hombres.get() + ", Mujeres: " + mujeres.get() + ", Niños: " + niños.get() + ", Niñas: " + niñas.get() + ", Submarinistas: " + submarinistas.get()));
    }

    protected void log(String mensaje) {
        SwingUtilities.invokeLater(() -> logTextArea.append(mensaje + "\n"));
    }

    @Override
    public void run() {
        try {
            entrarPiscina();
            realizarLargo();
            salirPiscina();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract void entrarPiscina() throws InterruptedException;

    protected void realizarLargo() throws InterruptedException {
        int tiempo = new Random().nextInt(31) + 50; // 50 a 80 ms
        Thread.sleep(tiempo);
    }

    protected abstract void salirPiscina();
}





