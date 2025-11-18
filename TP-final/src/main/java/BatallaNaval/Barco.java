package BatallaNaval;

public class Barco {
    private final String nombre;
    private final int longitud;
    private int impactos;

    public Barco(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.impactos = 0;
    }

    public String getNombre() { return nombre; }
    public int getLongitud() { return longitud; }
    public int getImpactos() { return impactos; }
    public void recibirImpacto() { impactos++; }
    public boolean estaHundido() { return impactos >= longitud; }
    public String getEstado() {

    	
    	
    	String est = estaHundido() ? "HUNDIDO" : "ACTIVO";
        return String.format("%-12s len=%d impactos=%d %s", nombre, longitud, impactos, est);
    }
}
