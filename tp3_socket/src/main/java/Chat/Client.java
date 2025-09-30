package Chat;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Utils.Colors;

public class Client extends Connection {

    private DataInputStream disClient = null;
    private DataOutputStream dosClient = null;

    public Client(Utils.enumType type, String ip, int port) throws UnknownHostException, IOException {
        super(type, ip, port);
    }

    public void clientOn() {
        try {
            disClient = new DataInputStream(new java.io.BufferedInputStream(sockC.getInputStream()));
            dosClient = new DataOutputStream(new java.io.BufferedOutputStream(sockC.getOutputStream()));

            ps.println(Colors.ANSI_GREEN + "Conexión exitosa al servidor." + Colors.ANSI_RESET);


            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) { }

            boolean seguir = true;
            while (seguir) {
 
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Seleccioná el archivo a enviar");
                int result = chooser.showOpenDialog(null);
                if (result != JFileChooser.APPROVE_OPTION) {
                    ps.println(Colors.ANSI_BLUE + "No se seleccionó archivo. Terminando envío." + Colors.ANSI_RESET);

                    dosClient.writeBoolean(false);
                    dosClient.flush();
                    break;
                }

                File file = chooser.getSelectedFile();
                if (!file.exists() || !file.isFile()) {
                    ps.println(Colors.ANSI_RED + "Archivo inválido o no existe." + Colors.ANSI_RESET);

                    int r = JOptionPane.showConfirmDialog(null, "Archivo inválido. ¿Desea intentar con otro archivo?", "Error", JOptionPane.YES_NO_OPTION);
                    if (r != JOptionPane.YES_OPTION) {
                        dosClient.writeBoolean(false);
                        dosClient.flush();
                        break;
                    } else {
                        continue;
                    }
                }


                dosClient.writeBoolean(true);
                dosClient.flush();

                String filename = file.getName();
                long filesize = file.length();

                ps.printf(Colors.ANSI_BLUE + "Enviando archivo: %s (%,d bytes)\n" + Colors.ANSI_RESET, filename, filesize);


                dosClient.writeUTF(filename);
                dosClient.writeLong(filesize);

                try (FileInputStream fis = new FileInputStream(file);
                     BufferedInputStream bis = new BufferedInputStream(fis)) {

                    byte[] buffer = new byte[4096];
                    int read;
                    long total = 0;
                    while ((read = bis.read(buffer)) > 0) {
                        dosClient.write(buffer, 0, read);
                        total += read;
                    }
                    dosClient.flush();
                } catch (IOException ex) {
                    ps.println(Colors.ANSI_RED + "Error al leer/enviar el archivo: " + ex.getMessage() + Colors.ANSI_RESET);
 
                    try {
                        dosClient.writeUTF("ERROR");
                        dosClient.flush();
                    } catch (IOException e) {}
                    break;
                }

   
                String ack = disClient.readUTF();
                if ("OK".equals(ack)) {
                    ps.println(Colors.ANSI_GREEN + "Archivo enviado correctamente." + Colors.ANSI_RESET);
                } else {
                    ps.println(Colors.ANSI_RED + "Servidor indicó error en la recepción." + Colors.ANSI_RESET);
                }


                int resp = JOptionPane.showConfirmDialog(null, "¿Desea enviar otro archivo?", "Enviar otro?", JOptionPane.YES_NO_OPTION);
                if (resp != JOptionPane.YES_OPTION) {
   
                    dosClient.writeBoolean(false);
                    dosClient.flush();
                    seguir = false;

            }

        } 
        
    }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            ps.println(Colors.ANSI_RED + "Error de conexión/transmisión: " + ex.getMessage() + Colors.ANSI_RESET);
        } finally {
            try {
                if (dosClient != null) dosClient.close();
                if (disClient != null) disClient.close();
                if (sockC != null && !sockC.isClosed()) sockC.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
