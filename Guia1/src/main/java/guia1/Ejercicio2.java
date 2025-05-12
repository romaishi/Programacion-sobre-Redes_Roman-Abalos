package guia1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;


public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Ejercicio 2.a
	public static void ordenarAlfabeticamente(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException {
		ps.println("Ingrese el primer apellido: ");
		String ap1 = br.readLine();
		ps.println("Ingrese el segundo apellido: ");
		String ap2 = br.readLine();
		ps.println("Ingrese el tercer apellido: ");
		String ap3 = br.readLine();
		
		String[] apellidos = {ap1, ap2, ap3};
		
		Arrays.sort(apellidos);
		
		ps.println("Los apellidos ordenados alfabéticamente: ");
		for (String apellido : apellidos) {
			ps.println(apellido);
		}
	}
	
	//Ejercicio 2.b
	public static void ordenarMenor(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException {
		ps.println("Ingrese el primer número: ");
		double n1 = Double.parseDouble(br.readLine());
		ps.println("Ingrese el segundo número: ");
		double n2 = Double.parseDouble(br.readLine());
		ps.println("Ingrese el tercer número: ");
		double n3 = Double.parseDouble(br.readLine());
		
		double[] nums = {n1, n2, n3};
		
		Arrays.sort(nums);
		
		ps.println("El numero mas chico es: " + nums[0]);
	}
	
	//Ejercicio 2.c
	public static void parImpar(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException {
		ps.println("Ingrese un número: ");
		double num = Double.parseDouble(br.readLine());
		
		if (num % 2 == 0) {
			ps.println("El numero que ingresó es par");
		} else {
			ps.println("El numero que ingresó no es par");
		}
	}
	
	//Ejercicio 2.d
	public static void mayorMenor(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException {
		ps.println("Ingrese dos números que seand distintos ");
		ps.println("Ingrese el primer número: ");
		double n1 = Double.parseDouble(br.readLine());
		ps.println("Ingrese el segundo número: ");
		double n2 = Double.parseDouble(br.readLine());
		
		double mayor;
		double menor;
		
		
		if (n1 > n2) {
			mayor = n1;
			menor = n2;
		} else {
			mayor = n2;
			menor = n1;
			
		}
		
		if(mayor % menor == 0) {
			ps.println("El número mayor es divisble por el número menor.");
		} else {
			ps.println("El número mayor no es divisble por el número menor.");			
		}
	}
	
	//Ejercicio 2.e
	public static void signoZodiaco(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException {
		ps.println("Ingrese el dia de nacimiento (1 -31): ");
		int dia = Integer.parseInt(br.readLine());
		
		ps.println("Ingrese el mes de nacimiento (1 -12): ");
		int mes = Integer.parseInt(br.readLine());
		
		String signo = switch (mes) {
			case 1 -> (dia < 20) ? "Capricornio" : "Acuario";
			case 2 -> (dia < 19) ? "Acuario" : "Piscis";
			case 3 -> (dia < 21) ? "Piscis" : "Aries";
			case 4 -> (dia < 20) ? "Aries" : "Tauro";
			case 5 -> (dia < 21) ? "Tauro" : "Géminis";
			case 6 -> (dia < 21) ? "Géminis" : "Cáncer";
			case 7 -> (dia < 23) ? "Cáncer" : "Leo";
			case 8 -> (dia < 23) ? "Leo" : "Virgo";
			case 9 -> (dia < 23) ? "Virgo" : "Libra";
			case 10 -> (dia < 23) ? "Libra" : "Escorpio";
			case 11 -> (dia < 22) ? "Escorpio" : "Sagitario";
			case 12 -> (dia < 22) ? "Sagitario" : "Capricornio";
			default -> null;
		};

		if (signo != null) {
			ps.println("Su signo zodiacal es: " + signo);
		} else {
			ps.println("Fecha inválida.");
		}
	}
	
	
	//Ejercicio 2.f
	public static void medidaApellido(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException{
		ps.println("Ingrese el nombre y apellido de la primer persona: ");
		String nombre1 = br.readLine();
		ps.println("Ingrese el nombre y el apellido de la primer persona: ");
		String nombre2 = br.readLine();
		
		String[] lista1 = nombre1.split(" ");
		String[] lista2 = nombre2.split(" ");
		
		if (lista1.length < 2 || lista2.length < 2) {
			ps.println("Ingresa el nombre y appellido de las personas, seprado por un espacio");
		} else {
			if(lista1[1].length() > lista2[1].length()) {
				ps.println(nombre1 + " tiene el apellido más largo");
			} else if (lista1[1].length() < lista2[1].length()) {
				ps.println(nombre2 + " tiene el apellido más largo");				
			} else {
				ps.println("Ambos apellidos poseen la misma longitud de caracteres");								
			}
		}
		
	}
	
	//Ejercicio 2.g
	public static void tablaMultiplicacion(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException{
		ps.println("Ingrese un número: ");
		int n1 = Integer.parseInt(br.readLine());
			
		if (n1 <= 0) {
			ps.println("El numero no es válido");
		} else {
			ps.println("La tabla de multiplicar de " + n1 + " es: ");
			for (int i = 1; i <= 10; i++) {
				ps.println(n1 + " X " + i + " = " + (n1 * i));
			}
		}
			
	}
	
	//Ejercicio 2.g
	public static void esPrimo(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException{
		ps.println("Ingrese un número: ");
		int n1 = Integer.parseInt(br.readLine());
		
		boolean primalidad = true;
		
		if (n1 <= 1) {
			primalidad = false;
		} else {
			for (int i =  2; i <= Math.sqrt(n1); i++) {
				if (n1 % i == 0) {
					primalidad = false;
					break;
				}
			}
		}
		
		if (primalidad) {
			ps.println("El número que has ingresado es primo");
		} else {
			ps.println("El número que has ingresado no es primo");
		
	}
		
	}
}
