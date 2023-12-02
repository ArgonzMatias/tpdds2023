package ar.edu.utn.frba.dds.app.rankeador.criterios;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;

import java.util.List;

public interface CriterioRanking {
    Exportable generarRanking(List<Incidente> incidentes, List<Entidad> entidades);
}
