package unidad3.ServerEcoE3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("Echo Server listening on port 9999");
		while (true) {
			Socket socket = serverSocket.accept();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
					out.println(line);
					out.flush();
				}
			}
		}
	}

}