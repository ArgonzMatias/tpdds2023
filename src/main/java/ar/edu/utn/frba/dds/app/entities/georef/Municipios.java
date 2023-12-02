package ar.edu.utn.frba.dds.app.entities.georef;

import ar.edu.utn.frba.dds.app.exceptions.UbicacionNoEncontradaException;

import java.util.List;
import java.util.Objects;

public class Municipios {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public Provincias provincias;
    public Centroide centroide; // nueva clase Centroide con latitud y longitud
    public List<UbicacionGeografica> municipios;

    private class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincia;
    }

    public UbicacionGeografica getMunicipioDeNombre(String nombre){
        List<UbicacionGeografica> listadoMunicipios = municipios.stream().filter(ubicacionGeografica -> Objects.equals(ubicacionGeografica.nombre, nombre)).toList();
        if(listadoMunicipios.isEmpty()){
            throw new UbicacionNoEncontradaException("No es posible encontrar el departamento de nombre: " + nombre);
        }
        return listadoMunicipios.get(0);
    }
}
