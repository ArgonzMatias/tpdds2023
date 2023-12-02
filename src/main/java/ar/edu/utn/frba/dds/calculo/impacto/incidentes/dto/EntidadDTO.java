package ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntidadDTO {
    private String codigo;
    private List<IncidenteDTO> incidentes;

    public Integer calcularImpacto(Integer coeficienteIncidentesNoResueltos) {
        int sumTiempoRespuesta = incidentes.stream()
                .filter(IncidenteDTO::getEstaResuelto)
                .mapToInt(IncidenteDTO::getTiempoRespuesta)
                .sum();

        Integer cantidadIncidentesNoResueltos = incidentes.stream()
                .filter(incidenteDTO -> !incidenteDTO.getEstaResuelto())
                .toList().size();

        int miembrosAfectados = incidentes.stream()
                .mapToInt(IncidenteDTO::getMiembrosAfectados)
                .sum();

        return (sumTiempoRespuesta + (cantidadIncidentesNoResueltos * coeficienteIncidentesNoResueltos)) * miembrosAfectados;
    }
}
