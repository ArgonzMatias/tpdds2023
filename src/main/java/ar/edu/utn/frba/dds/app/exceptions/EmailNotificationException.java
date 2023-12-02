package ar.edu.utn.frba.dds.app.exceptions;

public class EmailNotificationException extends RuntimeException {
    public EmailNotificationException(String mensaje) {
        super(mensaje);
    }
}
