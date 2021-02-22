package dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Invitado
 */
public class Kardex {
    private String idAlumno;
    private HashMap<String, Integer> calificaciones;

    public Kardex(String idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public Kardex(String idAlumno, HashMap<String, Integer> calificaciones) {
        this.idAlumno = idAlumno;
        this.calificaciones = calificaciones;
    }

    @Override
    public String toString() {
        String datos="";
        datos+=idAlumno+".";
        
        for (Map.Entry<String, Integer> entry : calificaciones.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
            datos+=entry.getKey()+"."+entry.getValue()+".";
        }
                
        datos=datos.substring(0,datos.length()-1);
        
        return datos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idAlumno);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kardex other = (Kardex) obj;
        if (!Objects.equals(this.idAlumno, other.idAlumno)) {
            return false;
        }
        return true;
    }
    
    
}
