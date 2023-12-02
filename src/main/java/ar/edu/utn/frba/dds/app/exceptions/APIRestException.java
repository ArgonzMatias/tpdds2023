package ar.edu.utn.frba.dds.app.exceptions;

public class APIRestException extends RuntimeException{
    public APIRestException(String mensaje) {
        super(mensaje);
    }
}
