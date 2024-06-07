package unidad3.ServidorAritmetico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 9999);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor en " + "localhost" + ":" + "9999");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String serverResponse = in.readLine();
                System.out.println("Respuesta del servidor: " + serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Error al conectarse al servidor: " + e.getMessage());
        }
    }
}
