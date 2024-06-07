package Contador;

public class UnaTarea2 implements Runnable{
	Contador contador;

	public UnaTarea2(Contador contador) {
		this.contador = contador;
	}

	@Override
	public void run() {
		for (int i = 0; i < 150; i++) {
			contador.incrementar();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Contador contador = new Contador(150);
		UnaTarea2 t = new UnaTarea2(contador);
		Thread h1 = new Thread(t);
		Thread h2 = new Thread(t);
		h1.start();
		h2.start();
		h1.join();
		h2.join();
		System.out.println(contador.getValor());
	}

}
