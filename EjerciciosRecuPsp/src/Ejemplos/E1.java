package Ejemplos;

public class E1 {
	public static void main(String[] args) throws InterruptedException {
		Thread[] t = new Thread[10];
		/*
		 * Para extensiones de Thread o Threads
		 */
//		for (int i = 0; i < t.length; i++) {
//			t[i] = new UnHilo();
//			t[i].start();
//		}
		/*
		 * Para tareas Runnable
		 */
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new UnaTarea(), "Hilo " + i);	//, "Hilo " + i esto es para dar nombre al hilo
			t[i].start();
			t[i].join();
		}

	}

}

class UnHilo extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(getName() + " - Hola mundo - " + i);

		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
}

class UnaTarea implements Runnable { // representa tareas pero no hilos.Un hilo es un flujo y una tarea es lo que se
										// hace en ese hilo
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " - Hola mundo - " + i);

		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
}