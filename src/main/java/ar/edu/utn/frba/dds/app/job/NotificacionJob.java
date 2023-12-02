package ar.edu.utn.frba.dds.app.job;

import ar.edu.utn.frba.dds.app.domain.notificacion.EstadoNotificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.Notificacion;
import ar.edu.utn.frba.dds.app.notificador.Notificador;
import ar.edu.utn.frba.dds.app.repositories.NotificacionesRepository;
import ar.edu.utn.frba.dds.app.repositories.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificacionJob implements Job {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm");
    private final Notificador notificador;
    private final NotificacionesRepository repositorioNotificaciones;

    public NotificacionJob(Notificador notificador, NotificacionesRepository repositorioNotificaciones) {
        this.notificador = notificador;
        this.repositorioNotificaciones = repositorioNotificaciones;
    }

    @Override
    public void ejecutar() {
        List<Notificacion> notificacionesPendientes = repositorioNotificaciones.buscarNotificacionesPorEstado(EstadoNotificacion.PENDIENTE);
        notificacionesPendientes.forEach(notificacion -> {
            List<String> horariosDisponibles = notificacion.getHorariosDisponibles();
            String horarioActual = LocalDateTime.now().format(FORMATO);
            if (horariosDisponibles.contains(horarioActual)) {
                notificador.enviarNotificacionAsincronica(notificacion);
            }
        });
    }
}
