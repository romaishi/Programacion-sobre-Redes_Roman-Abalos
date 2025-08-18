package examen_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		Files.fileToCSV();
		infinteMenu();
	}

	public static void infinteMenu() {
		PrintStream ps = new PrintStream(System.out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int input = 0;
	    
	    try {
	    	while (input != 4) {
	    		
	    		ps.print( Colors.ANSI_CYAN  + "\n        1.       Agregar datos nuevos al archivo de texto.\r\n"
	                        + "        2.       Eliminar datos del archivo de texto.\r\n"
	                        + "        3.       Mostrar los datos existentes.\r\n" + "        "
	                        +         "4.       Salir. \n" + Colors.ANSI_RESET);
	    		
	    		ps.print("Seleccione una de las opciones: ");
	    		
	    		input = Integer.parseInt(br.readLine().trim());
	    		
	    		switch (input) {
				case 1: {
					Files.addDataLinkedList();
					break;
				}
				case 2: {
					Files.showContent();
					Files.modifyFileLinkedList();
					break;
				}
				case 3: {
					Files.showContent();
					break;
				}
				case 4: {
					ps.print(Colors.ANSI_RED + "\n Saliendo del programa" + Colors.ANSI_RESET);
					break;
				}
				
				default:
					ps.print("Numero no dentro de las opciones");
				}

	    	}
	    } catch(IOException e) {
	    	 Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, e);
        }
	}
	
	
}
