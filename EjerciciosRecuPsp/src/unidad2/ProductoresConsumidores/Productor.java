package unidad2.ProductoresConsumidores;

public class Productor extends Thread {
	private long retardo, contador = 0;
	private Almacen almacen;
	private boolean pausado = false;

	public Productor(Almacen almacen, long retardo) {

		super();
		this.retardo = retardo;
		this.almacen = almacen;
	}
	 public void pause() {
	        pausado = true;
	    }

	    public void resumeThread() {
	        pausado = false;
	        synchronized(this) {
	            notify();
	        }
	    }

	public void run() {
		while (true) {
			String producto = String.format("%d", ++contador);
			almacen.almacenar(producto);
			System.out.println("producto " + producto + " almacenado");
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e) {
			}synchronized(this) {
                while (pausado) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
		}
	}
	

	    
}