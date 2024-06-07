package unidad2.ProductoresConsumidores;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class RunApp extends JFrame {
	private static final long serialVersionUID = 1L;

    private JTextArea textArea;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private volatile boolean paused = false;
    private Productor productor;

    public RunApp() {
        Container contentPane = getContentPane();
        contentPane.setPreferredSize(new Dimension(900, 700));
        setTitle("Productores y Consumidores");
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
        textArea.setText(""); // Limpiar el JTextArea
        paused = false;

        // Inicializar el objeto Productor
        Almacen almacen = new Almacen(10);
        productor = new Productor(almacen, 1000); // Pasar una referencia de la ventana principal
        productor.start();

        // Puedes agregar texto inicial si lo deseas
        appendText("Inicio del programa...");
    }

    private void pausarHilos(ActionEvent e) {
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(true);
        paused = true;

        // Pausar el productor si está inicializado
        if (productor != null) {
            productor.pause();
        }

        // Puedes agregar texto indicando que los hilos están pausados
        appendText("Hilos pausados.");
    }

    private void reanudarHilos(ActionEvent e) {
        resumeButton.setEnabled(false);
        pauseButton.setEnabled(true);
        paused = false;

        // Reanudar el productor si está inicializado y no está en pausa
        if (productor != null && paused) {
            productor.resume();
        }

        // Puedes agregar texto indicando que los hilos se han reanudado
        appendText("Hilos reanudados.");
    }

    // Método para agregar texto al JTextArea
    public void appendText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RunApp().setVisible(true));
    }
}