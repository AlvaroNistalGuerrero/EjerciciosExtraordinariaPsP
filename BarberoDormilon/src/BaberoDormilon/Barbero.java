package BaberoDormilon;

public class Barbero extends Thread{
    private Barberia barberia=null;

    public Barbero(Barberia barberia){
        this.barberia = barberia;
    }

    public void run(){

        while (true){
            try {
           
            	barberia.esperarCliente();  //Recibe Cliente
                Thread.sleep(3000);
                barberia.acabarAfeitado(); //Afeita

                Thread.sleep(4000); //Duerme
            } catch (InterruptedException e) {
            }
        }
    }
}
