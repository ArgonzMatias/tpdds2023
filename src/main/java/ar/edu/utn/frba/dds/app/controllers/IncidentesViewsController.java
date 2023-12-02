package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.domain.prestacion.PrestacionDeServicio;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.repositories.IncidentesRepository;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesViewsController extends Controller {

    private final IncidentesRepository repositorio;

    public IncidentesViewsController(IncidentesRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void index(Context context) {
        super.usuarioLogueado(context);
        // TODO chequear si cuando no esta logueado el usuario no sigue con lo demas
        Map<String, Object> model = new HashMap<>();
        List incidentes = this.repositorio.buscarTodos("Incidente");
        model.put("incidentes", incidentes);
        model.put("admin", context.sessionAttribute("rol")=="ADMINISTRADOR");
        context.render("incidentes.hbs", model);
    }

    public void crear(Context context) {
        super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        Incidente incidente = null;
        model.put("incidente", incidente);
        List establecimientos = this.repositorio.buscarTodos("PrestacionDeServicio");
        model.put("prestaciones", establecimientos);
        context.render("aperturaIncidentes.hbs", model);
    }

    public void cerrar(Context context) {
        super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        List incidentes = this.repositorio.buscarTodos("Incidente");
        model.put("incidentes", incidentes);
        context.render("cierreIncidentes.hbs", model);
    }

    public void sugerencias(Context context) {
        super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorio.buscarIncidentesAbiertos();
        List<Incidente> incidentes1 = incidentes.stream().filter(incidente -> !incidente.getEstaResuelto()).toList();
        model.put("incidentes", incidentes1);
        context.render("sugerenciaIncidentes.hbs", model);
    }

    public void editar(Context context) {
        super.usuarioLogueado(context);
        context.redirect("/incidentes");
        // TODO agregar pantalla
    }


}
