package unidad2.Piscina;

import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Submarinista extends Persona {
    public Submarinista(String nombre, Semaphore semaforo, JLabel estadoLabel, JTextArea logTextArea) {
        super(nombre, semaforo, estadoLabel, logTextArea);
    }

    @Override
    protected void entrarPiscina() throws InterruptedException {
        semaforo.acquire(2);
        submarinistas.incrementAndGet();
        log(nombre + " ha entrado a la piscina.");
        actualizarEstado();
    }

    @Override
    protected void salirPiscina() {
        submarinistas.decrementAndGet();
        log(nombre + " ha salido de la piscina.");
        actualizarEstado();
        semaforo.release(2);
    }
}

