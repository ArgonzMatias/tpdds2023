package ar.edu.utn.frba.dds.app.validador;

import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ValidacionDebil implements Validable {
    private List<String> contraseniasDebiles;

    public ValidacionDebil() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("contrasenias.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            contraseniasDebiles = br.lines().collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            throw new ValidadorException("No se pudo cargar la lista de contraseñas débiles.");
        }
    }
    @Override
    public void validar(String contrasenia) throws ValidadorException {
        if (contraseniasDebiles.contains(contrasenia)) {
            throw new ValidadorException("La contraseña es demasiado débil.");
        }
    }
}
