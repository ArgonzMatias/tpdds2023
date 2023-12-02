package ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif;

import ar.edu.utn.frba.dds.app.notificador.clients.ApacheCommonsClient;

public class NotificacionEmail implements IMedioNotificacion {

    private final ApacheCommonsClient emailClient;

    public NotificacionEmail() {
        this.emailClient = new ApacheCommonsClient();
    }

    @Override
    public void enviarNotificacion(String contacto, String datos) {
        // TODO abstraer asunto
        String asunto = "Notificacion de fuera de servicio";
        emailClient.enviarMensaje(contacto, asunto, datos);
    }
    
}
