package ar.edu.utn.frba.dds.app.notificador.clients;

import ar.edu.utn.frba.dds.app.exceptions.EmailNotificationException;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class ApacheCommonsClient {
    public void enviarMensaje(String destinatario, String asunto, String contenido) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("dds.tp.grupo6.2023@gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("dds.tp.grupo6.2023@gmail.com", "ddsgrupo6"));
            email.setSSLOnConnect(true);
            email.setFrom("dds.tp.grupo6.2023@gmail.com");
            email.setSubject(asunto);
            email.setMsg(contenido);
            email.addTo(destinatario);
            email.send();
        } catch (EmailException e) {
            throw new EmailNotificationException("El envío del email no se ejecutó correctamente: " + e.getMessage());
        }
    }
}
