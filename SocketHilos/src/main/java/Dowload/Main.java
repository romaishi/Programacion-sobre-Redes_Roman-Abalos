package Dowload;

import java.io.PrintStream;
import java.util.ArrayList;

public class Main {
	
	private static ThreadGroup grupo = new ThreadGroup("GrupoDescargas");
	private static ArrayList<Thread> listaHilos = new ArrayList<>();
	private static PrintStream ps = new PrintStream(System.out);
	

	public static void main(String[] args) {
		
		//1) Instancia directa de Objeto Thread
		Thread hilo1 = new Thread(new TareaRunnable("consorti.java" , Utils.COLORES[0]) , "Hilo-Java" );
		hilo1.setPriority(10);
		listaHilos.add(hilo1);
		
		//hilo1.start();
		
		
		//2) Instancia de una Class que herede de Thread
		Thread hilo2 = new TareaThread(grupo, "consorti.mp3", "Hilo-musica", 10,Utils.COLORES[1]);
		
		//hilo2.start();
		listaHilos.add(hilo2);
		
		
		//3) Instancia de una class que sea una Tarea, o sea "Runnable" 
		Thread hilo3 = Utils.crearHilo(grupo, new TareaRunnable("consorti.png", Utils.COLORES[2]), 
							"Hilo-imagen", 10, Utils.COLORES[2]);
		
		//hilo3.start();
		listaHilos.add(hilo3);
		
		
		//4) Con clase anonima
		Thread hilo4 = Utils.crearHilo(grupo, 
				new Runnable() {
					@Override
					public void run() {
						ps.println(Utils.COLORES[3] + "[" + Thread.currentThread().getName()+ "] Preparando archivo... "
																						+ Utils.RESET);
						TareaEjecutar.TareaEjecutar("consorti.rar", "Clase Anonima", Utils.COLORES[3]);
						
					}
				}, 
				"Hilo-RAR", 10, Utils.COLORES[3]);
		
		//hilo4.start();
		listaHilos.add(hilo4);
		
		
		//5) Lambda
		Thread hilo5 = Utils.crearHilo(grupo, 
				() -> {
						ps.println(Utils.COLORES[4] + "[" + Thread.currentThread().getName()+ "] Preparando archivos... "
																						+ Utils.RESET);
						TareaEjecutar.TareaEjecutar("consorti.pdf", "Clase Lambda", Utils.COLORES[4]);
				}, 
				"Hilo-PDF", 10, Utils.COLORES[4]);
		
		//hilo5.start();
		listaHilos.add(hilo5);
		
		
		
		for(Thread t : listaHilos)
		{
			ps.println("[MAIN] Iniciando " + t.getName() + " con prioridad " + t.getPriority());
			t.start();
		}
		
		//Otras tareas que puede ejecutar el MAIN
		
		
		//Esperar que todas las tareas finalizcen para concluir el programa MAIN
		for(Thread hilo : listaHilos) 
		{
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Aca cerramos el programa MAIN
		ps.println("[" + Thread.currentThread().getName() + "] FIN MAIN. ");
	}

}
