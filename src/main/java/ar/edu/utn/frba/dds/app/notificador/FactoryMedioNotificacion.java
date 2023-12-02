package ar.edu.utn.frba.dds.app.notificador;

import ar.edu.utn.frba.dds.app.domain.notificacion.MedioNotificacion;
import ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif.IMedioNotificacion;
import ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif.NotificacionEmail;
import ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif.NotificacionWhatsapp;
import java.util.HashMap;
import java.util.Map;

public class FactoryMedioNotificacion {

    private static final Map<MedioNotificacion, IMedioNotificacion> INSTANCIAS = new HashMap<>();

    static {
        INSTANCIAS.put(MedioNotificacion.WHATSAPP, new NotificacionWhatsapp());
        INSTANCIAS.put(MedioNotificacion.EMAIL, new NotificacionEmail());
    }

    public static IMedioNotificacion get(MedioNotificacion medio) {
        return INSTANCIAS.get(medio);
    }
}
