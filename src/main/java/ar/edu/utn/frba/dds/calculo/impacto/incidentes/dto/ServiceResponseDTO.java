package ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponseDTO {
    private List<EntidadResultDTO> entidades;

    public ServiceResponseDTO() {
        this.entidades = new ArrayList<>();
    }

    public void agregarEntidad(EntidadResultDTO serviceResultDTO) {
        entidades.add(serviceResultDTO);
    }

    public void ordenarPorImpacto() {
        entidades.sort((entidad1, entidad2) -> Integer.compare(entidad2.getImpactoIncidentes(), entidad1.getImpactoIncidentes()));
    }
}
