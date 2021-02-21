package dominio;

import java.util.Objects;

/**
 *
 * @author Invitado
 */
public class Kardex {
    private String idAlumno;
    private int[] calificaciones;

    public Kardex(String idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public Kardex(String idAlumno, int[] calificaciones) {
        this.idAlumno = idAlumno;
        this.calificaciones = calificaciones;
    }

    @Override
    public String toString() {
        String datos="";
        datos+=idAlumno+".";
        for (int cal : calificaciones) {
            datos+=cal+".";
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
