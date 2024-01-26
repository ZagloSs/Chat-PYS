import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ventana.Ventana;

public class HiloCliente extends Thread {
	private Socket socket;

	public HiloCliente(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		String mensaje;
		Ventana ventana = new Ventana();
		String socketName = socket.getInetAddress().getHostName();

		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = null;

			do {
				mensaje = dis.readUTF();
				// PC?????;fagsdfagda
				String[] msg = mensaje.split(";");
				for (HiloCliente h : MainServidor.hilosCliente) {
					if(h.getHostName().equals(msg[0])) {
						Socket socketMsg = h.getSocket();
						dos = new DataOutputStream(socketMsg.getOutputStream());
						dos.writeUTF(socketName + ": " + msg[1]);
					}
				}
				 

			} while (!mensaje.equals("fin"));

			// Cerrar conexiones y streams
			dis.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getHostName() {
		return socket.getInetAddress().getHostName();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
