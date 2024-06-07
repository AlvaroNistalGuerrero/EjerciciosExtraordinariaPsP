package unidad2.ProductoresConsumidores;

public class MainSinInterfaz {
	
	public static void main(String[] args) {
		Almacen almacen = new Almacen(10);
		Productor productor = new Productor(almacen, 100);
		Consumidor consumidor = new Consumidor(almacen, 1000);
		productor.start();
		consumidor.start();
	}

}
