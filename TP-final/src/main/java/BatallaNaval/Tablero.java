package BatallaNaval;

import java.util.ArrayList;

public class Tablero {
    public static final int TAMANIO = 10;
    private ArrayList<ArrayList<Casilla>> tablero;
    private ArrayList<Barco> barcos;

    public Tablero() {
        tablero = new ArrayList<>();
        barcos = new ArrayList<>();
        inicializarTablero();
    }

    public void inicializarTablero() {
        tablero.clear();
        for (int i = 0; i < TAMANIO; i++) {
            ArrayList<Casilla> fila = new ArrayList<>();
            for (int j = 0; j < TAMANIO; j++) fila.add(new Casilla());
            tablero.add(fila);
        }
    }

    public boolean colocarBarco(String nombre, int fila, int col, int longitud, boolean horiz) {
        if (fila < 0 || col < 0 || fila >= TAMANIO || col >= TAMANIO) return false;
        if (horiz) {
            if (col + longitud > TAMANIO) return false;
            for (int c = col; c < col + longitud; c++) if (tablero.get(fila).get(c).getTipo() == CasillaType.BARCO) return false;
            Barco b = new Barco(nombre, longitud);
            barcos.add(b);
            for (int c = col; c < col + longitud; c++) {
                Casilla cel = tablero.get(fila).get(c);
                cel.setTipo(CasillaType.BARCO);
                cel.setNombreBarco(nombre);
            }
            return true;
        } else {
            if (fila + longitud > TAMANIO) return false;
            for (int r = fila; r < fila + longitud; r++) if (tablero.get(r).get(col).getTipo() == CasillaType.BARCO) return false;
            Barco b = new Barco(nombre, longitud);
            barcos.add(b);
            for (int r = fila; r < fila + longitud; r++) {
                Casilla cel = tablero.get(r).get(col);
                cel.setTipo(CasillaType.BARCO);
                cel.setNombreBarco(nombre);
            }
            return true;
        }
    }

    public String disparar(int fila, int col) {
        if (fila < 0 || col < 0 || fila >= TAMANIO || col >= TAMANIO) return "INVALID";
        Casilla cel = tablero.get(fila).get(col);
        if (cel.isDisparada()) return "ALREADY";
        cel.setDisparada(true);
        if (cel.getTipo() == CasillaType.BARCO) {
            cel.setTipo(CasillaType.IMPACTO);
            String nb = cel.getNombreBarco();
            for (Barco b : barcos) {
                if (b.getNombre().equals(nb)) {
                    b.recibirImpacto();
                    if (b.estaHundido()) return "HUNDIDO;" + nb;
                    else return "IMPACTO;" + nb;
                }
            }
            return "IMPACTO";
        } else {
            cel.setTipo(CasillaType.FALLO);
            return "AGUA";
        }
    }

    public String[] toLines(boolean ocultar) {
        String[] lines = new String[TAMANIO];
        for (int r = 0; r < TAMANIO; r++) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%2d ", r));
            for (int c = 0; c < TAMANIO; c++) {
                Casilla cel = tablero.get(r).get(c);
                char ch = '~';
                if (cel.getTipo() == CasillaType.BARCO && !ocultar) ch = 'B';
                if (cel.getTipo() == CasillaType.IMPACTO) ch = 'X';
                if (cel.getTipo() == CasillaType.FALLO) ch = 'O';
                sb.append(ch).append(' ');
            }
            lines[r] = sb.toString();
        }
        return lines;
    }

    public void mostrarTablero(boolean ocultar) {
        System.out.print("   ");
        for (int c = 0; c < TAMANIO; c++) System.out.printf("%2d", c);
        System.out.println();
        for (String line : toLines(ocultar)) System.out.println(line);
    }

    public void mostrarBarcos() {
        System.out.println();
        System.out.println("Barcos:");
        for (Barco b : barcos) System.out.println(b.getEstado());
    }

    public boolean todosLosBarcosCaidos() {
        for (Barco b : barcos) if (!b.estaHundido()) return false;
        return true;
    }
}
