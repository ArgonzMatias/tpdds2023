package ar.edu.utn.frba.dds.app.validador;

import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;

public class ValidacionComplejidad implements Validable {
    private static final String REQUERIMIENTOS = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un caracter especial.";
    private static final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,}$";

    @Override
    public void validar(String contrasenia) throws ValidadorException {
        if (!contrasenia.matches(REGEX)) {
            throw new ValidadorException(REQUERIMIENTOS);
        }
    }
}
