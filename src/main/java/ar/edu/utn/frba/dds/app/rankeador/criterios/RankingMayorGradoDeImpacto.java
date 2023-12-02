package ar.edu.utn.frba.dds.app.rankeador.criterios;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Comunidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.exportador.exportable.Documento;
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.rankeador.client.RankingMayorImpactoClient;
import ar.edu.utn.frba.dds.app.rankeador.client.dto.*;

import java.util.ArrayList;
import java.util.List;

public class RankingMayorGradoDeImpacto implements CriterioRanking {
    private static final String apiUrl = "http://localhost:8080/calculate";
    private final RankingMayorImpactoClient apiClient;

    public RankingMayorGradoDeImpacto() {
        this.apiClient = new RankingMayorImpactoClient(apiUrl);
    }

    @Override
    public Exportable generarRanking(List<Incidente> incidentes, List<Entidad> entidades) {
        ResponseMayorImpacto apiResponse = apiClient.enviarSolicitud(buildRequest(incidentes, entidades));
        return buildExportable(apiResponse);
    }

    private Exportable buildExportable(ResponseMayorImpacto apiResponse) {
        Documento doc = new Documento();
        doc.agregarDato(String.valueOf(0), "Codigo Incidente", "Grado Impacto");
        List<ResponseEntidadDTO> entidadesCalculadas = apiResponse.getEntidades();
        String nombre;
        int impacto;
        for (int i = 0; i < entidadesCalculadas.size(); i++) {
            nombre = entidadesCalculadas.get(i).getCodigoEntidad();
            impacto = entidadesCalculadas.get(i).getImpactoIncidentes();

            doc.agregarDato(String.valueOf(i + 1), nombre, String.valueOf(impacto));
        }

        return doc;
    }

    private RequestMayorImpacto buildRequest(List<Incidente> incidentes, List<Entidad> entidades) {
        RequestMayorImpacto request = new RequestMayorImpacto();

        entidades.forEach(entidad -> {
            RequestEntidad requestEntidad = buildRequestEntidad(entidad, incidentes);
            request.agregarEntidad(requestEntidad);
        });

        return request;
    }

    private RequestEntidad buildRequestEntidad(Entidad entidad, List<Incidente> incidentes) {
        RequestEntidad requestEntidad = new RequestEntidad();
        requestEntidad.setCodigo(entidad.getCodigo());

        List<Incidente> incidentesEntidades = obtenerIncidentesEntidades(entidad, incidentes);

        incidentesEntidades.forEach(incidente -> {
            RequestIncidente requestIncidente = buildRequestIncidente(incidente);
            requestEntidad.agregarIncidente(requestIncidente);
        });

        return requestEntidad;
    }

    private RequestIncidente buildRequestIncidente(Incidente incidente) {
        RequestIncidente requestIncidente = new RequestIncidente();
        requestIncidente.setEstaResuelto(incidente.getEstaResuelto());

        if (incidente.getEstaResuelto()) {
            requestIncidente.setTiempoRespuesta(incidente.minutosAbierto());
        }

        List<Comunidad> comunidadesInteresadas = incidente.getPrestacionDeServicio().getComunidadesInteresadas();
        requestIncidente.setMiembrosAfectados(obtenerMiembrosInteresados(comunidadesInteresadas));

        return requestIncidente;
    }

    public static List<Incidente> obtenerIncidentesEntidades(Entidad entidad, List<Incidente> incidentes) {
        List<Incidente> listaNueva = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            if (incidente.getPrestacionDeServicio().getEstablecimiento().getEntidad().getCodigo() == entidad.getCodigo()) {
                listaNueva.add(incidente);
            }
        }

        return listaNueva;
    }

    private static int obtenerMiembrosInteresados(List<Comunidad> comunidadesInteresadas) {
        return comunidadesInteresadas.stream()
                .mapToInt(Comunidad::getCantidadTotalDeMiembros)
                .sum();
    }


}
