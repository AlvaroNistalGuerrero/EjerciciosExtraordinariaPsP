package UI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.border.EmptyBorder;


import BaberoDormilon.Barberia;
import BaberoDormilon.Barbero;
import BaberoDormilon.Cliente;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private panelBarberia panelB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 618);
		panelB = new panelBarberia();
		panelB.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelB);
		Barberia laBarberia = new Barberia(panelB.getSillas(), panelB);
		Barbero elBarbero = new Barbero(laBarberia);
		
		elBarbero.start();
		
		for(int i = 0; i < panelB.getClientes(); i++) {
			Cliente c = new Cliente(laBarberia, i+1);
			
			c.start();
		}
	}

}
