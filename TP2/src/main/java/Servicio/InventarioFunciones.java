package Servicio;

import Modelo.Producto;
import Utils.Consola;
import java.util.List;

public class InventarioFunciones {

    public static void agregarProducto() {
        System.out.println(Utils.Colors.ANSI_BLUE + "\n\t--- Agregar nuevo producto ---" + Utils.Colors.ANSI_RESET);

        String nombre = Consola.leerLinea("Ingrese nombre del producto: ");
        float precioCompra = leerFloat("Ingrese precio de compra: ");
        float precioVenta = leerFloat("Ingrese precio de venta: ");
        int stock = leerInt("Ingrese stock: ");

        Producto producto = new Producto(nombre, precioCompra, precioVenta, stock);
        ArchivoFunciones.guardarProducto(producto);

        System.out.println(Utils.Colors.ANSI_GREEN + "\nProducto agregado exitosamente.\n" + Utils.Colors.ANSI_RESET);
    }

    public static void mostrarInventario() {
        System.out.println(Utils.Colors.ANSI_BLUE + "\n\t--- Inventario actual ---\n" + Utils.Colors.ANSI_RESET);
        List<Producto> productos = ArchivoFunciones.leerProductos();
        if (productos.isEmpty()) {
            System.out.println(Utils.Colors.ANSI_RED + "No hay productos registrados.\n" + Utils.Colors.ANSI_RESET);
        } else {
            for (int i = 0; i < productos.size(); i++) {
                System.out.printf("[%d] %s\n", i + 1, productos.get(i).stringFormateado());
            }
        }
    }

    public static void eliminarProducto() {
        mostrarInventario();
        List<Producto> productos = ArchivoFunciones.leerProductos();
        if (productos.isEmpty()) return;

        int index = leerInt("Ingrese el número del producto a eliminar: ") - 1;
        if (index >= 0 && index < productos.size()) {
            productos.remove(index);
            ArchivoFunciones.reescribirArchivo(productos);
            System.out.println(Utils.Colors.ANSI_GREEN + "Producto eliminado correctamente.\n" + Utils.Colors.ANSI_RESET);
        } else {
            System.out.println(Utils.Colors.ANSI_RED + "Índice inválido.\n" + Utils.Colors.ANSI_RESET);
        }
    }

    public static void editarProducto() {
        mostrarInventario();
        List<Producto> productos = ArchivoFunciones.leerProductos();
        if (productos.isEmpty()) return;

        int index = leerInt("Ingrese el número del producto a editar: ") - 1;
        if (index >= 0 && index < productos.size()) {
            Producto original = productos.get(index);
            System.out.println(Utils.Colors.ANSI_CYAN + "\nEdición del producto: " + original.nombre);

            String nombre = Consola.leerLinea("Nuevo nombre (" + original.nombre + "): ");
            float precioCompra = leerFloat("Nuevo precio de compra (" + original.precioCompra + "): ");
            float precioVenta = leerFloat("Nuevo precio de venta (" + original.precioVenta + "): ");
            int stock = leerInt("Nuevo stock (" + original.stock + "): ");

            productos.set(index, new Producto(nombre, precioCompra, precioVenta, stock));
            ArchivoFunciones.reescribirArchivo(productos);
            System.out.println(Utils.Colors.ANSI_GREEN + "Producto editado correctamente.\n" + Utils.Colors.ANSI_RESET);
        } else {
            System.out.println(Utils.Colors.ANSI_RED + "Índice inválido.\n" + Utils.Colors.ANSI_RESET);
        }
    }

    // Helpers internos
    private static int leerInt(String mensaje) {
        while (true) {
            String input = Consola.leerLinea(mensaje);
            if ("Es un Entero".equals(Consola.esNumero(input, null, null, null))) {
                return Consola.convertirEntero(input);
            }
        }
    }

    public static float leerFloat(String mensaje) {
        String entrada;
        while (true) {
            entrada = Consola.leerLinea(mensaje).replace(",", ".").replace("$", "");
            try {
                return Float.parseFloat(entrada);
            } catch (NumberFormatException e) {
                System.out.println(Utils.Colors.ANSI_RED  + "Ingrese un número válido (puede tener coma o punto decimal)." + Utils.Colors.ANSI_RESET);
            }
        }
    }

}