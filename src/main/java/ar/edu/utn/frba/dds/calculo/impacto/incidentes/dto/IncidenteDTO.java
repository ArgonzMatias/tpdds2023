package ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenteDTO {
    private Integer tiempoRespuesta;
    private Boolean estaResuelto;
    private Integer miembrosAfectados;
}
