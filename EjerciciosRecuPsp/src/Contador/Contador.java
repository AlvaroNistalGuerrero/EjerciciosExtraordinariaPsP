package Contador;

public class Contador {
	long valor;

	public Contador() {

	}

	public Contador(long valor) {
		this.valor = valor;
	}
//	public synchronized void incrementar() {
//		valor++;
//	}
	public void incrementar() {
		synchronized(this) {
			valor++;
		}
		
	}
	public long getValor() {
		return valor;
	}
	
}
