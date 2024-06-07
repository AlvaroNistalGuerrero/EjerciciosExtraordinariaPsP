package unidad2.CadenaMontaje;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
    private static final int CAPACIDAD_CADENA = 10;
    private final List<Integer> cadena = new ArrayList<>();
    private final AtomicInteger totalEmpaquetados = new AtomicInteger(0);
    private Thread colocadorThread;
    private Colocador colocador;
    private final Thread[] empaquetadoresThreads = new Thread[3];
    private final Empaquetador[] empaquetadores = new Empaquetador[3];
    private final JLabel totalLabel = new JLabel("Total empaquetados: 0");

    public Main() {
        setTitle("Cadena de Montaje");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnIniciar = new JButton("Iniciar");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarRobots();
            }
        });

        btnPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarRobots();
            }
        });

        btnReanudar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reanudarRobots();
            }
        });

        panelBotones.add(btnIniciar);
        panelBotones.add(btnPausar);
        panelBotones.add(btnReanudar);

        add(panelBotones, BorderLayout.NORTH);
        add(totalLabel, BorderLayout.CENTER);
    }

    private void iniciarRobots() {
        colocador = new Colocador(cadena, CAPACIDAD_CADENA);
        colocadorThread = new Thread(colocador);
        colocadorThread.start();

        for (int i = 0; i < 3; i++) {
            empaquetadores[i] = new Empaquetador(i + 1, cadena, totalEmpaquetados, totalLabel);
            empaquetadoresThreads[i] = new Thread(empaquetadores[i]);
            empaquetadoresThreads[i].start();
        }
    }

    private void pausarRobots() {
        if (colocador != null) {
            colocador.pause();
        }
        for (Empaquetador empaquetador : empaquetadores) {
            if (empaquetador != null) {
                empaquetador.pause();
            }
        }
    }

    private void reanudarRobots() {
        if (colocador != null) {
            colocador.resume();
        }
        for (Empaquetador empaquetador : empaquetadores) {
            if (empaquetador != null) {
                empaquetador.resume();
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
    
