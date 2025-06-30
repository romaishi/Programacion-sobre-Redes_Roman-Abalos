package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Consola {

    private static PrintStream ps = System.out; 
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String leerLinea(String mensaje) {
        try {
            ps.println(mensaje); 
            return reader.readLine().trim();
        } catch (Exception e) {
            ps.println("Error al leer entrada.");
            return "";
        }
    }
	
	
	public static String esNumero(String texto, BufferedReader br, PrintStream ps, PrintStream psErr) {
		if (texto == null) return "No es un numero";
        try {
            Integer.parseInt(texto);
            return "Es un Entero";
        } catch (NumberFormatException e1) {
            try {
                Float.parseFloat(texto);
                return "Es un Decimal";
            } catch (NumberFormatException e2) {
                return "No es un numero";
            }
        }
    }
	
	
	public static int convertirEntero(String texto) {
		return Integer.parseInt(texto);
	}
	
	public static float convertirDecimal(String texto) {
		return Float.parseFloat(texto);
	}
	
	public static boolean esNumeroValido(String texto) {
	    try {
	        Float.parseFloat(texto);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}


}
