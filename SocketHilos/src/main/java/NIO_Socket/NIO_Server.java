package NIO_Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;

public class NIO_Server {
	//Para un sistema que necesita un uso basico de sockets usamos NIO
	private Selector selector;
	private ServerSocketChannel serverChanel;
	private boolean running = false;
	private final Gson json = new Gson();
	
	private final Map<SocketChannel, String> usuarios = new HashMap<>();
	private Map<String , SocketChannel> canalesPorNombre = new HashMap<>();
	
	// Códigos ANSI para colores en consola
    private static final String RESET = "\u001B[0m";
    private static final String ROJO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String CIAN = "\u001B[36m";
	
	public void start( int port ) throws IOException {
		selector = Selector.open();
		serverChanel = ServerSocketChannel.open();
		serverChanel.configureBlocking(false);	
		serverChanel.register(selector, SelectionKey.OP_ACCEPT);
		serverChanel.bind(new InetSocketAddress(port));
		
		running = true;
		//Colocar mensaje de Server Iniciado
		System.out.print("Servidor Iniciado" + port);
		
		
		
		new Thread(() -> {
			
			
			try {
				while(running)
				{
					selector.select();
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					
					while( iter.hasNext() )
					{
						SelectionKey key = iter.next();
						if(!key.isValid()) continue;
						
						if(key.isAcceptable() )
						{
							ServerSocketChannel server = (ServerSocketChannel)key.channel();							
							SocketChannel client = server.accept();
							
							if( client != null ) {
								client.configureBlocking(false);
								client.register(selector, SelectionKey.OP_READ);
								//colocar mensaje de cliente conectado (client.getRemoteAddress())
								sendMessage(client);
							}
							
						} else if(key.isReadable()) {
							readFromClient(key);
						}
					}
					
				}	
			} catch (IOException ex) {

			} finally {
				running = false
				if(selector != null && selector.isOpen() ) selector.close();
				if( serverChanel != null && serverChanel.isOpen() ) serverChanel.close();
				System.out.print("Servidor cerrado")
			
			}
		}).start()
		
		
	}
    
    public static void main(String[] args) throws IOException {
		NIO_Server servidor = new NIO_Server();
		servidor.start(25565)
    	
	}
	
}
