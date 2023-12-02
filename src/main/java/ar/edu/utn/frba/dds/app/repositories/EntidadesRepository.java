package ar.edu.utn.frba.dds.app.repositories;

import ar.edu.utn.frba.dds.app.domain.Entidad;

public class EntidadesRepository extends Repository{

  public Entidad buscarEntidadPorCodigo(String codigo) {
    return entityManager()
        .createQuery("from Entidad where codigo = :codigo", Entidad.class)
        .setParameter("codigo", codigo)
        .getSingleResult();
  }
}
