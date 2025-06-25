package CadenaCaracteres;

import java.io.PrintStream;
import java.util.StringTokenizer;

public class Main {
	public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_MAGENTA = "\u0033[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String palabra = "LinKevin";
		
		palabra.charAt(5); //Agarra un caracter X
		palabra.compareTo("Kevin"); // False compara un texto completo
		palabra.compareToIgnoreCase("linkevin"); //True
		palabra.concat("/profile"); //Agrega texto al final
		palabra.contains("Ke"); //Devuelve si existe el texto
		palabra.indexOf('\n'); //Lo mmismo que el charAt pero busca un caracater
		palabra.indexOf("Ke"); //Ubicacion de esa palabra devuelve 3
		palabra.length(); //largo total de la cadena
		palabra.lastIndexOf('i'); //Ultima paaricion de un texto o caracter
		palabra.replace("Kevin", "Roman");
		palabra.replace("i", "X"); // LXKevin
		palabra.replaceAll("i", "x.X"); //Lx.XnKex.Xn
		palabra.toString();
		palabra.valueOf(5); //Transforma a texto
		palabra.trim(); //Quita espacions en blNCO delante y al final
		palabra.toLowerCase();
		palabra.toUpperCase();
		
		palabra.toCharArray(); //Convierte un string en un vector
		// ['L', 'i', 'n', 'K', 'e', 'v', 'i', 'n']
		palabra.split("K");// vec[] => {"Lin", "evin"} Lin evin
		palabra.substring(2,4); // LinKevin -> nKev
		
		
		//Tokenizer
		// String = 75+9+63=10
		StringTokenizer st = new StringTokenizer("75+9+63=10", "\\+"); //Primer / indica que lo sigue va a ser un caracter de escape, y dps viene el caracter de escape, en este caso seria el '\+'
		PrintStream ps = new PrintStream(System.out);
		
		ps.println(ANSI_GREEN.concat(String.valueOf(st.countTokens()).concat(ANSI_RESET)));// Cant. de tokens
		
		while(st.hasMoreTokens())
		{
			ps.println(st.nextToken());  // El texto en si como String			
		}
		
		/*
		ps.println(st.nextToken());  // El texto en si como String
		ps.println(st.nextToken());  // El texto en si como String
		ps.println(st.nextToken());  // El texto en si como String
		ps.println(st.nextElement());// Objeto representado con String
		
		
		ps.println(st.nextElement());
		*/
		int num = Integer.parseInt("9");
		
		
	}



}