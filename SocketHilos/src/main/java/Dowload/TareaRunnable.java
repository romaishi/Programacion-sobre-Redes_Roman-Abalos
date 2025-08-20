package Dowload;

import java.io.PrintStream;

public class TareaRunnable implements Runnable {
	
	private String nombreArchivo = "";
	private String color = "";
	PrintStream ps = new PrintStream(System.out);
	
	
	public TareaRunnable(String nombreArchivo, String color) {
		this.nombreArchivo = nombreArchivo;
		this.color = color;
	}
	
	
	@Override
	public void run() {
		
		ps.println(color + "[" + Thread.currentThread().getName() + "] Configurando para,etros inciales para " + nombreArchivo + Utils.RESET);
		TareaEjecutar.TareaEjecutar(nombreArchivo, "Thread", color);
		ps.println(color + "[" + Thread.currentThread().getName() + "] Guardando log de "    + nombreArchivo + Utils.RESET);
		
	}
	
}

	
}
