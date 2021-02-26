package conexion;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Invitado
 */
public interface Framer {
    public void frameMsg(byte[] message, OutputStream out) throws IOException;
    public byte[] nextMsg() throws IOException;
}
