package unidad2.BancoDelParque;

import java.util.Scanner;

public class Parque {
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Numero de plazas: ");
		int numplazas = sc.nextInt();
		System.out.println("Numero de personas: ");
		int numPersonas = sc.nextInt();
		Banco banco = new Banco(numplazas);
		Persona [] personas = new Persona[numPersonas];
		for (int i = 0; i < personas.length; i++) {
			personas[i] = new Persona(banco, " persona " + i);
		}
		for (int i = 0; i < personas.length; i++) {
			personas[i].start();;
		}
		for (int i = 0; i < personas.length; i++) {
			personas[i].join();;
		}
		System.out.println("Fin de la simulacion");
		
	}

}
