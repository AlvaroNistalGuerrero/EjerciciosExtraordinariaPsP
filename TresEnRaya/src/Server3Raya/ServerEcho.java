package Server3Raya;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Cliente3Raya.ClienteEcho;

public class ServerEcho {
	private static final int PUERTO = 5000;

	public static void main(String[] args) {
		try (ServerSocket servidor = new ServerSocket(PUERTO)) {
			ExecutorService ex = Executors.newFixedThreadPool(100);
			while (true) {
				Juego3Raya juego = new Juego3Raya();

				Socket jugador1 = servidor.accept();
				System.out.println("Jugador 1 conectado");

				Socket jugador2 = servidor.accept();
				System.out.println("Jugador 2 conectado");

				new Thread(new ClienteEcho(jugador1, jugador2, juego)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

