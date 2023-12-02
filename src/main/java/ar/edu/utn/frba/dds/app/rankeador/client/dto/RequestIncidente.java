package ar.edu.utn.frba.dds.app.rankeador.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestIncidente {
    private long tiempoRespuesta;
    private boolean estaResuelto;
    private int miembrosAfectados;
}
