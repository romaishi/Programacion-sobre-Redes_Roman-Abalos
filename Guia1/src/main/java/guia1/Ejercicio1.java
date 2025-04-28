package guia1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Ejercicio1 {
	
	
	
	public static void main(String[] args) {
		PrintStream ps = new PrintStream(System.out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream psErr = new PrintStream(System.err);
	}
	
	//Ejercicio 1.a
	public static void calcularSueldo(PrintStream ps, BufferedReader br, PrintStream psErr) throws NumberFormatException, IOException {
		ps.print("Ingrese el valor de su hora de trabajo: ");
		int valorHora = Integer.parseInt(br.readLine());
		
		ps.println("Ingrese la cantidad de horas trabajadas: ");
		int cantHora = Integer.parseInt(br.readLine());
		
		int sueldoBruto = valorHora * cantHora;
		ps.println("El valor de su sueldo bruto es de: " + sueldoBruto);
		
		
	}
	
	//Ejercicio 1.b
	public static void calcularAngulo(PrintStream ps, BufferedReader br, PrintStream psErr) throws NumberFormatException, IOException {
		ps.print("Ingrese el valor del primer ángulo: ");
		float a1 = Float.parseFloat(br.readLine());
		
		ps.println("Ingrese el valor del segundo ángulo:  ");
		float a2 = Float.parseFloat(br.readLine());
		
		float total = 180;
		float a3 = total - (a1 + a2);
		
		ps.println("El valor del ángulo restante es de:  " + a3 + "°");
		
	}
	
	//Ejercicio 1.c
		public static void calcularPerimetro(PrintStream ps, BufferedReader br, PrintStream psErr) throws NumberFormatException, IOException {
			ps.print("Ingrese el valor de la superficie del cuadrado: ");
			float superficie = Float.parseFloat(br.readLine());
			
			double perimetro = Math.sqrt(superficie) * 4);

			
			ps.println("El valor del perimetro de tu cuadrado es de: " + perimetro);
			
		}
		
	//Ejercicio 1.d
		public static void FahrenheitCelsius(PrintStream ps, BufferedReader br, PrintStream psErr) throws NumberFormatException, IOException {
			ps.print("Ingrese la temperatura en grados Fahrenheit: ");
			float fahrenheit = Float.parseFloat(br.readLine());
			
			
			float celsius = (fahrenheit - 32) * (5/9);
			
			ps.println("El valor de la temeperatura que ingresaste en Fahrenheit es de :  " + fahrenheit + " grados celsius");
			
		}
	
	//Ejercicio 1.e
		public static void CelsiusFahrenheit(PrintStream ps, BufferedReader br, PrintStream psErr) throws NumberFormatException, IOException {
			ps.print("Ingrese la temperatura en grados Celsius: ");
			float celsius = Float.parseFloat(br.readLine());
			
			
			float fahrenheit = celsius*(9/5) + 32;
			
			ps.println("El valor de la temeperatura que ingresaste en Celisus es de :  " + fahrenheit + " grados fahrenheit");
			
		}
}


