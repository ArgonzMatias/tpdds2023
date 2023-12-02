package ar.edu.utn.frba.dds.app.validador;

import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;
import java.util.ArrayList;
import java.util.List;

public class Validador {
    private static final int MIN_LARGO_CONTRASENIA = 8;

    private static final int MAX_LARGO_CONTRASENIA = 20;
    private List<Validable> validaciones;

    public Validador() {
        this.validaciones = new ArrayList<>();
        this.validaciones.add(new ValidacionLongitud(MIN_LARGO_CONTRASENIA, MAX_LARGO_CONTRASENIA));
        this.validaciones.add(new ValidacionDebil());
        this.validaciones.add(new ValidacionComplejidad());
    }

    public boolean validarContrasenia(String contrasenia) throws ValidadorException {
        for (Validable validacion : validaciones) {
            validacion.validar(contrasenia);
        }
        return true;
    }
}
