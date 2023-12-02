package ar.edu.utn.frba.dds.app.repositories;


import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;

public class EmpresasRepository extends Repository{
  public Empresa buscarEmpresaPorCodigo(String codigo) {
    return entityManager()
        .createQuery("from Empresa where codigo = :codigo", Empresa.class)
        .setParameter("codigo", codigo)
        .getSingleResult();
  }
}
