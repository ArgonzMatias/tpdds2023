package ar.edu.utn.frba.dds.app.notificador;

import ar.edu.utn.frba.dds.app.domain.notificacion.EstadoNotificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.MedioNotificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.Notificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.FormaNotificacion;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif.IMedioNotificacion;
import ar.edu.utn.frba.dds.app.repositories.Repository;

public class Notificador {
    private final Repository repositorioNotificaciones;

    public Notificador(Repository repositorioNotificaciones) {
        this.repositorioNotificaciones = repositorioNotificaciones;
    }

    public void enviarNotificacionSiCorresponde(Contactable contactable, Notificable notificable){
        EstadoNotificacion estadoNotificacion;
        if (FormaNotificacion.INMEDIATA.equals(contactable.formaDeContacto())) {
            enviarNotificacion(contactable.contacto(), contactable.medioNotificacion(), notificable.getDatos());
            estadoNotificacion = EstadoNotificacion.ENVIADO;
        } else {
            estadoNotificacion = EstadoNotificacion.PENDIENTE;
        }
        Notificacion notificacion = new Notificacion(contactable, notificable, estadoNotificacion);
        this.repositorioNotificaciones.guardar(notificacion);
    }

    public void enviarNotificacionAsincronica(Notificacion notificacion) {
        enviarNotificacion(notificacion.getContacto(), notificacion.getMedioNotificacion(), notificacion.getDatos());
        notificacion.setEstado(EstadoNotificacion.ENVIADO);
        this.repositorioNotificaciones.actualizar(notificacion);
    }

    private void enviarNotificacion(Usuario contacto, MedioNotificacion medioNotificacion, String datos){
        IMedioNotificacion medioNotificacionFactory = FactoryMedioNotificacion.get(medioNotificacion);
        String datoContacto;
        if (medioNotificacion.equals(MedioNotificacion.EMAIL)) {
            datoContacto = contacto.getCorreoElectronico();
        } else {
            datoContacto = contacto.getNumero();
        }
        medioNotificacionFactory.enviarNotificacion(datoContacto, datos);
    }
    
}
