package guiaDeEjerciciosFiles;

import java.io.*;
import java.util.*;

public class main {
    
    public static String obtenerEntrada() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    
    public static void metodo1() {
        try {
            System.out.println("=== Ejercicio 1: Último dato ingresado ===");
            String valor = "";
            String entrada;
            
            System.out.println("Ingrese datos (escriba 'fin' para terminar):");
            while (true) {
                entrada = obtenerEntrada();
                if (entrada.equals("fin")) break;
                valor = entrada;
            }
            
            PrintWriter pw = new PrintWriter(new FileWriter("ultimo_dato.txt"));
            pw.println(valor);
            pw.close();
            
            System.out.println("Último dato guardado en 'ultimo_dato.txt': " + valor);
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 1: " + ex.getMessage());
        }
    }
    
    public static void metodo2() {
        try {
            System.out.println("\n=== Ejercicio 2: Valores numéricos ===");
            PrintWriter pw = new PrintWriter(new FileWriter("numeros_usuario.txt"));
            String entrada;
            
            System.out.println("Ingrese datos (escriba 'fin' para terminar):");
            while (true) {
                entrada = obtenerEntrada();
                if (entrada.equals("fin")) break;
                
                try {
                    Double.parseDouble(entrada);
                    pw.println(entrada);
                } catch (NumberFormatException ex) {
                    System.out.println("'" + entrada + "' no es un número, se omite.");
                }
            }
            
            pw.close();
            System.out.println("Números guardados en 'numeros_usuario.txt'");
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 2: " + ex.getMessage());
        }
    }
    
