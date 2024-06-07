package unidad2.Piscina;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
    private static final int NUM_CALLES = 8;
    private final Semaphore semaforo = new Semaphore(NUM_CALLES);
    private final JLabel estadoLabel = new JLabel("Hombres: 0, Mujeres: 0, Niños: 0, Niñas: 0, Submarinistas: 0");
    private final JTextArea logTextArea = new JTextArea(10, 30);

    public Main() {
        setTitle("Piscina Olímpica");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnIniciar = new JButton("Iniciar");

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });

        panelBotones.add(btnIniciar);
        add(panelBotones, BorderLayout.NORTH);
        add(estadoLabel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void iniciarSimulacion() {
        String[] tiposNadadores = {"Hombre", "Mujer", "Niño", "Niña"};
        AtomicInteger[] contadores = {Persona.hombres, Persona.mujeres, Persona.niños, Persona.niñas};
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            if (random.nextBoolean()) {
                // Crear y empezar un hilo de Nadador
                int tipoIndex = random.nextInt(4);
                Nadador nadador = new Nadador(tiposNadadores[tipoIndex], semaforo, estadoLabel, logTextArea, contadores[tipoIndex]);
                new Thread(nadador).start();
            } else {
                // Crear y empezar un hilo de Submarinista
                Submarinista submarinista = new Submarinista("Submarinista", semaforo, estadoLabel, logTextArea);
                new Thread(submarinista).start();
            }

            try {
                Thread.sleep(200); // Tiempo entre entradas de usuarios
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}