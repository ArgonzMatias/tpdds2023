package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import java.util.Objects;

public abstract class Controller implements WithSimplePersistenceUnit {

    protected void usuarioLogueado(Context ctx) {
        if (ctx.sessionAttribute("usuario_id") == null) {
            ctx.redirect("/");
            return;
        }
        Usuario usuario = entityManager()
                .find(Usuario.class, Long.parseLong(Objects.requireNonNull(ctx.sessionAttribute("usuario_id"))));
        if (usuario == null) {
            ctx.redirect("/");
        }
    }
}
