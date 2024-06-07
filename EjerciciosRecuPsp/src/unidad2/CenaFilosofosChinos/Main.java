package unidad2.CenaFilosofosChinos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
    private final Lock[] palillos = new ReentrantLock[5];
    private final Thread[] filosofos = new Thread[5];
    private final Filosofo[] filosofoObjs = new Filosofo[5];
    private final String[] nombres = {"孔夫子", "楊朱", "荀子", "商鞅", "莊子"};
    private final JLabel[] estadoLabels = new JLabel[5];

    public Main() {
        setTitle("Filósofos Comensales");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnIniciar = new JButton("Iniciar");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarFilosofos();
            }
        });

        btnPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarFilosofos();
            }
        });

        btnReanudar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reanudarFilosofos();
            }
        });

        panelBotones.add(btnIniciar);
        panelBotones.add(btnPausar);
        panelBotones.add(btnReanudar);

        JPanel panelEstados = new JPanel(new GridLayout(5, 1));
        for (int i = 0; i < estadoLabels.length; i++) {
            estadoLabels[i] = new JLabel(nombres[i] + " está pensando.");
            panelEstados.add(estadoLabels[i]);
        }

        add(panelBotones, BorderLayout.NORTH);
        add(panelEstados, BorderLayout.CENTER);
    }

    private void iniciarFilosofos() {
        for (int i = 0; i < palillos.length; i++) {
            palillos[i] = new ReentrantLock();
        }

        for (int i = 0; i < filosofos.length; i++) {
            Lock palilloIzquierdo = palillos[i];
            Lock palilloDerecho = palillos[(i + 1) % palillos.length];
            filosofoObjs[i] = new Filosofo(nombres[i], palilloIzquierdo, palilloDerecho, estadoLabels[i]);
            filosofos[i] = new Thread(filosofoObjs[i]);
            filosofos[i].start();
        }
    }

    private void pausarFilosofos() {
        for (Filosofo filosofo : filosofoObjs) {
            if (filosofo != null) {
                filosofo.pause();
            }
        }
    }

    private void reanudarFilosofos() {
        for (Filosofo filosofo : filosofoObjs) {
            if (filosofo != null) {
                filosofo.resume();
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