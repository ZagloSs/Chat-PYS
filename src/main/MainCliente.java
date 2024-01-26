package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import ventana.Ventana;

public class MainCliente {

	public static void main(String[] args) {
	
		try {
			boolean continuar = true;
			Socket socket = new Socket("192.168.128.240", 6565);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner sc = new Scanner(System.in);
			String mensaje;
			
			Ventana ventana = new Ventana();
			ventana.setVisible(true);
			ventana.setDos(dos);

			while (continuar) {
				// Leemos del teclado y enviamos el mensaje al server
				ventana.enviar();
	
				// Recibimos la respuesta del server
				mensaje = dis.readUTF();
				ventana.Update(mensaje);
				

				continuar = !mensaje.equalsIgnoreCase("fin");
			}

			// Cierre de todas las conexiones o streams de datos
			sc.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
