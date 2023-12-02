package ar.edu.utn.frba.dds.calculo.impacto.incidentes;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import ar.edu.utn.frba.dds.calculo.impacto.incidentes.controllers.ServicioController;
import ar.edu.utn.frba.dds.calculo.impacto.incidentes.swagger.SwaggerInitializer;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

public class Main {
    public static void main(String[] args) {
        Javalin.create(config -> {
            config.plugins.register(new OpenApiPlugin(SwaggerInitializer.getOpenApiConfiguration()));
            config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
            config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));
        })
                .routes(() -> path("calculate", () -> post(ServicioController::calculate)))
                .start(8080);

        System.out.println("Check out ReDoc docs at http://localhost:8080/redoc");
        System.out.println("Check out Swagger UI docs at http://localhost:8080/swagger");
    }
}
