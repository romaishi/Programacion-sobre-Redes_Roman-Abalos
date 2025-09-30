package tp_sockets;

import javax.swing.JOptionPane;
import java.io.File;

//Clase para la interfaz de usuario del cliente
public class ClientUI {
    
    ///Muestra el menú principal del cliente
    public static void mostrarMenuPrincipal() {
        ConsoleColors.printInfo("=== CLIENTE DE TRANSMISIÓN DE ARCHIVOS ===");
        ConsoleColors.printInfo("Preparado para conectarse al servidor...");
        System.out.println();
    }
    

    public static File solicitarArchivo() {
        ClientLogger.logInfo("Abriendo selector de archivos...");
        File selectedFile = FileUtils.selectFile();
        
        if (selectedFile == null) {
            ClientLogger.logWarning("Selección de archivo cancelada por el usuario");
        } else {
            ClientLogger.logSuccess("Archivo seleccionado: " + selectedFile.getName());
            ClientLogger.logInfo("Ruta: " + selectedFile.getAbsolutePath());
            ClientLogger.logInfo("Tamaño: " + FileUtils.formatFileSize(selectedFile.length()));
        }
        
        return selectedFile;
    }
    

    public static boolean preguntarContinuar() {
        ClientLogger.logInfo("Preguntando al usuario si desea continuar...");
        boolean continuar = FileUtils.askForContinue();
        
        if (continuar) {
            ClientLogger.logInfo("El usuario eligió enviar otro archivo");
        } else {
            ClientLogger.logInfo("El usuario eligió finalizar la transmisión");
        }
        
        return continuar;
    }
    

    public static void mostrarDespedida() {
        System.out.println();
        ConsoleColors.printSuccess("=== TRANSMISIÓN FINALIZADA ===");
        ConsoleColors.printInfo("Gracias por usar el cliente de transmisión de archivos");
        System.out.println();
    }
    

    public static void mostrarErrorConexion(String mensaje) {
        ConsoleColors.printError("═══ ERROR DE CONEXIÓN ═══");
        ConsoleColors.printError("No se pudo conectar al servidor:");
        ConsoleColors.printError(mensaje);
        ConsoleColors.printInfo("Verifique que el servidor esté ejecutándose");
        System.out.println();
    }
}