    public static void metodo3() {
        try {
            System.out.println("\n=== Ejercicio 3: Números pares 0-1000 ===");
            File archivo = new File("../numeros.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(archivo));
            
            for (int contador = 0; contador <= 1000; contador += 2) {
                pw.println(contador);
            }
            
            pw.close();
            System.out.println("Archivo 'numeros.txt' creado con números pares del 0 al 1000");
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 3: " + ex.getMessage());
        }
    }
    
    public static void metodo4() {
        try {
            System.out.println("\n=== Ejercicio 4: Leer numeros.txt ===");
            File archivo = new File("../numeros.txt");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            
            String linea;
            System.out.println("Contenido de numeros.txt:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            
            br.close();
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 4: " + ex.getMessage());
        }
    }
    
    public static void metodo5() {
        try {
            System.out.println("\n=== Ejercicio 5: Eliminar múltiplos de 3 ===");
            File archivo = new File("../numeros.txt");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            
            ArrayList<String> lista = new ArrayList<>();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                int num = Integer.parseInt(linea.trim());
                if (num % 3 != 0) {
                    lista.add(linea);
                }
            }
            br.close();
            
            PrintWriter pw = new PrintWriter(new FileWriter(archivo));
            for (String dato : lista) {
                pw.println(dato);
            }
            pw.close();
            
            System.out.println("Múltiplos de 3 eliminados del archivo");
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 5: " + ex.getMessage());
        }
    }
    
    public static boolean verificarPrimo(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        
        for (int contador = 5; contador * contador <= num; contador += 6) {
            if (num % contador == 0 || num % (contador + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void metodo6() {
        try {
            System.out.println("\n=== Ejercicio 6: Números primos ===");
            File archivoOrigen = new File("../numeros.txt");
            File archivoDestino = new File("../../primos.dat");
            
            BufferedReader br = new BufferedReader(new FileReader(archivoOrigen));
            PrintWriter pw = new PrintWriter(new FileWriter(archivoDestino));
            
            String linea;
            while ((linea = br.readLine()) != null) {
                int num = Integer.parseInt(linea.trim());
                if (verificarPrimo(num)) {
                    pw.println(num);
                }
            }
            
            br.close();
            pw.close();
            
            System.out.println("Números primos guardados en 'primos.dat'");
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 6: " + ex.getMessage());
        }
    }
    
    public static void metodo7() {
        try {
            System.out.println("\n=== Ejercicio 7: Palabras con 'ñ' ===");
            File archivo = new File("../caracteres.dat");
            PrintWriter pw = new PrintWriter(new FileWriter(archivo));
            
            System.out.println("Ingrese 10 palabras que contengan la letra 'ñ':");
            for (int contador = 0; contador < 10; contador++) {
                System.out.print("Palabra " + (contador + 1) + ": ");
                String texto = obtenerEntrada();
                pw.println(texto);
            }
            pw.close();
            
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            ArrayList<String> lista = new ArrayList<>();
            String linea;
            
            System.out.println("\nFichero original:");
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
                System.out.println(linea);
            }
            br.close();
            
            PrintWriter pwModificado = new PrintWriter(new FileWriter(archivo));
            System.out.println("\nFichero arreglado:");
            for (String texto : lista) {
                String textoModificado = texto.replace("ñ", "nie-nio");
                pwModificado.println(textoModificado);
                System.out.println(textoModificado);
            }
            pwModificado.close();
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 7: " + ex.getMessage());
        }
    }
    
    public static void metodo8() {
        try {
            System.out.println("\n=== Ejercicio 8: Eliminar lorem de HTML ===");
            
            File archivoHtml = new File("../ejemplo.html");
            PrintWriter pw = new PrintWriter(new FileWriter(archivoHtml));
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head><title>Ejemplo</title></head>");
            pw.println("<body>");
            pw.println("<h1>Mi página web</h1>");
            pw.println("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>");
            pw.println("<p>Contenido importante aquí.</p>");
            pw.println("<p>Más lorem ipsum text aquí también.</p>");
            pw.println("</body>");
            pw.println("</html>");
            pw.close();
            
            BufferedReader br = new BufferedReader(new FileReader(archivoHtml));
            ArrayList<String> lista = new ArrayList<>();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                if (!linea.toLowerCase().contains("lorem")) {
                    lista.add(linea);
                }
            }
            br.close();
            
            PrintWriter pwLimpio = new PrintWriter(new FileWriter(archivoHtml));
            for (String dato : lista) {
                pwLimpio.println(dato);
            }
            pwLimpio.close();
            
            System.out.println("Archivo HTML limpiado de contenido 'lorem'");
            
        } catch (IOException ex) {
            System.err.println("Error en ejercicio 8: " + ex.getMessage());
        }
    }
    
    static class RegistroClima {
        String dia;
        double temp;
        String desc;
        
        public RegistroClima(String dia, double temp, String desc) {
            this.dia = dia;
            this.temp = temp;
            this.desc = desc;
        }
        
        @Override
        public String toString() {
            return dia + "," + temp + "," + desc;
        }
        
        public String presentar() {
            return "Fecha: " + dia + " - Temp: " + temp + "°C - " + desc;
        }
    }
    
    public static void metodo9() {
        try {
            System.out.println("\n=== Ejercicio 9: Sistema de datos climáticos ===");
            File archivoClima = new File("../datos_clima.txt");
            ArrayList<RegistroClima> lista = new ArrayList<>();
            
            if (archivoClima.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivoClima));
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] elementos = linea.split(",");
                    if (elementos.length == 3) {
                        lista.add(new RegistroClima(elementos[0], Double.parseDouble(elementos[1]), elementos[2]));
                    }
                }
                br.close();
            }
            
            String eleccion;
            do {
                System.out.println("\n--- Menú Datos Climáticos ---");
                System.out.println("1. Agregar dato climático");
                System.out.println("2. Mostrar todos los datos");
                System.out.println("3. Borrar registro");
                System.out.println("4. Salir");
                System.out.print("Seleccione opción: ");
                
                eleccion = obtenerEntrada();
                
                switch (eleccion) {
                    case "1":
                        System.out.print("Ingrese fecha (dd/mm/yyyy): ");
                        String dia = obtenerEntrada();
                        System.out.print("Ingrese temperatura: ");
                        double temperatura = Double.parseDouble(obtenerEntrada());
                        System.out.print("Ingrese descripción: ");
                        String descripcion = obtenerEntrada();
                        
                        lista.add(new RegistroClima(dia, temperatura, descripcion));
                        System.out.println("Dato agregado correctamente");
                        break;
                        
                    case "2":
                        if (lista.isEmpty()) {
                            System.out.println("No hay datos registrados");
                        } else {
                            System.out.println("Datos climáticos registrados:");
                            for (int contador = 0; contador < lista.size(); contador++) {
                                System.out.println((contador + 1) + ". " + lista.get(contador).presentar());
                            }
                        }
                        break;
                        
                    case "3":
                        if (lista.isEmpty()) {
                            System.out.println("No hay datos para borrar");
                        } else {
                            System.out.println("Seleccione el número del registro a borrar:");
                            for (int contador = 0; contador < lista.size(); contador++) {
                                System.out.println((contador + 1) + ". " + lista.get(contador).presentar());
                            }
                            System.out.print("Número: ");
                            int pos = Integer.parseInt(obtenerEntrada()) - 1;
                            if (pos >= 0 && pos < lista.size()) {
                                lista.remove(pos);
                                System.out.println("Registro eliminado");
                            } else {
                                System.out.println("Índice inválido");
                            }
                        }
                        break;
                        
                    case "4":
                        System.out.println("Saliendo...");
                        break;
                        
                    default:
                        System.out.println("Opción inválida");
                }
                
            } while (!eleccion.equals("4"));
            
            PrintWriter pw = new PrintWriter(new FileWriter(archivoClima));
            for (RegistroClima registro : lista) {
                pw.println(registro.toString());
            }
            pw.close();
            
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error en ejercicio 9: " + ex.getMessage());
        }
    }
    
    public static List<Integer> obtenerNumeros() {
        try {
            List<Integer> lista = new ArrayList<>();
            System.out.println("\n=== Ejercicio 10: Lista de enteros ===");
            System.out.println("Ingrese números enteros (-99 para terminar):");
            
            String entrada;
            while (true) {
                entrada = obtenerEntrada();
                try {
                    int valor = Integer.parseInt(entrada);
                    if (valor == -99) break;
                    lista.add(valor);
                } catch (NumberFormatException ex) {
                    System.out.println("Por favor ingrese un número entero válido");
                }
            }
            
            return lista;
            
        } catch (IOException ex) {
            System.err.println("Error leyendo valores: " + ex.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static int obtenerSuma(List<Integer> lista) {
        int total = 0;
        for (Integer valor : lista) {
            total += valor;
        }
        return total;
    }
    
    public static void presentarResultados(List<Integer> lista) {
        int total = obtenerSuma(lista);
        double promedio = lista.isEmpty() ? 0 : (double) total / lista.size();
        
        System.out.println("Cantidad de valores: " + lista.size());
        System.out.println("Suma: " + total);
        System.out.println("Media: " + promedio);
        
        System.out.println("Valores leídos:");
        int contadorMayores = 0;
        for (Integer valor : lista) {
            System.out.print(valor + " ");
            if (valor > promedio) {
                contadorMayores++;
            }
        }
        System.out.println();
        System.out.println("Números mayores que la media: " + contadorMayores);
    }
    
    public static void metodo10() {
        List<Integer> lista = obtenerNumeros();
        presentarResultados(lista);
    }
    
    static class Institucion {
        private Map<String, Integer> paises;
        
        public Institucion() {
            this.paises = new HashMap<>();
        }
        
        public void agregarEstudiante(String pais) {
            paises.put(pais, paises.getOrDefault(pais, 0) + 1);
        }
        
        public void mostrarTodos() {
            System.out.println("Nacionalidades y cantidad de alumnos:");
            for (Map.Entry<String, Integer> elemento : paises.entrySet()) {
                System.out.println(elemento.getKey() + ": " + elemento.getValue() + " alumnos");
            }
        }
        
        public void mostrarPais(String pais) {
            int cantidad = paises.getOrDefault(pais, 0);
            System.out.println(pais + ": " + cantidad + " alumnos");
        }
        
        public void contarPaises() {
            System.out.println("Cantidad de nacionalidades diferentes: " + paises.size());
        }
        
        public void limpiar() {
            paises.clear();
            System.out.println("Datos eliminados");
        }
    }
    
    public static void metodo11() {
        try {
            System.out.println("\n=== Ejercicio 11: Colegio ===");
            Institucion centro = new Institucion();
            
            centro.agregarEstudiante("Argentina");
            centro.agregarEstudiante("Brasil");
            centro.agregarEstudiante("Argentina");
            centro.agregarEstudiante("Chile");
            centro.agregarEstudiante("Brasil");
            centro.agregarEstudiante("Argentina");
            
            centro.mostrarTodos();
            centro.mostrarPais("Argentina");
            centro.contarPaises();
            
        } catch (Exception ex) {
            System.err.println("Error en ejercicio 11: " + ex.getMessage());
        }
    }
    
    public static void metodo12() {
        System.out.println("\n=== Ejercicio 12: Días de la semana ===");
        
        List<String> primera = new ArrayList<>();
        primera.add("Lunes");
        primera.add("Martes");
        primera.add("Miércoles");
        primera.add("Jueves");
        primera.add("Viernes");
        primera.add("Sábado");
        primera.add("Domingo");
        
        primera.add(4, "Juernes");
        
        List<String> segunda = new ArrayList<>(primera);
        
        primera.addAll(segunda);
        
        System.out.println("Posición 3: " + primera.get(3));
        System.out.println("Posición 4: " + primera.get(4));
        
        System.out.println("Primer elemento: " + primera.get(0));
        System.out.println("Último elemento: " + primera.get(primera.size() - 1));
        
        boolean removido = primera.remove("Juernes");
        System.out.println("¿Se eliminó 'Juernes'? " + removido);
        
        System.out.println("Elementos usando Iterator:");
        Iterator<String> iterador = primera.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
        
        boolean hallado = false;
        for (String elemento : primera) {
            if (elemento.equalsIgnoreCase("lunes")) {
                hallado = true;
                break;
            }
        }
        System.out.println("¿Existe 'Lunes'? " + hallado);
        
        Collections.sort(primera);
        System.out.println("Lista ordenada: " + primera);
    }
    
    public static void metodo13() {
        System.out.println("\n=== Ejercicio 13: Conjunto de jugadores ===");
        
        Set<String> equipo = new HashSet<>();
        equipo.add("Jordi Alba");
        equipo.add("Pique");
        equipo.add("Busquets");
        equipo.add("Iniesta");
        equipo.add("Messi");
        
        System.out.println("Jugadores del FC Barcelona:");
        Iterator<String> iterador = equipo.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
        
        boolean hayNeymar = equipo.contains("Neymar JR");
        System.out.println("¿Existe 'Neymar JR'? " + hayNeymar);
        
        Set<String> segundo = new HashSet<>();
        segundo.add("Piqué");
        segundo.add("Busquets");
        
        boolean todosPertencen = equipo.containsAll(segundo);
        System.out.println("¿Todos los jugadores de segundo existen en equipo? " + todosPertencen);
        
        Set<String> combinado = new HashSet<>(equipo);
        combinado.addAll(segundo);
        System.out.println("Unión de conjuntos: " + combinado);
        
        boolean insertado = equipo.add("Piqué");
        System.out.println("¿Se agregó 'Piqué' nuevamente? " + insertado);
    }
    
    public static void metodo14() {
        System.out.println("\n=== Ejercicio 14: Generador de lotería ===");
        
        Random rand = new Random();
        
        Set<Integer> rojas = new HashSet<>();
        while (rojas.size() < 6) {
            rojas.add(rand.nextInt(33) + 1);
        }
        
        int azul = rand.nextInt(16) + 1;
        
        System.out.println("Números ganadores:");
        System.out.println("Bolas rojas: " + rojas);
        System.out.println("Bola azul: " + azul);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("=== EJERCICIOS DE I/O Y COLECCIONES ===\n");
            
            metodo1();
            metodo2();
            metodo3();
            metodo4();
            metodo5();
            metodo6();
            metodo7();
            metodo8();
            metodo9();
            
            metodo10();
            metodo11();
            metodo12();
            metodo13();
            metodo14();
            
            System.out.println("\n=== TODOS LOS EJERCICIOS COMPLETADOS ===");
            
        } catch (Exception ex) {
            System.err.println("Error general: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}