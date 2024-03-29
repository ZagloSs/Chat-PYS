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
			Socket socket = new Socket("192.168.128.217", 6566);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner sc = new Scanner(System.in);
			String mensaje;
			
			Ventana ventana = new Ventana();
			ventana.setDos(dos);
			ventana.setVisible(true);

			while (continuar) {
	
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
