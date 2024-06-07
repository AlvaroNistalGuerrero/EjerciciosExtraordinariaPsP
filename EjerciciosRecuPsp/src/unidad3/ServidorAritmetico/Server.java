package unidad3.ServidorAritmetico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String inputLine = in.readLine();
                    if (inputLine != null) {
                        String[] parts = inputLine.split(" ");
                        if (parts.length == 3) {
                            try {
                                double num1 = Double.parseDouble(parts[0]);
                                double num2 = Double.parseDouble(parts[1]);
                                String operation = parts[2];

                                double result = performOperation(num1, num2, operation);
                                out.println(result);
                            } catch (NumberFormatException e) {
                                out.println("Error: Los operandos deben ser números.");
                            }
                        } else {
                            out.println("Error: Formato incorrecto. Debe ser: <num1> <num2> <operación>");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double performOperation(double num1, double num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN;
                }
            case "sqrt":
                if (num1 >= 0) {
                    return Math.sqrt(num1);
                } else {
                    return Double.NaN;
                }
            default:
                return Double.NaN;
        }
    }
}
