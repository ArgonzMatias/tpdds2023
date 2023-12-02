package ar.edu.utn.frba.dds.app.exportador.exportable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Documento implements Exportable {
    private Map<String, List<String>> datos;

    public Documento(){
        this.datos = new HashMap<>();
    }

    public void agregarDato(String clave, String ... valor){
        this.agregarClaveSiNoExiste(clave);
        Collections.addAll(this.datos.get(clave), valor);
    }

    private void agregarClaveSiNoExiste(String clave){
        if(!this.datos.containsKey(clave)){
            this.datos.put(clave, new ArrayList<>());
        }
    }

    public Map<String, List<String>> datos() {
        return this.datos;
    }
}
