package ar.edu.utn.frba.dds.app.notificador;

import ar.edu.utn.frba.dds.app.domain.notificacion.MedioNotificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.FormaNotificacion;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;

import java.util.List;

public interface Contactable {
    FormaNotificacion formaDeContacto();
    MedioNotificacion medioNotificacion();
    List<String> horariosDisponibles();
    Usuario contacto();
}