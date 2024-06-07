package BaberoDormilon;

import javax.swing.*;

import UI.panelBarberia;

public class Barberia {
    private int nSillasEspera = 0;
    private int nSillasEsperaOcupadas = 0;
    private boolean sillasBarberoOcupada = false;
    private boolean finAfeitado = false;
    private boolean barberoDormido = false;
	private panelBarberia panelB;
	private int afeitados = 0;

    public Barberia(int nSillasEspera,panelBarberia panelB){
        this.nSillasEspera = nSillasEspera;
        this.panelB=panelB;
        panelB.adaptarImagen(panelB.getLabelBarberoDurmiendo(), "src/file/dormilon.png");
    }
    public synchronized  boolean llegaCliente(int clienteId) throws InterruptedException {
        if (nSillasEsperaOcupadas == nSillasEspera) {
            //System.out.println("El cliente " + clienteId + " se van sin afeitarse");
            //JOptionPane.showMessageDialog(null, "El cliente " + clienteId + " se va sin afeitarse");
            panelB.getMensaje().setText("El cliente " + clienteId + " se va sin afeitarse");
            return false;
        } else {
            //Cliente espera si la silla del barbero esta ocupada
            nSillasEsperaOcupadas++;
            panelB.ocuparSillasEspera(nSillasEsperaOcupadas);
            System.out.println("El cliente " + clienteId + " se sienta en la silla de espera");
            while (sillasBarberoOcupada) {
                wait();
            }
            if (barberoDormido) {
                System.out.println("El cliente " + clienteId + " despierta a el barbero");
                panelB.adaptarImagen(panelB.getLabelBarberoDurmiendo(), null);
                notifyAll();
            }
        /*
        Proceso de afeitar al cliente
         */
            nSillasEsperaOcupadas--;
            panelB.ocuparSillasEspera(nSillasEsperaOcupadas);
            panelB.adaptarImagen(panelB.getLabelSillonBarbero(), "src/file/sillaBarberoOcupada.png ");
            sillasBarberoOcupada = true;
            finAfeitado = false;
            System.out.println("EL cliente " + clienteId + " esta en la silla del barbero");
            panelB.adaptarImagen(panelB.getLabelSillonBarbero(), "src/file/sillaBarberoOcupada.png ");
            while (!finAfeitado) { //Mientras lo afeita
                wait();
                Thread.sleep(1000);
            }
            sillasBarberoOcupada = false;
            panelB.adaptarImagen(panelB.getLabelSillonBarbero(), "src/file/sillaBarbero.png");
            notify();
            notifyAll();
            afeitados++;
			panelB.actualizaClientes(afeitados);
            System.out.println("---- El cliente " + clienteId + " se va con afeitado");
            return true;
        }
    }
    public synchronized  void esperarCliente() throws InterruptedException{ //El barbero es quien llama a este metodo
        //Barbero esperando a que llegue el cliente
        barberoDormido = true;
        while (!sillasBarberoOcupada){
            System.out.println("Barbero esperando al cliente");
            wait();
        }
        barberoDormido = false;
        System.out.println("Barbero afeitando a un cliente");
    }
    public synchronized void acabarAfeitado(){
        finAfeitado = true;
        System.out.println("Barbero termina de afeitar a un cliente");
        //Para pasar al siguiente cliente
        notifyAll();
    }



}
