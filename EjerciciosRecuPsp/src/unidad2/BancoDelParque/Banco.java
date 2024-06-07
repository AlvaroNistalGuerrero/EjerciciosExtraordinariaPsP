package unidad2.BancoDelParque;

public class Banco {
	
	private int plazasLibres;

	public Banco(int plazasLibres) {
		this.plazasLibres = plazasLibres;
	}

	public synchronized void sentarse() {
		if(plazasLibres == 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " espera por una plaza en el banco");
				wait();
			}catch (InterruptedException e) {}
			plazasLibres--;
		}
	}
	
	public synchronized void levantarse() {
		plazasLibres++;
		notify();
	} 

}
