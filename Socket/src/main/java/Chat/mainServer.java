package Chat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.enumType;

public class mainServer {

	public static void main(String[] args) {
					
			Server servidor;
			try {
				servidor = new Server( enumType.SERVER );
				servidor.serverOn();
			} catch (UnknownHostException ex) {
				Logger.getLogger(mainServer.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(mainServer.class.getName()).log(Level.SEVERE, null, ex);
			}
	}	
}
