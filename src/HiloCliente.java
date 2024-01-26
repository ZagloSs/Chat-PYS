import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread {
	private Socket socket;

	public HiloCliente(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		String mensaje;

		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			do {
				mensaje = dis.readUTF();


			} while (!mensaje.equals("fin"));

			// Cerrar conexiones y streams
			dis.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
