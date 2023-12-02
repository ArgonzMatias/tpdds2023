package ar.edu.utn.frba.dds.app.exceptions;

public class ValidadorException extends RuntimeException {
    public ValidadorException(String mensaje) {
        super(mensaje);
    }
}
