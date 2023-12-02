package ar.edu.utn.frba.dds.app.rankeador.client.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMayorImpacto {
    private List<ResponseEntidadDTO> entidades;
}
