package unidad2.TicTac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	private JTextArea textArea;
	private JButton startButton;
	private JButton pauseButton;
	private JButton resumeButton;
	private final int N = 2;
	private volatile boolean paused = false;
	private TicTac ticTac;

	public Main() {
		Container contentPane = getContentPane();
		contentPane.setPreferredSize(new Dimension(900, 700));
		setTitle("Programa Concurrente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		pack();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel(new FlowLayout());
		startButton = new JButton("Iniciar");
		startButton.addActionListener(this::inicio);
		buttonPanel.add(startButton);

		pauseButton = new JButton("Pausar");
		pauseButton.addActionListener(this::pausarHilos);
		pauseButton.setEnabled(false);
		buttonPanel.add(pauseButton);

		resumeButton = new JButton("Reanudar");
		resumeButton.addActionListener(this::reanudarHilos);
		resumeButton.setEnabled(false);
		buttonPanel.add(resumeButton);

		panel.add(buttonPanel, BorderLayout.NORTH);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		add(panel);
	}

	private void inicio(ActionEvent e) {
		startButton.setEnabled(false);
		pauseButton.setEnabled(true);
		textArea.setText("");
		paused = false;

		ticTac = new TicTac(this);
		Thread ticTacThread = new Thread(ticTac);
		ticTacThread.start();
	}

	private void pausarHilos(ActionEvent e) {
		pauseButton.setEnabled(false);
		resumeButton.setEnabled(true);
		paused = true;
		ticTac.pause();
	}

	private void reanudarHilos(ActionEvent e) {
		resumeButton.setEnabled(false);
		pauseButton.setEnabled(true);
		paused = false;
		ticTac.resume();
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public boolean isPaused() {
		return paused;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}