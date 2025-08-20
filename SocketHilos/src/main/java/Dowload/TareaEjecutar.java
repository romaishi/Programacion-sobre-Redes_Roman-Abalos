package Dowload;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TareaEjecutar {
	
	private static PrintStream ps = new PrintStream(System.out);

	public static void TareaEjecutar(String nombre, String tipo, String color) {
		ArrayList<String> pasos = new ArrayList<>(
				Arrays.asList("Descargando","Verificando","Descomprimiendo","Procesando","FInalizando")
				);
		
		//Tarea que vamos a realizar
		 for(String paso : pasos){
			
			 if(Thread.currentThread().isInterrupted()) {
				 ps.println(color + "[" + Thread.currentThread().getName() + "] Interrumpido! Abortando Hilo" 
						 + nombre + Utils.RESET);
				 return;
			 }
			 
			 ps.println(color + "[" + Thread.currentThread().getName() + "]" + 
				        tipo  + "[" + nombre + ": " + paso +" ... " + Utils.RESET);
			try {
				Thread.sleep((int)(200 + Math.random() * 800));				
			} catch (InterruptedException ex) {
				 ps.println(color + "[" + Thread.currentThread().getName() + "] Interrumpcion detectada en medio de " 
						 + paso + Utils.RESET);
				return; // El return permite que el hilo siga trabajando
			}
		 }
		
		
		//Tarea completa
		ps.println(color + "[" + Thread.currentThread().getName() + "]" + 
			       tipo  + "[" + nombre + " COMPLETADO " + Utils.RESET);
		
	}
	
}
