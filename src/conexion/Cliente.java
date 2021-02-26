package conexion;

import gui.GUIObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 *
 * @author Invitado
 */
public class Cliente implements Runnable, Framer {

    private Socket socket;
    private final GUIObserver observer;
    private OutputStream out;
    private InputStream in;
    private static final byte DELIMITADOR = '~'; //delimitador del mensaje

    public Cliente(GUIObserver observer) {
        this.observer = observer;
    }

    public void escuchar() {
        System.out.println("El sistema Kardex esta conectado al servidor.");
        try {
            socket = new Socket("127.0.0.1", 9001);
            out = socket.getOutputStream();

            while (true) {
                System.out.println("Sistema Kardex esta en la escucha");
                System.out.println("----");
                in = socket.getInputStream();
//                byte[] bytes = new byte[esperarDatos(in)];
//                in.read(bytes);
                String recibido = deserializar(nextMsg());
                System.out.println("Enviando: " + recibido);
                System.out.println("----");
                notificar(recibido);
            }

        } catch (Exception ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
        }
    }

    public void enviar(String contenido) {
        try {
            System.out.println("Enviando: " + contenido);
            System.out.println("----");
            byte[] bytes = serializar(contenido);
            frameMsg(bytes, out);
//          out.write(bytes);
//          out.flush();
        } catch (Exception ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
        }
    }

    private int esperarDatos(InputStream in) throws IOException {

        int tam;
        while ((tam = in.available()) == 0) {
            if (tam > 0) {
                break;
            }
        }
        return tam;
    }

    private static byte[] serializar(String cadena) throws IOException {
        return cadena.getBytes();
    }

    private static String deserializar(byte[] datos) throws IOException, ClassNotFoundException {
        return new String(datos, StandardCharsets.UTF_8);
    }

    private void notificar(String contenido) {
        observer.update(contenido);
    }

    @Override
    public void run() {
        escuchar();
    }

    @Override
    public void frameMsg(byte[] mensaje, OutputStream out) throws IOException {
        for (byte b : mensaje) {
            if(b==DELIMITADOR){
                throw new IOException("El mensaje contiene el delimitador.");
            }
        }
        out.write(mensaje);
        out.write(DELIMITADOR);
        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream msgBuffer=new ByteArrayOutputStream();
        int sigByte;
        
        while((sigByte=in.read())!=DELIMITADOR){
            if(sigByte==-1){
                if(msgBuffer.size()==0){
                    return null;
                }else{
                    throw new IOException("Mensaje sin delimitador.");
                }
            }
            msgBuffer.write(sigByte);
        }
        return msgBuffer.toByteArray();
    }
}
