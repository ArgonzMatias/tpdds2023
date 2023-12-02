package ar.edu.utn.frba.dds.app.entities.georef;

import ar.edu.utn.frba.dds.app.exceptions.UbicacionNoEncontradaException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Provincias {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public Centroide centroide;
    public List<UbicacionGeografica> provincias;

    public Optional<UbicacionGeografica> provinciaDeId(int id){
        return this.provincias.stream()
                .filter(p -> p.id == id)
                .findFirst();
    }

    public UbicacionGeografica getProvinciaDeNombre(String nombre){
        List<UbicacionGeografica> listadoProvincias = provincias.stream().filter(ubicacionGeografica -> Objects.equals(ubicacionGeografica.nombre, nombre)).toList();
        if(listadoProvincias.isEmpty()){
            throw new UbicacionNoEncontradaException("No es posible encontrar la provincia de nombre: " + nombre);
        }
        return listadoProvincias.get(0);
    }

    private class Parametro {
        public List<String> campos;
    }
}
