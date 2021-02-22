package conexion;

import gui.GUIObserver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Invitado
 */
public class Cliente implements Runnable {

    private Socket socket;
    private final GUIObserver observer;
    private OutputStream out;
    private InputStream in;

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
                byte[] bytes = new byte[esperarDatos(in)];
                in.read(bytes);
                String recibido = deserializar(bytes);
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
            out.write(bytes);
            out.flush();
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
}
