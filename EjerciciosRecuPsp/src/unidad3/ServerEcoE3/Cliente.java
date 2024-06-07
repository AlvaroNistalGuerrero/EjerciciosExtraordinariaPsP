package unidad3.ServerEcoE3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws IOException {
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		System.out.print("> ");
		while ((line = keyboardIn.readLine()) != null) {
			Socket socket = new Socket("localhost", 9999);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
				out.println(line);
				out.flush();
				System.out.println(in.readLine());
				System.out.print("> ");
			}
		}
	}

}