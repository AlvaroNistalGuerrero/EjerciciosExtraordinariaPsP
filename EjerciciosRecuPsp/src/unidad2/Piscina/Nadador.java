package unidad2.Piscina;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public 
class Nadador extends Persona {
    private AtomicInteger contadorTipo;

    public Nadador(String nombre, Semaphore semaforo, JLabel estadoLabel, JTextArea logTextArea, AtomicInteger contadorTipo) {
        super(nombre, semaforo, estadoLabel, logTextArea);
        this.contadorTipo = contadorTipo;
    }

    @Override
    protected void entrarPiscina() throws InterruptedException {
        semaforo.acquire();
        contadorTipo.incrementAndGet();
        log(nombre + " ha entrado a la piscina.");
        actualizarEstado();
    }

    @Override
    protected void salirPiscina() {
        contadorTipo.decrementAndGet();
        log(nombre + " ha salido de la piscina.");
        actualizarEstado();
        semaforo.release();
    }
}