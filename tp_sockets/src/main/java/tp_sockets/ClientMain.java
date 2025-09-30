package tp_sockets;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.ConnectException;
import java.net.UnknownHostException;


public class ClientMain {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;
    
    public static void main(String[] args) {
        ClientUI.mostrarMenuPrincipal();
        
        Socket socket = null;
        boolean continuarEnviando = true;
        
        try {
            // Intentar conectar al servidor
            ClientLogger.logInfo("Intentando conectar al servidor en " + SERVER_HOST + ":" + SERVER_PORT);
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            ClientLogger.logSuccess("✓ Conexión establecida con el servidor");
            System.out.println();
            
            // Ciclo principal para envío de múltiples archivos
            while (continuarEnviando) {
                // Solicitar archivo al usuario
                File archivoSeleccionado = ClientUI.solicitarArchivo();
                
                if (archivoSeleccionado == null) {
                    ClientLogger.logWarning("No se seleccionó ningún archivo");
                    continuarEnviando = ClientUI.preguntarContinuar();
                    continue;
                }
                
                // Verificar que el archivo existe y es legible
                if (!archivoSeleccionado.exists()) {
                    ClientLogger.logError("El archivo seleccionado no existe");
                    continuarEnviando = ClientUI.preguntarContinuar();
                    continue;
                }
                
                if (!archivoSeleccionado.canRead()) {
                    ClientLogger.logError("No se puede leer el archivo seleccionado");
                    continuarEnviando = ClientUI.preguntarContinuar();
                    continue;
                }
                
                if (archivoSeleccionado.length() == 0) {
                    ClientLogger.logWarning("El archivo seleccionado está vacío");
                    continuarEnviando = ClientUI.preguntarContinuar();
                    continue;
                }
                
                try {
                    // Enviar el archivo
                    System.out.println();
                    ClientLogger.logInfo("Iniciando transmisión del archivo...");
                    FileSender.sendFile(socket, archivoSeleccionado);
                    System.out.println();
                    
                    // Preguntar si desea enviar otro archivo
                    continuarEnviando = ClientUI.preguntarContinuar();
                    
                } catch (IOException e) {
                    ClientLogger.logError("Error durante la transmisión: " + e.getMessage());
                    continuarEnviando = false; // Terminar en caso de error de transmisión
                }
            }
            
        } catch (ConnectException e) {
            ClientUI.mostrarErrorConexion("Servidor no disponible - " + e.getMessage());
        } catch (UnknownHostException e) {
            ClientUI.mostrarErrorConexion("Host desconocido - " + e.getMessage());
        } catch (IOException e) {
            ClientLogger.logError("Error de conexión: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                    ClientLogger.logInfo("Conexión cerrada correctamente");
                } catch (IOException e) {
                    ClientLogger.logError("Error al cerrar la conexión: " + e.getMessage());
                }
            }
            
            ClientUI.mostrarDespedida();
        }
    }
}
