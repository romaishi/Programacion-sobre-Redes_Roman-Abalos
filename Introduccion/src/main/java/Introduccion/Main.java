package Introduccion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

	public static void main(String[] args) {
		/*
		System.out.print(); //manda el dato sin enter
		System.out.println();//manda un enter al final
		System.out.write(); //byte
		System.out.printf("texto", var1, var2, var3); //concatenar datos
		// "texto" + var + "otro texto" + var2 <- concatenar datos
		
		System.err;
		*/
		
		PrintStream ps = new PrintStream(System.out);
		PrintStream psErr = new PrintStream(System.err);
		
		ps.println("ebuifcuiebuifbn");
		
		
		try {
			
			int linea ;
			String palabra="";
			while( (linea = System.in.read()) != 13)
			{
				palabra = palabra + (char)linea;
			}
			/*
			* %s String
			* %n Int
			* %f Float
			* %d Double
			* %b Boolean 
			*/
			
			ps.printf("La variable palabra: %s \n", palabra);
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			int linea2 ;
			String palabra2 ="";
			while( (linea2 = System.in.read()) != 13)
			{
				palabra2 = palabra2 + (char)linea2;
			}
			
			ps.printf("Con readline:%s \n", br.readLine());//lee toda la linea
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();// Trackea el error completo y nos lo muestra en la consola de errores
		}
		psErr.println("Esto es un error");
		
	}

}