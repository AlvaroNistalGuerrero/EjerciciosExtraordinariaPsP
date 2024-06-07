package BaberoDormilon;

public class Cliente extends Thread{
    private int idCliente;
    private boolean afeitado = false;
    private Barberia barberia = null;

    public Cliente(Barberia barberia, int numeroCliente){
        this.idCliente = numeroCliente;
        this.barberia = barberia;
    }
    @Override
    public void run(){
        while(true){ 
            try {
                Thread.sleep(8000);
                afeitado = barberia.llegaCliente(idCliente);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        
        //Al hacer el bucle llega un cliente y  puede ser que no se afeite y se pira, asi que volveria a entrar mas tarde
        /*
        Si lo hago sin el while true, no volvera a entrar.
         */
//        try {
//            Thread.sleep(3000);
//            afeitado = barberia.llegaCliente(idCliente);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

   }
    public int getNumeroCliente() {
        return idCliente;
    }
    public void setNumeroCliente(int numeroCliente){
        this.idCliente = numeroCliente;
    }
}
