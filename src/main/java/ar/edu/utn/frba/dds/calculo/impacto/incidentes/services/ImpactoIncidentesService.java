package ar.edu.utn.frba.dds.calculo.impacto.incidentes.services;

import ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto.EntidadResultDTO;
import ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto.ServiceRequestDTO;
import ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto.ServiceResponseDTO;

public class ImpactoIncidentesService {
    private static final Integer coeficienteIncidentesNoResueltos = 1;
    public static ServiceResponseDTO calculate(ServiceRequestDTO requestDTO) {
        ServiceResponseDTO response = new ServiceResponseDTO();

        requestDTO.getEntidades().forEach(entidadDTO -> {
            EntidadResultDTO entidadResultDTO = new EntidadResultDTO(entidadDTO.getCodigo(), entidadDTO.calcularImpacto(coeficienteIncidentesNoResueltos));
            response.agregarEntidad(entidadResultDTO);
        });
        response.ordenarPorImpacto();

        return response;
    }
}
