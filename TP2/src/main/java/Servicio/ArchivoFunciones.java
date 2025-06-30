package Servicio;


import Modelo.Producto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ArchivoFunciones {
    private static final String RUTA_ARCHIVO = "inventario.dat";

    public static void crearArchivoSiNoExiste() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    public static void guardarProducto(Producto producto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            bw.write(producto.lineaArchivo());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    float precioCompra = Float.parseFloat(partes[1]);
                    float precioVenta = Float.parseFloat(partes[2]);
                    int stock = Integer.parseInt(partes[3]);
                    productos.add(new Producto(nombre, precioCompra, precioVenta, stock));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return productos;
    }

    public static void reescribirArchivo(List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, false))) {
            for (Producto p : productos) {
                bw.write(p.lineaArchivo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }
}
