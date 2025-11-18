package BatallaNaval;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BNClient {
	
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Server IP [127.0.0.1]: ");
        String ip = kb.readLine().trim(); if (ip.isEmpty()) ip = "127.0.0.1";
        System.out.print("Server port [5000]: ");
        String ps = kb.readLine().trim(); int port = ps.isEmpty() ? 5000 : Integer.parseInt(ps);
        Socket sock = new Socket(ip, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        System.out.print("Tu nombre: "); String name = kb.readLine().trim();
        
        
        out.println(name);
        boolean run = true;
        
        
        while (run) {
            String srv = in.readLine();
            if (srv == null) break;
            if (srv.startsWith("MATCH_FOUND")) {
                System.out.println("Batalla contra: " + srv.split(";")[1]);
            } else if (srv.equals("START_PLACEMENT")) {
                System.out.println("Coloca tus barcos:");
            } else if (srv.startsWith("PLACE_REQUEST")) {
                String[] t = srv.split(";");
                System.out.println("Colocar " + t[1] + " (tamaño " + t[2] + ")");
                int r = -1, c = -1; boolean h = false;
                while (true) {
                    try { System.out.print("fila (0-9): "); r = Integer.parseInt(kb.readLine()); System.out.print("col (0-9): "); c = Integer.parseInt(kb.readLine()); break; }
                    catch (NumberFormatException e) { System.out.println("Fila/col inválida"); }
                }
                while (true) {
                    System.out.print("horizontal (true/false): ");
                    String hh = kb.readLine().trim().toLowerCase();
                    if (hh.equals("true") || hh.equals("false")) { h = Boolean.parseBoolean(hh); break; }
                }
                out.println("PLACE;" + t[1] + ";" + r + ";" + c + ";" + h + ";" + t[2]);
                String resp = in.readLine();
                if ("PLACE_OK".equals(resp)) System.out.println("Colocado OK"); else System.out.println("Falló colocación");
            } else if (srv.equals("PLACEMENT_DONE")) {
                System.out.println("Colocación completa. Esperando al rival...");
            } else if (srv.equals("BOARD_START")) {
                String[] board = new String[10];
                for (int i = 0; i < 10; i++) board[i] = in.readLine();
                for (String l : board) System.out.println(l);
                in.readLine();
            } else if (srv.equals("YOUR_TURN")) {
                System.out.println("Tu turno!");
                int rr = -1, cc = -1;
                while (true) {
                    try { System.out.print("fila (0-9): "); rr = Integer.parseInt(kb.readLine()); System.out.print("col (0-9): "); cc = Integer.parseInt(kb.readLine()); break; }
                    catch (NumberFormatException e) { System.out.println("Fila/col inválida"); }
                }
                out.println("SHOT;" + rr + ";" + cc);
                String res = in.readLine();
                if (res != null && res.startsWith("RESULT;")) {
                    String[] s = res.split(";");
                    System.out.println("Resultado propio: " + s[1] + " en (" + s[2] + "," + s[3] + ")");
                }
            } else if (srv.startsWith("ENEMY_SHOT")) {
                String[] s = srv.split(";");
                System.out.println("El rival disparó: " + s[1] + " en (" + s[2] + "," + s[3] + ")");
            } else if (srv.startsWith("GAME_OVER")) {
                String[] s = srv.split(";");
                System.out.println(s[1].equals("WIN") ? "GANASTE!" : "PERDISTE!");
                run = false;
            } else if (srv.startsWith("ERROR") || srv.startsWith("GAME_ABORTED")) {
                System.out.println("Servidor: " + srv);
                run = false;
            } else if (srv.equals("WAIT")) {
                System.out.println("Esperando turno del rival...");
            } else {
                System.out.println("SERV-> " + srv);
            }
        }
        in.close(); out.close(); sock.close();
        System.out.println("Conexión cerrada.");
    }
}
