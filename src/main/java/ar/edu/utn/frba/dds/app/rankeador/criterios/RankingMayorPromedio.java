package ar.edu.utn.frba.dds.app.rankeador.criterios;


import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.exportador.exportable.Documento;
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;

import java.util.*;

public class RankingMayorPromedio implements CriterioRanking {
    @Override
    public Exportable generarRanking(List<Incidente> incidentes, List<Entidad> entidades) {
        List<Entidad> entidadesCalculadas = calcularPromedioCierrePorEntidad(incidentes, entidades);
        String nombre;
        double tiempoDeCierrePromedio;
        String tiempoDeCierrePromedioString;
        Documento doc = new Documento();
        doc.agregarDato(String.valueOf(0), "Entidades", "Tiempo Cierre Promedio (horas)");

        for (int i = 0; i < entidadesCalculadas.size(); i++) {
            nombre = entidadesCalculadas.get(i).getNombre();
            tiempoDeCierrePromedio = convertMinutesToHours(entidadesCalculadas.get(i).getTiempoDeCierrePromedio());
            tiempoDeCierrePromedioString = String.valueOf(tiempoDeCierrePromedio);
            doc.agregarDato(String.valueOf(i + 1), nombre, tiempoDeCierrePromedioString);
        }

        return doc;
    }

    public List<Entidad> calcularPromedioCierrePorEntidad(List<Incidente> incidentes, List<Entidad> entidades) {
        // Crear un mapa que asocie cada entidad con la suma total de tiempos de cierre de sus incidentes
        Map<Entidad, Long> tiempoCierrePorEntidad = new HashMap<>();
        Map<Entidad, Long> contadorIncidentesPorEntidad = new HashMap<>();

        // Inicializar los mapas
        for (Entidad entidad : entidades) {
            tiempoCierrePorEntidad.put(entidad, 0L);
            contadorIncidentesPorEntidad.put(entidad, 0L);
        }

        // Calcular la suma total de tiempos de cierre y el contador de incidentes por entidad
        for (Incidente incidente : incidentes) {

            Entidad entidad = incidente.getPrestacionDeServicio().getEstablecimiento().getEntidad();

            if (entidad != null) {
                tiempoCierrePorEntidad.put(entidad, tiempoCierrePorEntidad.get(entidad) + incidente.minutosAbierto());
                contadorIncidentesPorEntidad.put(entidad, contadorIncidentesPorEntidad.get(entidad) + 1);
            }
        }

        // Calcular el promedio de tiempo de cierre por entidad
        List<Entidad> entidadesConPromedio = new ArrayList<>();
        for (Entidad entidad : entidades) {
            Long tiempoCierreTotal = tiempoCierrePorEntidad.get(entidad);
            Long cantidadIncidentes = contadorIncidentesPorEntidad.get(entidad);

            if (cantidadIncidentes > 0) {
                double promedio = tiempoCierreTotal.doubleValue() / cantidadIncidentes.doubleValue();
                entidad.setTiempoDeCierrePromedio(promedio);
                entidadesConPromedio.add(entidad);
            }
        }

        // Ordenar las entidades por promedio de tiempo de cierre (de mayor a menor)
        entidadesConPromedio.sort(Comparator.comparingDouble(Entidad::getTiempoDeCierrePromedio).reversed());

        return entidadesConPromedio;
    }

    public static double convertMinutesToHours(double minutes) {
        return minutes / 60.0;
    }

}
