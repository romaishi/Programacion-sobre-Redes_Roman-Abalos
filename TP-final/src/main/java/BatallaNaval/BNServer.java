package BatallaNaval;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class BNServer {
    private final int port;
    private final Queue<Socket> waitq = new LinkedList<>();

    public BNServer(int port) { this.port = port; }

    public void start() throws Exception {
        ServerSocket srv = new ServerSocket(port);
        System.out.println("Servidor iniciado en puerto " + port);
        while (true) {
            Socket s = srv.accept();
            synchronized (waitq) {
                waitq.add(s);
                if (waitq.size() >= 2) {
                    Socket a = waitq.poll(); Socket b = waitq.poll();
                    try {
                        BufferedReader ra = new BufferedReader(new InputStreamReader(a.getInputStream()));
                        PrintWriter wa = new PrintWriter(a.getOutputStream(), true);
                        String na = ra.readLine();
                        BufferedReader rb = new BufferedReader(new InputStreamReader(b.getInputStream()));
                        PrintWriter wb = new PrintWriter(b.getOutputStream(), true);
                        String nb = rb.readLine();
                        PlayerHandler p1 = new PlayerHandler(a, ra, wa, na);
                        PlayerHandler p2 = new PlayerHandler(b, rb, wb, nb);
                        new Thread(p1).start(); new Thread(p2).start();
                        new Sala(p1, p2).start();
                    } catch (Exception ex) {
                        try { a.close(); } catch (Exception e) {} try { b.close(); } catch (Exception e) {}
                    }
                } else {
                    System.out.println("Jugador conectado. Esperando otro jugador...");
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 5000;
        BNServer s = new BNServer(port);
        try { s.start(); } catch (Exception e) { e.printStackTrace(); }
    }
}
