package ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;

	// Área de mensajes
	private final JTextArea mensajes = new JTextArea();
	// Prompt de envío                                                                                 
	private final JTextField prompt = new JTextField();
	// Botón de envío
	private final JButton boton = new JButton();
	private DataOutputStream dos;

	public Ventana() {
		// Distribución de los componentes por zonas de la ventana
		setLayout(new BorderLayout());

		mensajes.setPreferredSize(new Dimension(400, 200));
		prompt.setPreferredSize(new Dimension(200, 30));
		boton.setPreferredSize(new Dimension(20, 30));
		prompt.setToolTipText("Escribe aquí tu mensaje...");
		boton.setText("Enviar");
		boton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// Método que se ejecuta cuando hagamos click
				enviar();
			}

		});

		add(new JScrollPane(mensajes), BorderLayout.NORTH);
		add(prompt, BorderLayout.CENTER);
		add(boton, BorderLayout.SOUTH);

		pack();

		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void enviar() {
		String mensaje = prompt.getText();

		// Mostrar mensaje en el área de texto
		
		try {
			mensajes.append("Yo: " + mensaje.split(";")[1] + "\n");
			dos.writeUTF(mensaje.split(";")[0] + ": " + mensaje.split(";")[1]);
			prompt.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Borrar el mensaje enviado del prompt
		
		// TODO CÓDIGO DE ENVÍO DE MENSAJE
	}
	
	public void Update(String msg) {
		mensajes.append(msg);
	}
	
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

}
