package Server3Raya;

public class Juego3Raya {

	private char[][] tablero;
    private boolean turnoX;

    public Juego3Raya() {
        tablero = new char[3][3];
        turnoX = true;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    public synchronized boolean hacerMovimiento(int fila, int columna, char jugador) {
        if (fila < 0 || fila >= 3 || columna < 0 || columna >= 3 || tablero[fila][columna] != '-') {
            return false; 
        }

        tablero[fila][columna] = jugador;
        return true;
    }

    public synchronized boolean hayGanador() {
        // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != '-' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }

        // Comprobar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != '-' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return true;
            }
        }

        // Comprobar diagonales
        if (tablero[0][0] != '-' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != '-' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }

        return false;
    }

    public synchronized boolean tableroLLeno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    return false; // Hay al menos una casilla vacía
                }
            }
        }
        return true; // El tablero está lleno
    }

    public synchronized char[][] getTablero() {
        return tablero;
    }

    public synchronized boolean isTurnoX() {
        return turnoX;
    }

    public synchronized void cambiarTurno() {
        turnoX = !turnoX;
    }
}



