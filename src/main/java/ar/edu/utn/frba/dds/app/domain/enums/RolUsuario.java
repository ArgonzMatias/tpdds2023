package ar.edu.utn.frba.dds.app.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum RolUsuario {
    ADMINISTRADOR,
    MIEMBRO;

    public static List<RolUsuario> obtenerTodosLosRoles() {
        return Arrays.asList(values());
    }
}
