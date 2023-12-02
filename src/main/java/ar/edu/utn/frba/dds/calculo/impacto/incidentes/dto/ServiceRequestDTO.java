package ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestDTO {
    private List<EntidadDTO> entidades;
}