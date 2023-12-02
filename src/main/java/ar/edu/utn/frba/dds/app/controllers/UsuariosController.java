package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.domain.enums.RolUsuario;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.exceptions.ValidadorException;
import ar.edu.utn.frba.dds.app.repositories.UsuariosRepository;
import ar.edu.utn.frba.dds.app.validador.Validador;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.util.Map;

public class UsuariosController extends Controller {

    private final UsuariosRepository repositorio;

    private final Validador validador = new Validador();

    public UsuariosController(UsuariosRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void save(Context context) {
        try {
            String body = context.body();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> data = objectMapper.readValue(body, new TypeReference<>() {
                });
                Usuario usuario = crearUsuario(data);
                boolean esValida = validador.validarContrasenia(usuario.getContrasenia());
                if (esValida) {
                    this.repositorio.guardar(usuario);
                    context.status(HttpStatus.CREATED);
                    context.result("El usuario se creo con exito");
                }
            } catch (IOException e) {
                context.status(HttpStatus.BAD_REQUEST);
            }
        } catch (ValidadorException e) {
            context.status(HttpStatus.BAD_REQUEST);
            context.result(e.getMessage());
        }
    }


    private Usuario crearUsuario(Map<String, Object> data) {
        Usuario usuario = new Usuario();
        if (data.containsKey("correo")) {
            usuario.setCorreoElectronico(data.get("correo").toString());
        }
        if (data.containsKey("contrasenia")) {
            usuario.setContrasenia(data.get("contrasenia").toString());
        }
        if (data.containsKey("rol")) {
            usuario.setRol(RolUsuario.valueOf(data.get("rol").toString()));
        }
        if (data.containsKey("numero")) {
            usuario.setNumero(data.get("numero").toString());
        }
        return usuario;
    }

    public void delete(Context context) {
        super.usuarioLogueado(context);
        Usuario usuario = (Usuario) this.repositorio.buscar(Usuario.class, Long.parseLong(context.pathParam("id")));
        this.repositorio.eliminar(usuario);
        context.redirect("/admin");
    }
}
