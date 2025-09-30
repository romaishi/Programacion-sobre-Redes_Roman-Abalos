package tp_sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class ServerMain {
    private static final int PORT = 5000;
    private static boolean serverRunning = true;
    private static ServerSocket serverSocketGlobal = null; // Variable global para el shutdown hook
    
    public static void main(String[] args) {
        mostrarBanner();
        
        try {
            // Crear el socket del servidor
            serverSocketGlobal = new ServerSocket(PORT);
            ServerSocket serverSocket = serverSocketGlobal; // Variable local final
            
            ServerLogger.logSuccess("✓ Servidor iniciado en el puerto " + PORT);
            ServerLogger.logInfo("Esperando conexiones de clientes...");
            System.out.println();
            
            // Agregar hook para cierre limpio del servidor
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                ServerLogger.logInfo("Cerrando servidor...");
                serverRunning = false;
                try {
                    if (serverSocketGlobal != null && !serverSocketGlobal.isClosed()) {
                        serverSocketGlobal.close();
                    }
                } catch (IOException e) {
                    ServerLogger.logError("Error al cerrar el servidor: " + e.getMessage());
                }
            }));
            
            // Ciclo principal del servidor
            while (serverRunning) {
                try {
                    // Aceptar conexión de cliente
                    Socket clientSocket = serverSocket.accept();
                    String clientInfo = clientSocket.getInetAddress().getHostAddress() + 
                                      ":" + clientSocket.getPort();
                    
                    ServerLogger.logSuccess("✓ Cliente conectado desde: " + clientInfo);
                    System.out.println();
                    
                    // Manejar la sesión del cliente
                    handleClientSession(clientSocket, clientInfo);
                    
                } catch (SocketException e) {
                    if (serverRunning) {
                        ServerLogger.logError("Error en socket del servidor: " + e.getMessage());
                    }
                    break;
                } catch (IOException e) {
                    ServerLogger.logError("Error al aceptar conexión: " + e.getMessage());
                }
            }
            
        } catch (IOException e) {
            ServerLogger.logError("Error fatal del servidor: " + e.getMessage());
            ServerLogger.logError("No se pudo iniciar el servidor en el puerto " + PORT);
        } finally {
            // Cerrar el servidor
            if (serverSocketGlobal != null && !serverSocketGlobal.isClosed()) {
                try {
                    serverSocketGlobal.close();
                    ServerLogger.logInfo("Servidor cerrado correctamente");
                } catch (IOException e) {
                    ServerLogger.logError("Error al cerrar el servidor: " + e.getMessage());
                }
            }
            
            mostrarDespedida();
        }
    }
    
 
    private static void handleClientSession(Socket clientSocket, String clientInfo) {
        try {
            // Recibir archivos del cliente hasta que cierre la conexión
            while (!clientSocket.isClosed() && clientSocket.isConnected()) {
                try {
                    ServerLogger.logInfo("Esperando archivo del cliente: " + clientInfo);
                    FileReceiver.receiveFile(clientSocket);
                    System.out.println();
                    
                } catch (IOException e) {
                    // Si el cliente cierra la conexión normalmente, no es un error
                    if (e.getMessage().contains("Connection reset") || 
                        e.getMessage().contains("Socket closed") ||
                        e.getMessage().contains("Connection closed")) {
                        ServerLogger.logInfo("Cliente desconectado: " + clientInfo);
                    } else {
                        ServerLogger.logError("Error al recibir archivo: " + e.getMessage());
                    }
                    break;
                }
            }
            
        } finally {
            // Cerrar la conexión con el cliente
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                }
                ServerLogger.logInfo("Conexión cerrada con cliente: " + clientInfo);
                System.out.println();
                ServerLogger.logInfo("Esperando nuevas conexiones...");
                System.out.println();
                
            } catch (IOException e) {
                ServerLogger.logError("Error al cerrar conexión con cliente: " + e.getMessage());
            }
        }
    }
    

    private static void mostrarBanner() {
        System.out.println();
        ConsoleColors.printSuccess("╔═══════════════════════════════════════════╗");
        ConsoleColors.printSuccess("║       SERVIDOR DE ARCHIVOS - TCP         ║");
        ConsoleColors.printSuccess("╚═══════════════════════════════════════════╝");
        System.out.println();
        ConsoleColors.printInfo("Características:");
        ConsoleColors.printInfo("• Puerto de escucha: " + PORT);
        ConsoleColors.printInfo("• Transferencia por bloques de 4KB");
        ConsoleColors.printInfo("• Soporte para múltiples archivos");
        ConsoleColors.printInfo("• Almacenamiento en directorio 'archivos_recibidos'");
        System.out.println();
        ConsoleColors.printWarning("Presiona Ctrl+C para detener el servidor");
        System.out.println();
    }
    

    private static void mostrarDespedida() {
        System.out.println();
        ConsoleColors.printSuccess("═══════════════════════════════════════════");
        ConsoleColors.printSuccess("    SERVIDOR DETENIDO CORRECTAMENTE");
        ConsoleColors.printSuccess("═══════════════════════════════════════════");
        System.out.println();
    }
}
