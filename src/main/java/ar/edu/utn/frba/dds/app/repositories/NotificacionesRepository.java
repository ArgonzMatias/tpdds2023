package ar.edu.utn.frba.dds.app.repositories;

import ar.edu.utn.frba.dds.app.domain.notificacion.EstadoNotificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.Notificacion;
import java.util.List;

public class NotificacionesRepository extends Repository {
    public List<Notificacion> buscarNotificacionesPorEstado(EstadoNotificacion estado) {
        return entityManager()
                .createQuery("from Notificacion where estado = :estado", Notificacion.class)
                .setParameter("estado", estado.name())
                .getResultList();
    }
}
