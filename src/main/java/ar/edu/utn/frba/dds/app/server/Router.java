package ar.edu.utn.frba.dds.app.server;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import ar.edu.utn.frba.dds.app.controllers.FactoryController;
import ar.edu.utn.frba.dds.app.controllers.IncidentesController;
import ar.edu.utn.frba.dds.app.controllers.IncidentesViewsController;
import ar.edu.utn.frba.dds.app.controllers.RandomController;
import ar.edu.utn.frba.dds.app.controllers.UsuariosController;
import ar.edu.utn.frba.dds.app.controllers.UsuariosViewsController;

public class Router {
    public static void init() {
        Server.app().routes(() -> {
            get("/admin", ((UsuariosViewsController) FactoryController.controller("UsuariosViews"))::admin);
            get("/", ((UsuariosViewsController) FactoryController.controller("UsuariosViews"))::login);
            post("/login", ((UsuariosViewsController) FactoryController.controller("UsuariosViews"))::handleLogin);
            get("/logout", ((UsuariosViewsController) FactoryController.controller("UsuariosViews"))::logout);
            get("/usuarios/crear", ((UsuariosViewsController) FactoryController.controller("UsuariosViews"))::crear);
            get("/usuarios/cerrar", ((UsuariosViewsController) FactoryController.controller("UsuariosViews"))::cerrar);

            post("/usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::save);
            delete("usuarios/{id}", ((UsuariosController) FactoryController.controller("Usuarios"))::delete);

            get("/incidentes", ((IncidentesViewsController) FactoryController.controller("IncidentesViews"))::index);
            get("/incidentes/crear", ((IncidentesViewsController) FactoryController.controller("IncidentesViews"))::crear);
            get("/incidentes/cerrar", ((IncidentesViewsController) FactoryController.controller("IncidentesViews"))::cerrar);
            get("/incidentes/sugerencias", ((IncidentesViewsController) FactoryController.controller("IncidentesViews"))::sugerencias);
            get("/incidentes/{id}/editar", ((IncidentesViewsController) FactoryController.controller("IncidentesViews"))::editar);

            get("/incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::getById);
            put("/incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
            post("/incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
            delete("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::delete);

            get("/ranking", ((RandomController) FactoryController.controller("Random"))::ranking);
            get("/faq", ((RandomController) FactoryController.controller("Random"))::faq);
            get("/cargarEntidad", ((RandomController) FactoryController.controller("Random"))::cargarEntidad);
            post("/cargarEntidad", ((RandomController) FactoryController.controller("Random"))::cargarEntidadCSV);
        });
    }
}
