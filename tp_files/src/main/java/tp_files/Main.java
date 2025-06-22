package tp_files;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        EntradaDatos entrada = new EntradaDatos();
        int[] vector = entrada.IngresarDatosVector();

        // Guardar vector en archivo
        ManejadorArchivo archivo = new ManejadorArchivo("datos.txt");
        archivo.crearFileConBuffer(archivo.getFiles(), vector);

        // Procesar divisiones y generar archivos de salida
        Procesos proc = new Procesos();
        proc.procesarVector(vector);
    }
}
