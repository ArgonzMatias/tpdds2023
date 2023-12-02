package ar.edu.utn.frba.dds.app.exceptions;

public class DeserealizacionException extends RuntimeException{
    public DeserealizacionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
