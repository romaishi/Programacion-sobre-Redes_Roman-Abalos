package Chat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clientMain {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5000;

        try {
            Client cliente = new Client(Utils.enumType.CLIENT, ip, port);
            cliente.setIp(ip);
            cliente.setPort(port);

            cliente.clientOn();
        } catch (UnknownHostException ex) {
            Logger.getLogger(clientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(clientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
