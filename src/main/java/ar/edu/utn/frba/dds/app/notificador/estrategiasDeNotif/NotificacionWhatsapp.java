package ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif;

import ar.edu.utn.frba.dds.app.notificador.clients.TwilioWhatsappClient;

public class NotificacionWhatsapp implements IMedioNotificacion {
    @Override
    public void enviarNotificacion(String contacto, String datos) {
        TwilioWhatsappClient.enviarMensaje(contacto, datos);
    }
}
