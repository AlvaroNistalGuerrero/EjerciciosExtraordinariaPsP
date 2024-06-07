package unidad2.Fumadores;

import static unidad2.Fumadores.Main.actualizar;

public class Agente extends Thread {

	private Mesa mesa;

	static boolean suspendido = false;

	public Agente(Mesa mesa) {
		super("Agente");
		this.mesa = mesa;
	}

	public synchronized void pausar() {
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
					sleep(1000); // Espera activa para evitar uso excesivo de la CPU
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			Ingrediente i1 = Ingrediente.get();
			Ingrediente i2 = Ingrediente.get();
			while (i1 == i2) {
				i2 = Ingrediente.get();
				
			}
			mesa.depositar(i1, i2);

			// Aquí va el código del bucle principal

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		actualizar("El agente finaliza su tarea\n");
	}

}
