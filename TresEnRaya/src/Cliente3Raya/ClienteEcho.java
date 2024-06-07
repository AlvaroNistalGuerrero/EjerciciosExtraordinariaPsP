package Cliente3Raya;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Server3Raya.Juego3Raya;




public class ClienteEcho implements Runnable {
	private Socket jugador1;
	private Socket jugador2;
	private Juego3Raya juego;

	public ClienteEcho(Socket jugador1, Socket jugador2, Juego3Raya juego) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.juego = juego;
	}

	@Override
	public void run() {
		try (Scanner inJugador1 = new Scanner(jugador1.getInputStream());
				PrintWriter outJugador1 = new PrintWriter(jugador1.getOutputStream(), true);
				Scanner inJugador2 = new Scanner(jugador2.getInputStream());
				PrintWriter outJugador2 = new PrintWriter(jugador2.getOutputStream(), true)) {
			// Comenzar juego
			outJugador1.println( jugador1 + "Eres las X");
			outJugador2.println("Eres los O");

			while (true) {
				// Enviar estado del tablero a ambos clientes
				char[][] tablero = juego.getTablero();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						outJugador1.print(tablero[i][j] + " ");
						outJugador2.print(tablero[i][j] + " ");
					}
					outJugador1.println();
					outJugador2.println();
				}

				// Verificar si hay un ganador o empate
				if (juego.hayGanador()) {
					outJugador1.println("¡Has ganado!");
					outJugador2.println("¡Has perdido!");
					break;
				} else if (juego.tableroLLeno()) {
					outJugador1.println("¡Empate!");
					outJugador2.println("¡Empate!");
					break;
				}

				// Esperar movimiento del cliente 1
				outJugador1.println("Es tu turno.");
				int fila1 = inJugador1.nextInt();
				int columna1 = inJugador1.nextInt();
				if (!juego.hacerMovimiento(fila1, columna1, 'X')) {
					outJugador1.println("Movimiento inválido. Intenta de nuevo.");
					continue;
				}

				// Verificar si hay un ganador después del movimiento del cliente 1
				if (juego.hayGanador()) {
					outJugador1.println("¡Has ganado!");
					outJugador2.println("¡Has perdido!");
					break;
				} else if (juego.tableroLLeno()) {
					outJugador1.println("¡Empate!");
					outJugador2.println("¡Empate!");
					break;
				}

				// Enviar estado del tablero a ambos clientes después del movimiento del cliente
				// 1
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						outJugador1.print(juego.getTablero()[i][j] + " ");
						outJugador2.print(juego.getTablero()[i][j] + " ");
					}
					outJugador1.println();
					outJugador2.println();
				}

				// Esperar movimiento del cliente 2
				outJugador2.println("Es tu turno.");
				int fila2 = inJugador2.nextInt();
				int columna2 = inJugador2.nextInt();
				if (!juego.hacerMovimiento(fila2, columna2, 'O')) {
					outJugador2.println("Movimiento inválido. Intenta de nuevo.");
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				jugador1.close();
				jugador2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}}
