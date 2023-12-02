package ar.edu.utn.frba.dds.app.repositories;

import ar.edu.utn.frba.dds.app.domain.enums.Estado;
import ar.edu.utn.frba.dds.app.domain.prestacion.PrestacionDeServicio;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;

import java.util.List;

public class IncidentesRepository extends Repository {
    public PrestacionDeServicio buscarPrestacionDeServicioPorEstablecimientoYServicio(Long establecimientoId, Long servicioId) {
        return entityManager()
                .createQuery("SELECT ps FROM PrestacionDeServicio ps " +
                        "WHERE ps.establecimiento.id = :establecimientoId " +
                        "AND ps.servicio.id = :servicioId", PrestacionDeServicio.class)
                .setParameter("establecimientoId", establecimientoId)
                .setParameter("servicioId", servicioId)
                .getSingleResult();
    }
    public List<Incidente> buscarIncidentesAbiertos() {
        return entityManager()
                .createQuery("FROM Incidente", Incidente.class).getResultList();
    }
}
