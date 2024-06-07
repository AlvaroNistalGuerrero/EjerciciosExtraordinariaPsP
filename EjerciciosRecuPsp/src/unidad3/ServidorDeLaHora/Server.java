package unidad3.ServidorDeLaHora;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Servidor de hora iniciado en el puerto " + 9999);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    LocalDateTime now = LocalDateTime.now();
                    String currentTime = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    writer.println(currentTime);
                    System.out.println("Hora enviada a " + clientSocket.getRemoteSocketAddress() + ": " + currentTime);
                } catch (IOException e) {
                    System.err.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}