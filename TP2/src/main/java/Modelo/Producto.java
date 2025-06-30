package Modelo;

public class Producto {
    public String nombre;
    public float precioCompra;
    public float precioVenta;
    public int stock;

    public Producto(String nombre, float precioCompra, float precioVenta, int stock) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public String lineaArchivo() {
        return nombre + ";" + precioCompra + ";" + precioVenta + ";" + stock;
    }

    public String stringFormateado() {
    	//Esta linea me la tiro ChatGPT, esta buena
    	//Ademas cada secuencia de caracteres detras de cada % hace que el texto tenga un formato diferente
        return String.format("Nombre: %-15s | Compra: $%-8.2f | Venta: $%-8.2f | Stock: %d",
                nombre, precioCompra, precioVenta, stock);
    }
}