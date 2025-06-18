package tp_files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicio_1 {
	PrintStream ps = new PrintStream(System.out);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

    public static void main(String[] args) { 
    }

    public void IngresarDatosVector() {
        int[] origen1 = new int[5];
        boolean valido = false;
        
        while (!valido) {
            System.out.println("Ingrese 5 números para el vector (deben haber al menos 2 ceros): ");
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.printf("%d - Ingrese un número: ", i + 1);
                    origen1[i] = Integer.parseInt(br.readLine().trim());
                }

                if (contadorCeros(origen1)) {
                    valido = true;
                    System.out.println("Vector aceptado.");
                } else {
                    System.out.println("Error: debe ingresar al menos 2 ceros. Intente nuevamente.");
                }

            } catch (IOException | NumberFormatException e) {
                Logger.getLogger(Ejercicio_1.class.getName()).log(Level.WARNING, null, e);
            }
        }
    }

    
    
    public boolean contadorCeros(int[] vector) {
    	int contador = 0;
    	for (int num : vector) {
    		if (num == 0) {
    			contador++;
    		}
    	}
    	return contador >= 2;
    }
    
    
    
    
}
