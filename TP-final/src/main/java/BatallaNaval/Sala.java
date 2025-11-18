package BatallaNaval;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class Sala extends Thread {
    private final PlayerHandler p1;
    private final PlayerHandler p2;
    private final String[] names = {"Portaaviones","Acorazado","Crucero","Submarino","Destructor"};
    private final int[] lens = {5,4,3,3,2};

    public Sala(PlayerHandler a, PlayerHandler b) {
        this.p1 = a; this.p2 = b;
        setName("GameRoom-" + p1.getNombre() + "-vs-" + p2.getNombre());
    }

    private void sendBoard(PrintWriter out, Tablero t, boolean ocult) {
        out.println("BOARD_START");
        String[] lines = t.toLines(ocult);
        for (String l : lines) out.println(l);
        out.println("BOARD_END");
        out.flush();
    }

    private void solicitarColocacion(PlayerHandler p) throws IOException {
        PrintWriter out = p.getOut();
        BufferedReader in = p.getIn();
        out.println("START_PLACEMENT"); out.flush();
        for (int i = 0; i < names.length; i++) {
            boolean ok = false;
            while (!ok) {
                out.println("PLACE_REQUEST;" + names[i] + ";" + lens[i]); out.flush();
                String msg = in.readLine();
                if (msg == null) throw new IOException("cliente desconectado");
                String[] tok = msg.split(";");
                if (tok.length < 6 || !tok[0].equals("PLACE")) { out.println("ERROR;Formato invalido"); out.flush(); continue; }
                String ship = tok[1];
                int r = Integer.parseInt(tok[2]);
                int c = Integer.parseInt(tok[3]);
                boolean h = Boolean.parseBoolean(tok[4]);
                int L = Integer.parseInt(tok[5]);
                ok = p.getTablero().colocarBarco(ship, r, c, L, h);
                out.println(ok ? "PLACE_OK" : "PLACE_FAIL"); out.flush();
            }
            sendBoard(out, p.getTablero(), false);
        }
        out.println("PLACEMENT_DONE"); out.flush();
    }

    @Override
    public void run() {
        try {
            p1.getOut().println("MATCH_FOUND;" + p2.getNombre()); p1.getOut().flush();
            p2.getOut().println("MATCH_FOUND;" + p1.getNombre()); p2.getOut().flush();

            solicitarColocacion(p1);
            solicitarColocacion(p2);

            sendBoard(p1.getOut(), p1.getTablero(), false);
            sendBoard(p1.getOut(), p2.getTablero(), true);
            sendBoard(p2.getOut(), p2.getTablero(), false);
            sendBoard(p2.getOut(), p1.getTablero(), true);

            PlayerHandler cur = ThreadLocalRandom.current().nextBoolean() ? p1 : p2;
            PlayerHandler oth = cur == p1 ? p2 : p1;
            cur.getOut().println("YOUR_TURN"); cur.getOut().flush();
            oth.getOut().println("WAIT"); oth.getOut().flush();

            boolean fin = false;
            while (!fin) {
                String shotMsg = cur.getIn().readLine();
                if (shotMsg == null) break;
                if (shotMsg.startsWith("SHOT;")) {
                    String[] parts = shotMsg.split(";");
                    int rr = Integer.parseInt(parts[1]);
                    int cc = Integer.parseInt(parts[2]);
                    String res = oth.getTablero().disparar(rr, cc);
                    cur.getOut().println("RESULT;" + res + ";" + rr + ";" + cc); cur.getOut().flush();
                    oth.getOut().println("ENEMY_SHOT;" + res + ";" + rr + ";" + cc); oth.getOut().flush();

                    sendBoard(cur.getOut(), cur.getTablero(), false);
                    sendBoard(cur.getOut(), oth.getTablero(), true);
                    sendBoard(oth.getOut(), oth.getTablero(), false);
                    sendBoard(oth.getOut(), cur.getTablero(), true);

                    if (oth.getTablero().todosLosBarcosCaidos()) {
                        cur.getOut().println("GAME_OVER;WIN"); cur.getOut().flush();
                        oth.getOut().println("GAME_OVER;LOSE"); oth.getOut().flush();
                        fin = true; break;
                    }

                    PlayerHandler tmp = cur; cur = oth; oth = tmp;
                    cur.getOut().println("YOUR_TURN"); cur.getOut().flush();
                    oth.getOut().println("WAIT"); oth.getOut().flush();
                } else {
                    cur.getOut().println("ERROR;UnknownCommand"); cur.getOut().flush();
                }
            }
        } catch (IOException ex) {
            try { p1.getOut().println("GAME_ABORTED"); } catch (Exception e) {}
            try { p2.getOut().println("GAME_ABORTED"); } catch (Exception e) {}
        } finally {
            p1.deactivate(); p2.deactivate();
        }
    }
}
