package guia1;

import java.io.IOException;
import java.io.PrintStream;

public class Ejercicio1 {
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		PrintStream ps = new PrintStream(System.out);
		calcularSueldo(ps);

	}
	
	//Ejercicio 1.a
	public static void calcularSueldo(PrintStream ps) throws NumberFormatException, IOException  {

		ps.print("Ingrese el precio de su hora de trabajo: ");
        ps.flush();
		int precioHora = Integer.parseInt(LineReader.leerLinea());

		ps.print("Ingrese cantidad de horas de trabajo: ");
		int horasTrabajadas = Integer.parseInt(LineReader.leerLinea());
		
		ps.println(horasTrabajadas * precioHora);
	}

	//Ejercicio 1.b
	public static void calcularAngulo(PrintStream ps) throws NumberFormatException, IOException {
		ps.print("Ingrese el valor del primer ángulo: ");
		float a1 = Float.parseFloat(LineReader.leerLinea());
		
		ps.println("Ingrese el valor del segundo ángulo:  ");
		float a2 = Float.parseFloat(LineReader.leerLinea());
		
		float total = 180;
		float a3 = total - (a1 + a2);
		
		ps.println("El valor del ángulo restante es de:  " + a3 + "°");
		
	}
	
	//Ejercicio 1.c
	public static void calcularPerimetro(PrintStream ps) throws NumberFormatException, IOException {
		ps.print("Ingrese el valor de la superficie del cuadrado: ");
		float superficie = Float.parseFloat(LineReader.leerLinea());
			
		double perimetro = Math.sqrt(superficie) * 4;

			
		ps.println("El valor del perimetro de tu cuadrado es de: " + perimetro);
			
		}
		
	//Ejercicio 1.d
	public static void FahrenheitCelsius(PrintStream ps) throws NumberFormatException, IOException {
		ps.print("Ingrese la temperatura en grados Fahrenheit: ");
		float fahrenheit = Float.parseFloat(LineReader.leerLinea());
			
			
		float celsius = (fahrenheit - 32) * (5/9);
			
		ps.println("El valor de la temeperatura que ingresaste en Fahrenheit es de :  " + fahrenheit + " grados celsius");
			
	}
	
	//Ejercicio 1.e	
	public static void conversionTiempo(PrintStream ps) throws NumberFormatException, IOException {
	    ps.print("Ingrese el tiempo en segundos: ");
	    float tiempoInicial = Float.parseFloat(LineReader.leerLinea());

	        
	    float dias = tiempoInicial / 86400;
	    float horas = (tiempoInicial % 86400) / 3600;
	    float minutos = (tiempoInicial % 3600) / 60;
	    float segundos = tiempoInicial % 60;

	    ps.println("El tiempo dado en Segundos, " + dias + " días, " + horas + " horas, " + minutos + " minutos, " + segundos + " segundos.");
	    
	}

	//Ejercicio 1.f
	public static void calcularPlan(PrintStream ps) throws NumberFormatException, IOException {
		ps.print("Ingrese la temperatura en grados Celsius: ");
		float precio = Float.parseFloat(LineReader.leerLinea());
			
        float precioPlan1 = precio - (precio * 0.10f);

        
        float precioPlan2 = precio + (precio * 0.10f);
        float cuotaPlan2 = (precioPlan2 / 2);

       
        float precioPlan3 = precio + (precio * 0.15f);
        float cuotaPlan3 = (precioPlan3 * 0.75f) / 5;

        
        float precioPlan4 = precio + (precio * 0.25f);
        float cuotaPlan4PrimeraParte = (precioPlan4 * 0.60f) / 4;
        float cuotaPlan4SegundaParte = (precioPlan4 * 0.40f) / 4;

        
        ps.println("Plan 1: 100% al contado con un 10% de descuento.");
        ps.println("Precio final: $" + precioPlan1);

        ps.println("Plan 2: 50% al contado y el resto en 2 cuotas iguales con un 10% de incremento.");
        ps.println("Precio final: $" + precioPlan2);
        ps.println("Cuota 1 y 2: $" + cuotaPlan2);

        ps.println("Plan 3: 25% al contado y el resto en 5 cuotas iguales con un 15% de incremento.");
        ps.println("Precio final: $" + precioPlan3);
        ps.println("Cuotas 1 a 5: $" + cuotaPlan3);

        ps.println("Plan 4: Totalmente financiado en 8 cuotas con un 25% de incremento.");
        ps.println("Precio final: $" + precioPlan4);
        ps.println("Cuotas 1 a 4: $" + cuotaPlan4PrimeraParte);
        ps.println("Cuotas 5 a 8: $" + cuotaPlan4SegundaParte);
					
		
	}	
	
	//Ejercicio 1.g
	public static void mesNacimiento(PrintStream ps) throws IOException {
		ps.print("Ingrese su signo zodiacal: ");
		String signo = LineReader.leerLinea().toLowerCase();
		
		switch(signo) {
		case "acuario":
			ps.println("Mes de nacimiento posible: Enero - Febrero");
			break;
		case "piscis":
			ps.println("Mes de nacimiento aproximado: Febrero - Marzo");
			break;
		case "aries":
			ps.println("Mes de nacimiento aproximado: Marzo - Abril");
			break;
		 case "tauro":
             ps.println("Mes de nacimiento aproximado: Abril - Mayo");
             break;
         case "géminis":
             ps.println("Mes de nacimiento aproximado: Mayo - Junio");
             break;
         case "cáncer":
             ps.println("Mes de nacimiento aproximado: Junio - Julio");
             break;
         case "leo":
             ps.println("Mes de nacimiento aproximado: Julio - Agosto");
             break;
         case "virgo":
             ps.println("Mes de nacimiento aproximado: Agosto - Septiembre");
             break;
         case "libra":
             ps.println("Mes de nacimiento aproximado: Septiembre - Octubre");
             break;
         case "escorpio":
             ps.println("Mes de nacimiento aproximado: Octubre - Noviembre");
             break;
         case "sagitario":
             ps.println("Mes de nacimiento aproximado: Noviembre - Diciembre");
             break;
         case "capricornio":
             ps.println("Mes de nacimiento aproximado: Diciembre - Enero");
             break;
         default:
        	 ps.println("No se ha podido reconocer el sigono que ingreso");
        	 break;
		}
	}
}


