package unidad2.ProductoresConsumidores;

public class Consumidor extends Thread {
	 private long retardo;
	    private Almacen almacen;
	    private boolean pausado = false;

	    public Consumidor(Almacen almacen, long retardo) {
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
	            String producto = almacen.retirar();
	            System.out.println("producto " + producto + " retirado");
	            try {
	                Thread.sleep(retardo);
	            } catch (InterruptedException e) {
	            }
	            synchronized(this) {
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