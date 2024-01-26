import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServidor {
	public static ArrayList<HiloCliente> hilosCliente = new ArrayList<HiloCliente>();

	public static void main(String[] args) {
		

		try (ServerSocket serverSocket = new ServerSocket(6565)) {

			while (true) {
				// accept() es bloqueante
				Socket socketCliente = serverSocket.accept();
				hilosCliente.add(new HiloCliente(socketCliente));
				hilosCliente.get(hilosCliente.size()-1).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
