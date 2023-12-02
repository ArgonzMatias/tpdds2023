package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.repositories.IncidentesRepository;
import ar.edu.utn.frba.dds.app.repositories.UsuariosRepository;

public class FactoryController {

    public static Object controller(String nombre) {
        // Repos
        final IncidentesRepository incidentesRepository = new IncidentesRepository();
        final UsuariosRepository usuariosRepository = new UsuariosRepository();

        return switch (nombre) {
            case "Incidentes" -> new IncidentesController(incidentesRepository);
            case "IncidentesViews" -> new IncidentesViewsController(incidentesRepository);
            case "UsuariosViews" -> new UsuariosViewsController(usuariosRepository);
            case "Usuarios" -> new UsuariosController(usuariosRepository);
            case "Random" -> new RandomController();
            default -> null;
        };
    }
}
