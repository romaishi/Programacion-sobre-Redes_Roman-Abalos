package BatallaNaval;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerHandler implements Runnable {
    private final Socket sock;
    private final BufferedReader in;
    private final PrintWriter out;
    private final String nombre;
    private final Tablero tab;
    private volatile boolean activo = true;

    public PlayerHandler(Socket s, BufferedReader r, PrintWriter w, String n) {
        this.sock = s; this.in = r; this.out = w; this.nombre = n; this.tab = new Tablero();
    }

    public String getNombre() { return nombre; }
    public Tablero getTablero() { return tab; }
    public BufferedReader getIn() { return in; }
    public PrintWriter getOut() { return out; }
    public boolean isActive() { return activo; }
    public void deactivate() { activo = false; }

    @Override
    public void run() {
        try {
            while (activo && !sock.isClosed()) Thread.sleep(1000);
        } catch (Exception e) {
        } finally {
            try { in.close(); } catch (Exception ex) {}
            try { out.close(); } catch (Exception ex) {}
            try { sock.close(); } catch (Exception ex) {}
        }
    }
}
