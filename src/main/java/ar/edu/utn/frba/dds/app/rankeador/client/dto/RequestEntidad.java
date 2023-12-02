package ar.edu.utn.frba.dds.app.rankeador.client.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEntidad {
    private String codigo;
    private List<RequestIncidente> incidentes;

    public RequestEntidad() {
        this.incidentes = new ArrayList<>();
    }

    public void agregarIncidente(RequestIncidente incidente) {
        this.incidentes.add(incidente);
    }
}
