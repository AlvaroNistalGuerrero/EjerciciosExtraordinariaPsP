package unidad2.BancoDelParque;

public class Persona extends Thread{
	private Banco banco ;
	private long tiempoPaseo;
	private long tiempoSentado;
	
	public Persona(Banco banco, String nombre) {
		super(nombre);
		this.banco = banco;
		tiempoPaseo = (long) ((Math.random()*1000000) % 2000 + 1000);
		tiempoSentado = (long) ((Math.random()*1000000) % 901 + 100);
		
	}
	public void run() {
		try {
			Thread.sleep(tiempoPaseo);
			System.out.println(getName() + " llega al banco");
			banco.sentarse();
			System.out.println(getName() + " se ha sentado");
			Thread.sleep(tiempoSentado);
			banco.levantarse();
			System.out.println(getName() + " se ha levantado");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
