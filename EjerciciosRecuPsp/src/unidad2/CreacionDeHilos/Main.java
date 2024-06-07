package unidad2.CreacionDeHilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private final int N = 5; 
    private volatile boolean paused = false;

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
        startButton.addActionListener(this::Inicio);
        buttonPanel.add(startButton);

        pauseButton = new JButton("Pausar");
        pauseButton.addActionListener(this::pauseThreads);
        pauseButton.setEnabled(false);
        buttonPanel.add(pauseButton);

        resumeButton = new JButton("Reanudar");
        resumeButton.addActionListener(this::resumeThreads);
        resumeButton.setEnabled(false);
        buttonPanel.add(resumeButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void Inicio(ActionEvent e) {
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        textArea.setText("");
        paused = false;
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i + 1));
            thread.start();
        }
    }

    private void pauseThreads(ActionEvent e) {
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(true);
        paused = true;
    }

    private void resumeThreads(ActionEvent e) {
        resumeButton.setEnabled(false);
        pauseButton.setEnabled(true);
        paused = false;
        synchronized (this) {
            notifyAll();
        }
    }

    class Worker implements Runnable {
        private final int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (Main.this) {
                    while (paused) {
                        try {
                        	Main.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                SwingUtilities.invokeLater(() -> textArea.append("Hilo " + id + " iniciando ejecución. Tiempo de espera: " + id * 1000 + " ms.\n"));
                try {
                    Thread.sleep(id * 1000); // Dormir durante el tiempo especificado
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                SwingUtilities.invokeLater(() -> textArea.append("Hilo " + id + " finalizó su tarea.\n"));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}