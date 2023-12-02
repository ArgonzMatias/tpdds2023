package ar.edu.utn.frba.dds.app.rankeador;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.exportador.Exportador;

import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.rankeador.criterios.CriterioRanking;

import java.util.ArrayList;
import java.util.List;

public class RankeadorIncidentes {
    private List<Entidad> entidades = new ArrayList<>();
    private List<CriterioRanking> criterios = new ArrayList<>();
    private Exportador exportador;

    public RankeadorIncidentes(Exportador exportador) {
        this.exportador = exportador;
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public void agregarCriterio(CriterioRanking criterioRanking) {
        criterios.add(criterioRanking);
    }

    public List<Exportable> generarInforme(List<Incidente> incidentes, List<Entidad> entidades) {
        List<Exportable> listaDeExportables = new ArrayList<>();
        for (CriterioRanking criterio : criterios){
            Exportable exportable = criterio.generarRanking(incidentes,entidades);
            listaDeExportables.add(exportable);

        }
        return listaDeExportables;
    }
}
