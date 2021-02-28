package datos;

import dominio.Kardex;
import dominio.Materia;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author angel
 */
public class BaseDeDatosKardex {

    private ArrayList<Kardex> lstKardex = new ArrayList<>();

    public ArrayList<Kardex> getLstKardex() {
        return lstKardex;
    }

    public void inicializarKardex() {
        HashMap<Materia, Integer> materias = new HashMap<>();
        
        final String ID_BORREGO="1234";
        final String ID_VALTIERRA="4321";
        final String ID_CABADA="0000";
        
        Materia materia1=new Materia(1, "Sistemas Distribuidos", ID_BORREGO);
        Materia materia2=new Materia(2, "Matemáticas Computacionales", ID_VALTIERRA);
        Materia materia3=new Materia(3, "Matemáticas Discretas", ID_VALTIERRA);
        Materia materia4=new Materia(4, "Sistemas Operativos", ID_CABADA);
        
        materias.put(materia1, 9);
        materias.put(materia2, 10);
        lstKardex.add(new Kardex("00000204270", (HashMap<Materia, Integer>) materias.clone()));

        materias.clear();
        materias.put(materia2, 10);
        materias.put(materia3, 8);
        lstKardex.add(new Kardex("00000204271", (HashMap<Materia, Integer>) materias.clone()));
        
        materias.clear();
        materias.put(materia3, 10);
        materias.put(materia4, 8);
        lstKardex.add(new Kardex("00000204272", (HashMap<Materia, Integer>) materias.clone()));
        
        materias.clear();
        materias.put(materia1, 8);
        materias.put(materia4, 10);
        lstKardex.add(new Kardex("00000204273", (HashMap<Materia, Integer>) materias.clone()));
        
        materias.clear();
        materias.put(materia2, 7);
        materias.put(materia4, 10);
        lstKardex.add(new Kardex("00000204274", (HashMap<Materia, Integer>) materias.clone()));
    }

    
    /**
     * Actualizar la calificación de la materia de un alumno en la base de datos.
     * @param idMaestro ID del maestro que imparte la materia.
     * @param idAlumno ID del alumno al que se desea actualizar la calificación.
     * @param idMateria ID de la materia en la que se desea actualizar calificación.
     * @param calificacion Nueva calificación para el alumno.
     */
    public void actualizarCalificacion(String idMaestro, String idAlumno, int idMateria, int calificacion){
        
        for (Kardex kardex : lstKardex) {
            if(kardex.getIdAlumno().equals(idAlumno)){
                Kardex k=kardex;
                HashMap<Materia, Integer> cals=k.getCalificaciones();

                Materia materia=new Materia(idMateria, idMaestro);
                
                cals.put(materia, calificacion);
                
                break;
            }
        }
    }
}
