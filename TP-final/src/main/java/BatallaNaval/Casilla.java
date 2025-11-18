package BatallaNaval;

public class Casilla {
    private CasillaType tipo;
    private boolean disparada;
    private String nombreBarco;

    public Casilla() {
        this.tipo = CasillaType.AGUA;
        this.disparada = false;
        this.nombreBarco = null;
    }

    public CasillaType getTipo() { return tipo; }
    public void setTipo(CasillaType tipo) { this.tipo = tipo; }
    public boolean isDisparada() { return disparada; }
    public void setDisparada(boolean d) { this.disparada = d; }
    public String getNombreBarco() { return nombreBarco; }
    public void setNombreBarco(String n) { this.nombreBarco = n; }
}
