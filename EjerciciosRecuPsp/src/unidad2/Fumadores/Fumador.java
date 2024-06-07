package unidad2.Fumadores;

import static unidad2.Fumadores.Main.actualizar;

public class Fumador extends Thread {
	Ingrediente ingrediente;
	Mesa mesa;

	static boolean suspendido = false;

	public Fumador(String nombre, Ingrediente ingrediente, Mesa mesa) {
		super(nombre);
		this.ingrediente = ingrediente;
		this.mesa = mesa;
	}

	public synchronized void suspender() {
		suspendido = true;
	}

	public synchronized void reanudar() {
		suspendido = false;
		notify();
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			while (suspendido) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				
				}
			}

			mesa.retirar(ingrediente);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
			actualizar(getName() + " terminó de fumar\n");
		}
		actualizar(getName() + " finaliza su tarea\n");
	}

}