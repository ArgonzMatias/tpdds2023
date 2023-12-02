package ar.edu.utn.frba.dds.app.validador;

import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;

public interface Validable {
    void validar(String contrasenia) throws ValidadorException;
}
