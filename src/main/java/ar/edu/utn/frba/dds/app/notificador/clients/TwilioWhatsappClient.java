package ar.edu.utn.frba.dds.app.notificador.clients;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioWhatsappClient {
    private static final String ACCOUNT_SID = "AC8bfb1150670915857140a31fe7b79414";
    private static final String AUTH_TOKEN = "125a8c0e41c3076cac7fc15bc0b62ebf";

    public static void enviarMensaje(String destinatario, String mensaje) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + destinatario),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                mensaje).create();

        System.out.println(message.getSid());
    }
}
