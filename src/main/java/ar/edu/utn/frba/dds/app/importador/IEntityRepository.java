package ar.edu.utn.frba.dds.app.importador;

public interface IEntityRepository<T> {
    T getByCodigo(String codigo);
}