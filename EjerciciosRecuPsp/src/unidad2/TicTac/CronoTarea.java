package unidad2.TicTac;

public class CronoTarea {

	private String sonido;
	private static Object lock = new Object();
	private static boolean ticTurn = true;

	public CronoTarea(String sonido) {
		this.sonido = sonido;
	}

	public void tarea() {
		while (true) {
			synchronized (lock) {
				try {
					
					while ((sonido.equals("TIC") && !ticTurn) || (sonido.equals("TAC") && ticTurn)) {
						lock.wait();
					}
					System.out.println(sonido + " ");
					Thread.sleep(1000);
					ticTurn = !ticTurn;
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				

			}
			 try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
		}

	}

	public static void main(String[] args) throws InterruptedException {
		CronoTarea tic = new CronoTarea("TIC");

		CronoTarea tac = new CronoTarea("TAC");
		new Thread(tic::tarea).start();
		new Thread(tac::tarea).start();
	}

}