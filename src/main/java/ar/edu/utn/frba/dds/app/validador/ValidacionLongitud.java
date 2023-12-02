package ar.edu.utn.frba.dds.app.validador;

import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;

public class ValidacionLongitud implements Validable {
    private int minLargo;
    private int maxLargo;

    public ValidacionLongitud(int minLargo, int maxLargo) {
        this.minLargo = minLargo;
        this.maxLargo = maxLargo;
    }

    @Override
    public void validar(String contrasenia) throws ValidadorException {
        if (contrasenia.length() < minLargo || contrasenia.length() > maxLargo) {
            throw new ValidadorException("La contraseña debe tener entre " + minLargo + " y " + maxLargo + " caracteres.");
        }
    }
}
