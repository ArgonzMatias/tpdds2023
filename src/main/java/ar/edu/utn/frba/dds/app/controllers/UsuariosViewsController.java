package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.domain.enums.RolUsuario;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.repositories.IncidentesRepository;
import ar.edu.utn.frba.dds.app.repositories.UsuariosRepository;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.NoResultException;

public class UsuariosViewsController extends Controller {
    private final UsuariosRepository repositorio;

    public UsuariosViewsController(UsuariosRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void login(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.render("ingresoUsuario.hbs", model);
    }

    public void handleLogin(Context context) {
        if (Objects.equals(context.formParam("password"), "") || Objects.equals(context.formParam("username"), "")) {
            context.redirect("/");
        } else {
            try {
                Usuario user = this.repositorio.buscarUsuarioPorCorreo(context.formParam("username"));
                if (user.getContrasenia().equals(context.formParam("password"))) {
                    context.sessionAttribute("usuario_id", user.getId().toString());
                    context.sessionAttribute("rol", user.getRol().toString());
                    context.redirect("/incidentes");
                } else {
                    context.redirect("/");
                }
            } catch (NoResultException e) {
                context.redirect("/");
            }
        }
    }

    public void admin(Context context) {
        // TODO faltaria agarrar las comunidades que se pueda ser admin para mostrarlas, tambien meter un chequeo de si el usuario es admin para mostrar la vista o no
        super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        List comunidades = this.repositorio.buscarTodos("Comunidad");
        model.put("comunidad", comunidades);
        model.put("admin", context.sessionAttribute("rol") == "ADMINISTRADOR");
        context.render("adminUsuario.hbs", model);
    }

    public void logout(Context context) {
        context.sessionAttribute("usuario_id", null);
        context.sessionAttribute("rol", null);
        context.redirect("/");
    }


    public void crear(Context context) {
        super.usuarioLogueado(context);
        if(context.sessionAttribute("rol") == "MIEMBRO"){
            context.redirect("/incidentes");
        }
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = null;
        model.put("usuario", usuario);
        List roles = RolUsuario.obtenerTodosLosRoles();
        model.put("roles", roles);
        context.render("crearUsuario.hbs", model);

    }

    public void cerrar(Context context) {
        super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        List usuarios = this.repositorio.buscarTodos("Usuarios");
        model.put("usuarios", usuarios);
        context.render("borrarUsuario.hbs", model);
    }
}

