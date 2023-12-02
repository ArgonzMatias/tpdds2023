package ar.edu.utn.frba.dds.app.notificador.estrategiasDeNotif;

public interface IMedioNotificacion {
    void enviarNotificacion(String contacto, String datos);
}