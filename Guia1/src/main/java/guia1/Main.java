package guia1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintStream ps = new PrintStream(System.out);
        PrintStream psErr = new PrintStream(System.err);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int opcion = -1;

        while (opcion != 0) {
            ps.println("Ejercicios");
            ps.println("1.a Calcular Salario Bruto (1)");
            ps.println("1.b Calcular Valor de Ángulo Restante (2)");
            ps.println("1.c Calcular Perímetro de un Cuadrado (3)");
            ps.println("1.d Convertir Fahrenheit a Celsius (4)");
            ps.println("1.e Transformar Segundos a Días, Horas, Minutos y Segundos (5)");
            ps.println("1.f Calcular Plan de Pagos (6)");
            ps.println("1.g Calcular Mes de Nacimiento según Signo (7)");
            ps.println("2.a Ordenar Apellidos Alfabéticamente (8)");
            ps.println("2.b Indicar el Número Menor (9)");
            ps.println("2.c Indicar si un Número es Par o Impar (10)");
            ps.println("2.d Calcular si un Número es Divisible por Otro (11)");
            ps.println("2.e Calcular Signo Zodiacal por Fecha de Nacimiento (12)");
            ps.println("2.f Comparar Apellidos y Ver cuál es Más Largo (13)");
            ps.println("2.g Mostrar Tabla de Multiplicar de un Número (14)");
            ps.println("2.h Indicar si un Número es Primo (15)");
            ps.println("0. Salir");
            ps.println("");
            ps.print("Ingrese una opción: ");

            try {
                opcion = Integer.parseInt(br.readLine());

                switch (opcion) {
                    case 1 -> Ejercicio1.calcularSueldo(ps);
                    case 2 -> Ejercicio1.calcularAngulo(ps);
                    case 3 -> Ejercicio1.calcularPerimetro(ps);
                    case 4 -> Ejercicio1.FahrenheitCelsius(ps);
                    case 5 -> Ejercicio1.conversionTiempo(ps);
                    case 6 -> Ejercicio1.calcularPlan(ps);
                    case 7 -> Ejercicio1.mesNacimiento(ps);
                    case 8 -> Ejercicio2.ordenarAlfabeticamente(ps, psErr, br);
                    case 9 -> Ejercicio2.ordenarMenor(ps, psErr, br);
                    case 10 -> Ejercicio2.parImpar(ps, psErr, br);
                    case 11 -> Ejercicio2.mayorMenor(ps, psErr, br);
                    case 12 -> Ejercicio2.signoZodiaco(ps, psErr, br);
                    case 13 -> Ejercicio2.medidaApellido(ps, psErr, br);
                    case 14 -> Ejercicio2.tablaMultiplicacion(ps, psErr, br);
                    case 15 -> Ejercicio2.esPrimo(ps, psErr, br);
                    case 0 -> {
                        ps.println("Saliendo del programa...");
                        break;
                    }
                    default -> ps.println("Opción inválida. Intente nuevamente.");
                }

                if (opcion != 0) {
                    ps.println("\nPresione ENTER para volver al menú...");
                    br.readLine();
                }

            } catch (IOException | NumberFormatException e) {
                psErr.println("El dato ingresado es incorrecto: " + e.getMessage());
            }
        }
    }
}