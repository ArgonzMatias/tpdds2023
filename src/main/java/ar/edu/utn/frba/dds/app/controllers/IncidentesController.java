package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.domain.prestacion.PrestacionDeServicio;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.repositories.IncidentesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IncidentesController extends Controller {
    private final IncidentesRepository repositorio;

    public IncidentesController(IncidentesRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void getById(Context context) {
        super.usuarioLogueado(context);
        Incidente incidente = (Incidente) this.repositorio.buscar(Incidente.class, Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidente.hbs", model);

    }

    public void save(Context context) {
        super.usuarioLogueado(context);

        Incidente incidente = this.crearIncidente(context);
        this.repositorio.guardar(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/incidentes");
    }


    public void edit(Context context) {
        super.usuarioLogueado(context);
        Incidente incidente = (Incidente) this.repositorio.buscar(Incidente.class, Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidente.hbs", model);

    }

    public void update(Context context) {
        Incidente incidente = (Incidente) this.repositorio.buscar(Incidente.class, Long.parseLong(context.pathParam("id")));
        if (incidente != null) {
            String body = context.body();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> data = objectMapper.readValue(body, new TypeReference<>() {
                });
                asignarParametros(data, incidente);
                this.repositorio.actualizar(incidente);
                context.status(HttpStatus.OK);
            } catch (IOException e) {
                context.status(HttpStatus.BAD_REQUEST);
            }
        } else {
            context.status(HttpStatus.NOT_FOUND);
        }
    }

    public void delete(Context context) {
        super.usuarioLogueado(context);
        Incidente incidente = (Incidente) this.repositorio.buscar(Incidente.class, Long.parseLong(context.pathParam("id")));
        this.repositorio.eliminar(incidente);
        context.redirect("/incidentes");
    }

    private void asignarParametros(Map<String, Object> data, Incidente incidente) {
        if (data.containsKey("detalle")) {
            incidente.setDetalle(data.get("detalle").toString());
        }
        if (data.containsKey("estaResuelto")) {
            incidente.setEstaResuelto(Boolean.parseBoolean(data.get("estaResuelto").toString()));
        }
        if (data.containsKey("fechaCierre")) {
            String fechaCierre = data.get("fechaCierre").toString();
            if (fechaCierre.endsWith("Z")) {
                fechaCierre = fechaCierre.substring(0, fechaCierre.length() - 1);
            }
            incidente.setFechaCierre(LocalDateTime.parse(fechaCierre));
        }
    }

    private Incidente crearIncidente(Context context) {
        String detalleIncidente = context.formParam("detalle");
        Long prestacionId = Long.parseLong(Objects.requireNonNull(context.formParam("prestacion")));
        PrestacionDeServicio prestacionDeServicio = (PrestacionDeServicio) this.repositorio.buscar(PrestacionDeServicio.class, prestacionId);
        return new Incidente(detalleIncidente, null, prestacionDeServicio);
    }
}