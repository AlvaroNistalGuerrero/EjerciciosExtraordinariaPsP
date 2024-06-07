package Ejemplos;

public class Ejercicio1 {
	
	public static class Hilo extends Thread{
		private long tiempo;
		
		public Hilo(long tiempo) {
			this.tiempo=tiempo;
		}
		@Override
		public void run() {
			System.out.println(getName() + " va a dormir " + tiempo);
			try {
				Thread.sleep(tiempo);
			} catch (InterruptedException e) { //Este catch no se va a lanzar nunca
			}
			System.out.println(getName()+ " ha finalizado");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		int n = 10;
		long tiempo;
		Thread [] threads = new Thread[n];
	
		for(int i=0; i<n; i++) {
			tiempo = (long) (Math.random()*2000+1000);
			threads[i] = new Hilo(tiempo);
			threads[i].start();
		}
		for(int j=0; j<n; j++) {
			threads[j].join();
		}
		System.out.println("Han finalizado todos los hilos");
		
		}
	}


