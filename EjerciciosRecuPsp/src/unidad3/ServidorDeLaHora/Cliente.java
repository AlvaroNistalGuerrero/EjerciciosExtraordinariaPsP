package unidad3.ServidorDeLaHora;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cliente {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 9999)) {
            long requestTime = System.currentTimeMillis(); // Tiempo antes de la solicitud

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverTimeStr = reader.readLine();

            long responseTime = System.currentTimeMillis(); // Tiempo después de recibir la respuesta
            long roundTripTime = responseTime - requestTime;

            LocalDateTime serverTime = LocalDateTime.parse(serverTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime correctedTime = serverTime.plusMinutes(roundTripTime / 2); // Ajustar la hora

            System.out.println("Hora del servidor: " + serverTimeStr);
            System.out.println("Hora corregida: " + correctedTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } catch (IOException e) {
            System.err.println("Error al conectarse al servidor: " + e.getMessage());
        }
    }
}