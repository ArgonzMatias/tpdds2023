package ar.edu.utn.frba.dds.app.rankeador.criterios;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.exportador.Exportador;
import ar.edu.utn.frba.dds.app.exportador.exportable.Documento;
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.rankeador.criterios.CriterioRanking;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class RankingMasIncidentes implements CriterioRanking {
    public Exportable generarRanking(List<Incidente> incidentes, List<Entidad> entidades) {
        List<Entidad> entidadesRankeadas = rankearEntidadesPorCantidadIncidentes(incidentes, entidades);
        String nombre;
        Documento doc = new Documento();
        doc.agregarDato(String.valueOf(0), "Entidades", "Cantidad de Incidentes");

        for (int i = 0; i < entidadesRankeadas.size(); i++) {
            nombre = entidadesRankeadas.get(i).getNombre();

            doc.agregarDato(String.valueOf(i + 1), nombre, cantidadIncidentesPorEntidadAsString(entidadesRankeadas.get(i), incidentes));
        }

        return doc;
    }

    public List<Entidad> rankearEntidadesPorCantidadIncidentes(List<Incidente> incidentes, List<Entidad> entidades) {
        // Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Filtrar incidentes que ocurrieron en la última semana y cumplan la restricción de 24 horas
        List<Incidente> incidentesValidos = incidentes.stream()
                .filter(incidente -> ChronoUnit.DAYS.between(incidente.getFechaApertura(), fechaActual) <= 7)
                .collect(Collectors.toList());

        // Crear un mapa para contar la cantidad de incidentes por entidad
        Map<Entidad, Integer> cantidadIncidentesPorEntidad = new HashMap<>();

        for (Entidad entidad : entidades) {
            cantidadIncidentesPorEntidad.put(entidad, 0);
        }

        for (Incidente incidente : incidentes) {
            Entidad entidad = incidente.getPrestacionDeServicio().getEstablecimiento().getEntidad();

            if (entidad != null) {
                int cantidadIncidentes = cantidadIncidentesPorEntidad.get(entidad);
                cantidadIncidentesPorEntidad.put(entidad, cantidadIncidentes + 1);
            }
        }

        // Ordenar las entidades por cantidad de incidentes (de mayor a menor)
        List<Entidad> entidadesOrdenadas = entidades.stream()
                .sorted(Comparator.comparingInt(cantidadIncidentesPorEntidad::get).reversed())
                .collect(Collectors.toList());

        return entidadesOrdenadas;
    }

    public String cantidadIncidentesPorEntidadAsString(Entidad entidad, List<Incidente> incidentes) {
        // Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Filtrar incidentes que ocurrieron en la última semana y cumplan la restricción de 24 horas
        List<Incidente> incidentesValidos = incidentes.stream()
                .filter(incidente -> ChronoUnit.DAYS.between(incidente.getFechaApertura(), fechaActual) <= 7)
                .collect(Collectors.toList());

        // Contar la cantidad de incidentes para la entidad especificada
        long cantidadIncidentes = incidentes.stream()
                .filter(incidente -> entidad.equals(incidente.getPrestacionDeServicio().getEstablecimiento().getEntidad()))
                .count();

        return String.valueOf(cantidadIncidentes);
    }

}