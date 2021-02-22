/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Kardex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        HashMap<String, Integer> materias = new HashMap<>();
        
        materias.put("Programación", 9);
        materias.put("Diseño de Software", 10);
        materias.put("Emprendimiento", 8);
        lstKardex.add(new Kardex("204270", (HashMap<String, Integer>) materias.clone()));

        materias.clear();
        materias.put("Programación", 10);
        materias.put("Diseño de Software", 8);
        materias.put("Emprendimiento", 9);
        lstKardex.add(new Kardex("204271", (HashMap<String, Integer>) materias.clone()));
        
        materias.clear();
        materias.put("Programación", 10);
        materias.put("Diseño de Software", 8);
        materias.put("Emprendimiento", 8);
        lstKardex.add(new Kardex("204272", (HashMap<String, Integer>) materias.clone()));
        
        materias.clear();
        materias.put("Programación", 8);
        materias.put("Diseño de Software", 10);
        materias.put("Emprendimiento", 8);
        lstKardex.add(new Kardex("204273", (HashMap<String, Integer>) materias.clone()));
        
        materias.clear();
        materias.put("Programación", 7);
        materias.put("Diseño de Software", 10);
        materias.put("Emprendimiento", 7);
        lstKardex.add(new Kardex("204274", (HashMap<String, Integer>) materias.clone()));
    }

}
