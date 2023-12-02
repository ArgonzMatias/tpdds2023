package ar.edu.utn.frba.dds.app.rankeador.client.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMayorImpacto {
    private List<RequestEntidad> entidades;
    public RequestMayorImpacto() {
        this.entidades = new ArrayList<>();
    }


    public void agregarEntidad(RequestEntidad entidad) {
        this.entidades.add(entidad);
    }
}
