package unidad2.ContadorPalabras;

import java.io.IOException;
import java.util.Scanner;

/*
 * Crear un programa que reciba a través de sus argumentos una lista de ficheros de texto y cuente cuántas líneas, 
 * palabras y caracteres hay en cada fichero, así como en el total entre todos los ficheros. 
 * El programa recibirá un argumento adicional que indique si los ficheros se procesarán de forma secuencial o en paralelo usando hilos.
 * Se considera como palabra cualquier cadena descrita por la expresión \p{Alpha}+.
 * El programa medirá el tiempo que emplea en procesar los ficheros. 
 * Usar esa información para comparar la velocidad de proceso de la versión secuencial con la velocidad 
 * de proceso de la versión concurrente.
 */

public class Ejercicio3 {

	public static void main(String[] args) {
		
		String[] archivos = {"files/archivopruebaEjer3Psp.txt", 
				"/EjerciciosRecuPsp/src/unidad2/ContadorPalabras/files/archivopruebaEjer3Psp2.txt"};
		
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        for (String nombreArchivo : archivos) {
            try (Scanner sc = new Scanner(System.in)) {
                int lineas = 0, palabras = 0, caracteres = 0;
                String linea;
                while ((linea = sc.nextLine()) != null) {
                    lineas++;
                    caracteres += linea.length();
                    palabras += linea.split("\\s+").length;
                }
                System.out.println("Archivo: " + nombreArchivo + "\nLineas: " + lineas + "\nPalabras: " + palabras + "\nCaracteres: " + caracteres + "\n");
                totalLineas += lineas;
                totalPalabras += palabras;
                totalCaracteres += caracteres;
            } 
        }
        System.out.println("Total de todos los archivos:\nTotal de lineas: " + totalLineas + "\nTotal de palabras: " + totalPalabras + "\nTotal de caracteres: " + totalCaracteres);
    }
	}


