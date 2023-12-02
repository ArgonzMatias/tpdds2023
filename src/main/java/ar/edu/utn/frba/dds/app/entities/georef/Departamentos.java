package ar.edu.utn.frba.dds.app.entities.georef;

import ar.edu.utn.frba.dds.app.exceptions.UbicacionNoEncontradaException;

import java.util.List;
import java.util.Objects;

public class Departamentos {


        public int cantidad;
        public int total;
        public int inicio;
        public Parametro parametros;
        public Provincias provincias;
        public Centroide centroide;
        public List<UbicacionGeografica> departamentos;


        private class Parametro {
                public List<String> campos;
                public int max;
                public List<String> provincia;
        }

        public UbicacionGeografica getDepartamentoDeNombre(String nombre){
                List<UbicacionGeografica> listadoDepartamentos = departamentos.stream().filter(ubicacionGeografica -> Objects.equals(ubicacionGeografica.nombre, nombre)).toList();
                if(listadoDepartamentos.isEmpty()){
                        throw new UbicacionNoEncontradaException("No es posible encontrar el departamento de nombre: " + nombre);
                }
                return listadoDepartamentos.get(0);
        }
}
