package unidad2.ProductoresConsumidores;

public class Almacen {
	private int almacenados = 0;
	private String[] productos;

	public Almacen(int capacidad) {
		productos = new String[capacidad];
	}

	public synchronized void almacenar(String producto) {
		if (almacenados == productos.length) // almacén lleno
			try {
				wait();
			} catch (InterruptedException e) {
			}
		productos[almacenados++] = producto;
		notify();
	}

	public synchronized String retirar() {
		if (almacenados == 0) // almacén vacío
			try {
				wait();
			} catch (InterruptedException e) {
			}
		String producto = productos[--almacenados];
		notify();
		return producto;
	}
}