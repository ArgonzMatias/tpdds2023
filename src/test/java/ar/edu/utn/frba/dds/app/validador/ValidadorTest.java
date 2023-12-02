package ar.edu.utn.frba.dds.app.validador;

import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;
import ar.edu.utn.frba.dds.app.validador.Validador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidadorTest {

    private final Validador validador = new Validador();

    @Test
    @DisplayName("La contrasenia es demasiado corta y no alcanza los 8 caracteres")
    public void validadorFallaConContraseniaCorta() {
        String contrasenia = "1234567";
        String mensajeEsperado = "La contraseña debe tener entre 8 y 20 caracteres.";

        ValidadorException exception = Assertions.assertThrows(ValidadorException.class, () -> validador.validarContrasenia(contrasenia));

        String mensajeObtenido = exception.getMessage();
        Assertions.assertEquals(mensajeEsperado, mensajeObtenido);
    }

    @Test
    @DisplayName("La contrasenia es demasiado larga y no supera los 20 caracteres")
    public void validadorFallaConContraseniaLarga() {
        String contrasenia = "12345678910111123123211";
        String mensajeEsperado = "La contraseña debe tener entre 8 y 20 caracteres.";

        ValidadorException exception = Assertions.assertThrows(ValidadorException.class, () -> validador.validarContrasenia(contrasenia));

        String mensajeObtenido = exception.getMessage();
        Assertions.assertEquals(mensajeEsperado, mensajeObtenido);
    }

    @Test
    @DisplayName("La contrasenia es muy debil")
    public void validadorFallaConContraseniaDebil() {
        String contrasenia = "password";
        String mensajeEsperado = "La contraseña es demasiado débil.";

        ValidadorException exception = Assertions.assertThrows(ValidadorException.class, () -> validador.validarContrasenia(contrasenia));

        String mensajeObtenido = exception.getMessage();
        Assertions.assertEquals(mensajeEsperado, mensajeObtenido);
    }
    @Test
    @DisplayName("La contrasenia no es compleja")
    public void validadorFallaConContraseniaNoCompleja() {
        String contrasenia = "PasWorDddd";
        String mensajeEsperado = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un caracter especial.";

        ValidadorException exception = Assertions.assertThrows(ValidadorException.class, () -> validador.validarContrasenia(contrasenia));

        String mensajeObtenido = exception.getMessage();
        Assertions.assertEquals(mensajeEsperado, mensajeObtenido);
    }

    @Test
    @DisplayName("La contrasenia es correcta")
    public void validadorCorrectaContrasenia() {
        String contrasenia = "miContrasenia12345.";

        boolean esValida = validador.validarContrasenia(contrasenia);

        Assertions.assertTrue(esValida);
    }
}
