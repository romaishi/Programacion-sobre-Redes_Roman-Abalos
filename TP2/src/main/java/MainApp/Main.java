package MainApp;

import Servicio.ArchivoFunciones;
import Servicio.InventarioFunciones;

import Utils.Consola;

public class Main {
    public static void main(String[] args) {
        ArchivoFunciones.crearArchivoSiNoExiste();

        while (true) {
            mostrarMenu();
            String opcion = Consola.leerLinea("Seleccione una opción: ");
            switch (opcion) {
                case "1":
                    InventarioFunciones.agregarProducto();
                    break;
                case "2":
                	InventarioFunciones.mostrarInventario();
                    break;
                case "3":
                	InventarioFunciones.editarProducto();
                    break;
                case "4":
                	InventarioFunciones.eliminarProducto();
                    break;
                case "5":
                    System.out.println(Utils.Colors.ANSI_RED + "\nSaliendo del sistema... ¡Hasta luego!\n" + Utils.Colors.ANSI_RESET);
                    return;
                default:
                    System.out.println(Utils.Colors.ANSI_RED + "Opción inválida. Intente nuevamente.\n" + Utils.Colors.ANSI_RESET);
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println(Utils.Colors.ANSI_BLUE + "\n\t====== MENÚ PRINCIPAL ======"  + Utils.Colors.ANSI_RESET);
        System.out.println("\t1. Agregar producto");
        System.out.println("\t2. Mostrar inventario");
        System.out.println("\t3. Editar producto");
        System.out.println("\t4. Eliminar producto");
        System.out.println("\t5. Salir");
    }
}
