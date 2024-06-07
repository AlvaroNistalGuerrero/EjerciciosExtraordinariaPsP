package UI;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import org.jvnet.substance.SubstanceLookAndFeel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Component;

public class panelBarberia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelNombre;
	private JPanel panelPrincipal;
	private JPanel panelBarberia;
	private JPanel panelClientes;
	private JPanel panelSillon;
	private JPanel panelSillas;
	private JPanel panelZonaBarbero;
	private JLabel LabelSillonBarbero;
	private JLabel LabelBarberoDurmiendo;
	private JLabel Mensaje;
	
	private int sillas =0;
	private int clientes = 0;

	/**
	 * Create the panel.
	 */
	public panelBarberia() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel_2(), BorderLayout.NORTH);
		add(getPanel_1_1(), BorderLayout.CENTER);
		String respuesta = JOptionPane.showInputDialog("Introducir numero de sillas");
		sillas = Integer.parseInt(respuesta);
		creaSillas(sillas);
		respuesta = JOptionPane.showInputDialog("Introducir numero de clientes");
		clientes = Integer.parseInt(respuesta);
		//creaSillas(sillas);
		creaClientes(clientes);
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		//JDialog.setDefaultLookAndFeelDecorated(true);
//		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
		


	}
	public int getSillas() {
		return sillas;
	}
	public int getClientes() {
		return clientes;
	}
	private JPanel getPanel_2() {
		if (panelNombre == null) {
			panelNombre = new JPanel();
			panelNombre.add(getMensaje());
		}
		return panelNombre;
	}
	private JPanel getPanel_1_1() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new GridLayout(0, 2, 0, 0));
			panelPrincipal.add(getPanel_2_1());
			panelPrincipal.add(getPanelClientes());
		}
		return panelPrincipal;
	}
	private JPanel getPanel_2_1() {
		if (panelBarberia == null) {
			panelBarberia = new JPanel();
			panelBarberia.setLayout(new GridLayout(2, 0, 0, 0));
			panelBarberia.add(getPanelSillas());
			panelBarberia.add(getPanelSillon());
		}
		return panelBarberia;
	}
	private JPanel getPanelClientes() {
		if (panelClientes == null) {
			panelClientes = new JPanel();
		}
		return panelClientes;
	}
	private JPanel getPanelSillon() {
		if (panelSillon == null) {
			panelSillon = new JPanel();
			panelSillon.setLayout(new GridLayout(1, 2, 0, 0));
			panelSillon.add(getPanelZonaBarbero());
		}
		return panelSillon;
	}
	private JPanel getPanelSillas() {
		if (panelSillas == null) {
			panelSillas = new JPanel();
			panelSillas.setLayout(new GridLayout(1, 1, 0, 0));
		}
		return panelSillas;
	}
	private void creaSillas(int sillas) {
		panelSillas.removeAll();
		for(int i = 0; i < sillas; i++) {
			JLabel silla = new JLabel();
			silla.setSize(50, 50);
			adaptarImagen(silla, "src/file/silla.png");
			panelSillas.add(silla);
		}
	}
	private void creaClientes(int clientes) {
		panelClientes.removeAll();
		for(int i = 0; i < clientes; i++) {
			JLabel cliente = new JLabel();
			cliente.setSize(50, 50);
			adaptarImagen(cliente, "src/file/Cliente.png");
			panelClientes.add(cliente);
		}
	}
	public void adaptarImagen(JLabel boton, String rutaImagen) {
		Image imgOriginal = null;
		try {
			imgOriginal = new ImageIcon(rutaImagen).getImage();
		} catch (Exception e) {
			imgOriginal = new ImageIcon(rutaImagen).getImage();
		}
		Image imgCarrito = imgOriginal.getScaledInstance((int) (boton.getWidth()), (int) (boton.getHeight()),
				Image.SCALE_FAST);

		boton.setIcon(new ImageIcon(imgCarrito));

		boton.setDisabledIcon(new ImageIcon(imgCarrito));
	}
	public JPanel getPanelZonaBarbero() {
		if (panelZonaBarbero == null) {
			panelZonaBarbero = new JPanel();
			panelZonaBarbero.setLayout(new GridLayout(0, 2, 0, 0));
			panelZonaBarbero.add(getLabelSillonBarbero());
			panelZonaBarbero.add(getLabelBarberoDurmiendo());
		}
		return panelZonaBarbero;
	}
	public JLabel getLabelSillonBarbero() {
		if (LabelSillonBarbero == null) {
			LabelSillonBarbero = new JLabel();
			LabelSillonBarbero.setSize(100, 100);
			adaptarImagen(LabelSillonBarbero, "src/file/sillaBarbero.png");
		}
		return LabelSillonBarbero;
	}
	public JLabel getLabelBarberoDurmiendo() {
		if (LabelBarberoDurmiendo == null) {
			LabelBarberoDurmiendo = new JLabel("");
			LabelBarberoDurmiendo.setSize(100, 100);
			adaptarImagen(LabelBarberoDurmiendo, "src/file/dormilon.jpg");
		}
		return LabelBarberoDurmiendo;
	}
		
	public JLabel getMensaje() {
		if (Mensaje == null) {
			Mensaje = new JLabel("New label");
		}
		return Mensaje;
	}
	public void ocuparSillasEspera(int nSillasEsperaOcupadas) {
		Component[] component = panelSillas.getComponents();
		for(int i = 0; i <component.length ; i++) {
			JLabel silla = (JLabel) component[i];
			adaptarImagen(silla, "src/file/silla.png");
		}
		for(int i = 0; i < nSillasEsperaOcupadas; i++) {
			JLabel silla = (JLabel) component[i];
			adaptarImagen(silla, "src/file/sillaEsperaOcupada.png");
		}
		
	}
	public void actualizaClientes(int afeitados) {
		Component[] component = panelSillas.getComponents();
		for(int i = 0; i <component.length ; i++) {
			JLabel c = (JLabel) component[i];
			adaptarImagen(c, "src/file/.png");	//TODO 
		}
		for(int i = 0; i < afeitados; i++) {
			JLabel c = (JLabel) component[i];
			adaptarImagen(c, "src/file/clienteafeitado.png");	//TODO Falta imagen
		}
		
	}
	
}